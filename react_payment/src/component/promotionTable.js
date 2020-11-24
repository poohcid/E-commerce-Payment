import React from 'react';
import '../App.css';
import { Link } from "react-router-dom"
import styled from 'styled-components';

const PromotionTable = ({promotion}) =>{
    const tableStyle= {
        textAlign: "center",
        borderRadius: 5,
    }
    return(
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th>#</th>
                    <th>รหัสโปรโมชั่น</th>
                </tr>
            </thead>
            <tbody>
                {/* {promotion.map(() =>{
                    <tr>
                        <td>1</td>
                        <td>Ryzen 5 5600X</td>
                    </tr>
                })}          */}

            </tbody>
        </table>
    );
}


export default PromotionTable;
