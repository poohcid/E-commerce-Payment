import React from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import OrderTable from "../component/orderTable"
const Record = () =>{
    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <h1>ประวัติการสั่งซื้อ</h1>
                <OrderTable/>
            </div>
        </React.Fragment>
    );
}

export default Record;