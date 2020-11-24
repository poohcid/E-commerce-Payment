import React,{ useState} from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import ProductTable from "../component/productListTable"
import PromotionTable from "../component/promotionTable"
import { useParams } from "react-router-dom"
import { Link, useLocation } from "react-router-dom"
import { useSelector } from "react-redux";
import styled from "styled-components"

const Pay = () =>{
    const location = useLocation()
    const { orderId } = useParams();
    const {netPayment, taltalPrice, discount, promotions, date} = location.state
    const token = useSelector( (state) => state.athorize.id );
    console.log(date)
    const paid = async (orderId) =>{
        const url = 'http://localhost:8080/pay'
        const body = {order_id:orderId}
        const response = await fetch(url, {
            method: 'POST',
            body: JSON.stringify(body),
            headers:{
                'Authorization': token, 
                'Content-Type': 'application/json'
            }
        })
        if(response.status === 200 ){
            const json = await response.json()
            console.log(json, "PAID")
        }
    }

    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <h1>รายการสินค้า</h1>
                <ProductTable orderId={orderId}/>
                <div className="price-container">
                    <h2>ราคาทั้งหมด : {taltalPrice} บาท</h2>
                </div>
                
                <h1>โปรโมชั่น</h1>
                <PromotionTable promotions={promotions}/>
                <div className="price-container">
                    <h2>ส่วนลดจากโปรโมชั่น : {discount} บาท</h2>
                    <h2>ราคาสุทธิ : {netPayment} บาท</h2>
                    {!date && <Button onClick={() => paid(orderId)}>จ่ายเงิน</Button>}
                </div>
                
            </div>
        </React.Fragment>
    );
}


const Button = styled.button`
    padding: 10px 25px;
    border: none;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    background-image: linear-gradient(to right top, #051937, #004267, #006d76, #00965a, #87b51f);
    border-radius: 10px;
    font-family: 'Mitr', sans-serif;
    margin: 20px;
    font-size: 18px;
    color: #FFF;
    outline: none;
    cursor: pointer;
`
export default Pay;