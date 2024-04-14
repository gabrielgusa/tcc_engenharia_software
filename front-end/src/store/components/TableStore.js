import React from "react";
import { Link } from "react-router-dom";

export const TableStore = ({stores, openSearchProducts}) => {
    return (
        <table id="custom-table">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Horário Funcionamento</th>
                    <th>Estado</th>
                    <th>Cidade</th>
                    <th>Bairro</th>
                </tr>
            </thead>
            <tbody>
            {stores.map((store) => {
                return (
                    <tr key={store.id}>
                        <td><Link style={{textDecoration: 'none', color: 'blue', paddingRight: '40%'}} onClick={(e) => {e.preventDefault(); openSearchProducts(store.id)}}>{store.name}</Link></td>
                        <td>{store.timeOpen} - {store.timeClose}</td>
                        <td>{store.address.state}</td>
                        <td>{store.address.city}</td>
                        <td>{store.address.district}</td>
                    </tr>
                );
            })}
            </tbody>
        </table>
    );
};