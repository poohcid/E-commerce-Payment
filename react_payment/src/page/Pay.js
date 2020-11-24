import React, { useState } from "react";
import "../App.css";
import Navbar from "../component/navbar";
import ProductTable from "../component/productListTable";
import PromotionTable from "../component/promotionTable";
import { useParams } from "react-router-dom";
import { Link, useLocation } from "react-router-dom";
import styled from "styled-components";
import { useSelector } from "react-redux";

const Pay = () => {
  const location = useLocation();
  const { orderId } = useParams();
  const {
    netPayment,
    taltalPrice,
    discount,
    promotions,
    date,
  } = location.state;
  const [promotionId, setPromotionId] = useState();
  const token = useSelector((state) => state.athorize.id);
  const [pro, setPro] = useState(promotions);
  const [discountLo, setDisCountLo] = useState(discount);
  const [netPaymentLo, setNetPaymentLo] = useState(netPayment);

  const paid = async (orderId) => {
    const url = "http://localhost:8080/pay";
    const body = { order_id: orderId };
    const response = await fetch(url, {
      method: "POST",
      body: JSON.stringify(body),
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    if (response.status === 200) {
      const json = await response.json();
      console.log(json, "PAID");
    }
  };

  const addPromotion = async (orderId) => {
    const url = "http://localhost:8080/promotion/add";
    const body = {
      order_id: Number(orderId),
      promotion_id: Number(promotionId),
    };
    const response = await fetch(url, {
      method: "POST",
      body: JSON.stringify(body),
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    if (response.status === 200) {
      const json = await response.json();
      setPro([...pro, body]);
      setNetPaymentLo(json.netPayment);
      setDisCountLo(json.discount);
      console.log(json, "Add Promotion");
    }
    console.log(body);
    console.log(promotions);
  };

  return (
    <React.Fragment>
      <Navbar />
      <div className="container">
        <h1>รายการสินค้า</h1>
        <ProductTable orderId={orderId} />
        <div className="price-container">
          <h2>ราคาทั้งหมด : {taltalPrice} บาท</h2>
        </div>
        <div>
          <h1>โปรโมชั่น</h1>
          <div>
            <Input onChange={(e) => setPromotionId(e.target.value)} />
            <Button onClick={() => addPromotion(orderId)}>เพิ่ม</Button>
          </div>
        </div>

        <PromotionTable promotions={pro} orderId={orderId} token={token} />
        <div className="price-container">
          <h2>ส่วนลดจากโปรโมชั่น : {discountLo} บาท</h2>
          <h2>ราคาสุทธิ : {netPaymentLo} บาท</h2>
        </div>
        {!date && <Button onClick={() => paid(orderId)}>จ่ายเงิน</Button>}
      </div>
    </React.Fragment>
  );
};

const Button = styled.button`
  padding: 10px 25px;
  border: none;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  background-image: linear-gradient(
    to right top,
    #051937,
    #004267,
    #006d76,
    #00965a,
    #87b51f
  );
  border-radius: 10px;
  font-family: "Mitr", sans-serif;
  margin: 20px;
  font-size: 18px;
  color: #fff;
  outline: none;
  cursor: pointer;
`;

const Input = styled.input`
  padding: 10px;
  width: 50%;
  font-size: 18px;
  border-radius: 10px;
  border: none;
  background-color: #f0f0f0;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  font-family: "Mitr", sans-serif;
  outline: none;
`;

export default Pay;
