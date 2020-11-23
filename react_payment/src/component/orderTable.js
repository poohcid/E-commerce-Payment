import React, {useEffect} from 'react';
import '../App.css';
import { Link } from "react-router-dom"
import styled from 'styled-components';

const OrderTable = () =>{
    const tableStyle= {
        textAlign: "center",
        borderRadius: 5,
    }

    useEffect(() =>{
        fetch('http://localhost:8080/receive', {
            method: 'GET',
            headers: new Headers({
                'Authorization': '1', 
                'Content-Type': 'application/json'
            })
        })
        .then((res) => res.json())
        .then((json) =>{
            console.log(json)
        })
    }, [])

    return(
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th>#</th>
                    <th>หมายเลขออเดอร์</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>          
                <tr>
                    <td>1</td>
                    <td>order1</td>
                    <td><Link to="/Pay" className="nav-link">ชำระเงิน</Link></td>
                </tr>
            </tbody>
        </table>
    );
}


export default OrderTable;
