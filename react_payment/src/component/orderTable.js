import React, {useEffect, useState} from 'react';
import '../App.css';
import { Link,useLocation } from "react-router-dom"
const OrderTable = () =>{
    const [data, setData] = useState([]) 
    const location = useLocation()
    const tableStyle= {
        textAlign: "center",
        borderRadius: 5,
    }
    
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
        if(location.pathname === "/"){
            if(value.created_date === null){
                return (
                    <tr key={value.id}>
                        <td>{value.id}</td>
                        <td>{value.order_id}</td>
                        <td><Link to={`/Pay/${value.order_id}`} className="nav-link">ชำระเงิน</Link></td>
                    </tr>
                ) 
            }
        }
        else{
            if(value.created_date !== null){
                return (
                    <tr key={value.id}>
                        <td>{value.id}</td>
                        <td>{value.order_id}</td>
                        <td><Link to={`/Pay/${value.order_id}`} className="nav-link">ดู</Link></td>
                    </tr>
                ) 
            }

        }
        
    }
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
                {data.map((value) => renderData(value))}   
            </tbody>
        </table>
    );
}


export default OrderTable;
