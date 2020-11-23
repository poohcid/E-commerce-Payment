import React,{ useState } from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import PaymentLogTable from "../component/paymentLogTable"
import { useParams } from "react-router-dom"

const PaymentLog = () =>{
    const { userId } = useParams();
    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <h1>ประวัติการจ่ายเงิน {userId}</h1>
                <PaymentLogTable />
            </div>
        </React.Fragment>
    );
}

export default PaymentLog;