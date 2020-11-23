import React,{ useState} from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import ProductTable from "../component/productListTable"
import PromotionTable from "../component/promotionTable"
import { useParams } from "react-router-dom"
import { Link, useLocation } from "react-router-dom"

const Pay = () =>{
    const location = useLocation()
    const { orderId } = useParams();
    const {netPayment, taltalPrice, discount, promotions} = location.state

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
                </div>
            </div>
        </React.Fragment>
    );
}

export default Pay;