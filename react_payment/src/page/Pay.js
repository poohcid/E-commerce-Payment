import React,{ useState} from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import ProductTable from "../component/productListTable"
import PromotionTable from "../component/promotionTable"
import { useParams } from "react-router-dom"


const Pay = () =>{

    const { orderId } = useParams();



    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <h1>รายการสินค้า {orderId}</h1>
                <ProductTable/>
                <div className="price-container">
                    <h2>ราคาทั้งหมด : 5 บาท</h2>
                </div>
                <h1>โปรโมชั่น</h1>
                <PromotionTable/>
                <div className="price-container">
                    <h2>ราคาทั้งหมดรวมโปรโมชั่น : 5 บาท</h2>
                </div>
            </div>
        </React.Fragment>
    );
}

export default Pay;