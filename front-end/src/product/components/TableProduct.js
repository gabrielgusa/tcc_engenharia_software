import React from "react";
import { Link } from "react-router-dom";

export const TableProduct = ({products, openViewUpdate, remover, isOnlyView}) => {
    return (
        <table id="custom-table">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Data de validade</th>
                    <th>Quantidade</th>
                    {isOnlyView && <th>Ações</th>}
                </tr>
            </thead>
            <tbody>
            {products.length > 0 &&  
                products.map((product) => {
                    return (
                        <tr key={product.id}>
                            <td>{product.name}</td>
                            <td>{product.expirationDate}</td>
                            <td>{product.quantity}</td>
                            {isOnlyView &&
                                <td>
                                    <Link style={{textDecoration: 'none', color: 'blue', paddingRight: '40%'}} onClick={(e) => {e.preventDefault(); openViewUpdate(product.id)}}>atualizar</Link>
                                    <Link style={{textDecoration: 'none', color: 'blue'}}  onClick={() => remover(product.id)}>remover</Link>
                                </td>
                            }  
                        </tr>
                    );
                })
            }
            </tbody>
        </table>
    );
};