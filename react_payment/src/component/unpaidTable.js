import React from 'react';
import '../App.css';
import { Link } from "react-router-dom"
import styled from 'styled-components';

const OrderTable = () =>{
    const tableStyle= {
        textAlign: "center",
        borderRadius: 5,
    }
    return(
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th>#</th>
                    <th>ชื่อออเดอร์</th>
                    <th>ราคารวม</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>          
                <tr>
                    <td>1</td>
                    <td>order1</td>
                    <td>50 บาท</td>
                    <td><Link to="/Pay" className="nav-link">ชำระเงิน</Link></td>
                </tr>
            </tbody>
        </table>
    );
}


export default OrderTable;
