import React from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import UnpaidTable from "../component/unpaidTable"
const Unpaid = () =>{
    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <h1>ออเดอร์ที่ค้างชำระ</h1>
                <UnpaidTable/>
            </div>
        </React.Fragment>
    );
}

export default Unpaid;