import React, {useEffect, useState} from 'react';
import '../App.css';

const ProductListTable = ({orderId, setPrice, price}) =>{
    const [data, setData] = useState([])

    useEffect(() =>{
        const ac = new AbortController();
        console.log(orderId)
        fetch(`https://ordermodule.herokuapp.com/getOrderDetails/${orderId}`, {
            method: 'GET',
            headers: new Headers({
                'Authorization': '1', 
                'Content-Type': 'application/json'
            })
        })
        .then((res) => res.json())
        .then((json) =>{
            console.log(json)
            setData(json.product)
            console.log(data)
        })
        return () => ac.abort();
    }, [])
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
                {data.map((value) => (
                    <tr key={value.product_id}>
                        <td>{value.product_id}</td>
                        <td>{value.productName}</td>
                        <td>{value.price} บาท</td>
                        <td>{value.amount} ชิ้น</td>
                        <td>{value.amount*value.price} บาท</td>
                    </tr>
                ))}

            </tbody>
        </table>
    );
}


export default ProductListTable;
