import React from "react";
import "../App.css";
import { Link } from "react-router-dom";
import styled from "styled-components";

const PromotionTable = ({ promotions, orderId, token, setPro,setDisCountLo, setNetPaymentLo }) => {
  const tableStyle = {
    textAlign: "center",
    borderRadius: 5,
  };

  const removePromotion = async (orderId, proId) => {
    const url = "http://localhost:8080/promotion/remove";
    const body = {
      order_id: Number(orderId),
      promotion_id: Number(proId),
    };
    const response = await fetch(url, {
      method: "DELETE",
      body: JSON.stringify(body),
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    if (response.status === 200) {
      const json = await response.json();
      setPro(json.promotions)
      setDisCountLo(json.discount)
      setNetPaymentLo(json.netPayment)
      console.log(json, "Add Promotion");
    }
  };

  return (
    <table style={tableStyle}>
      <thead>
        <tr>
          <th>#</th>
          <th>รหัสโปรโมชั่น</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {promotions.map((pro, index) => (
          <tr key={index}>
            <td>{index + 1}</td>
            <td>{pro.promotion_id}</td>
            <td
              onClick={() => removePromotion(orderId, pro.promotion_id)}
              style={{ color: "red", cursor: "pointer" }}
            >
              ลบ
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default PromotionTable;
