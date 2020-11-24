import React, {useState, useEffect} from 'react';
import '../App.css';
import {useSelector} from 'react-redux'

const OrderTable = () =>{
    const [paymentData, setPaymentData] = useState([])
    const token = useSelector( (state) => state.athorize.id );
    const tableStyle= {
        textAlign: "center",
        borderRadius: 5,
    }

    useEffect(() =>{
        const ac = new AbortController();
        fetch('http://localhost:8080/paymentLog/', {
            method: 'GET',
            headers: new Headers({
                'Authorization': token, 
                'Content-Type': 'application/json'
            })
        })
        .then((res) => res.json())
        .then((json) =>{
            setPaymentData(json)
        })
        return () => ac.abort();
    }, [])

    return(
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th>#</th>
                    <th>วันดำเนินการ</th>
                    <th>ยอดค่าใช้จ่าย</th>
                    <th>รูปแบบการดำเนินการ</th>
                </tr>
            </thead>
            <tbody>          
               { paymentData.map((value => (
                <tr>
                    <td>{value.id}</td>
                    <td>{value.created_date}</td>
                    <td>{value.amount}</td>
                    <td>{value.type === "top up" ? "เติมเงิน" : "จ่าย"}</td>
                </tr>
               )))}
            </tbody>
        </table>
    );
}


export default OrderTable;
