import React, {useEffect, useState} from 'react';
import '../App.css';
import { Link } from "react-router-dom"
import styled from 'styled-components';

const OrderTable = () =>{
    const [data, setData] = useState([]) 
    useEffect(() =>{
        const ac = new AbortController();
        fetch('http://localhost:8080/receive', {
            method: 'GET',
            headers: new Headers({
                'Authorization': '1', 
                'Content-Type': 'application/json'
            })
        })
        .then((res) => res.json())
        .then((json) =>{
            setData(json)
        })
        return () => ac.abort();
    }, [])
    const renderData = (value) =>{
        if(value.created_date === null){
            return (
                <tr key={value.id}>
                    <td>{value.id}</td>
                    <td>{value.order_id}</td>
                    <td>{value.netPayment}</td>
                    <td><Link to={{
                        pathname: `/Pay/${value.order_id}`,
                        state:{
                            taltalPrice: value.taltalPrice,
                            netPayment: value.netPayment,
                            discount: value.discount,
                            promotions: value.promotions
                        }
                    }} 
                    className="nav-link">ชำระเงิน</Link></td>
                </tr>
            ) 
        }
    }

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
                {data.map((value) => renderData(value))}          
            </tbody>
        </table>
    );
}


export default OrderTable;
