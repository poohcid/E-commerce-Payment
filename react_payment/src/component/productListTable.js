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
                    <th>ชื่อสินค้า</th>
                    <th>ราคา</th>
                    <th>จำนวน</th>
                    <th>ราคารวม</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>          
                <tr>
                    <td>1</td>
                    <td>Ryzen 5 5600X</td>
                    <td>9200</td>
                    <td>1</td>
                    <td>9200</td>
                </tr>
            </tbody>
        </table>
    );
}


export default OrderTable;
