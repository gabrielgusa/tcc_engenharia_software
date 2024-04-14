import React from "react";
import { useState, useEffect } from "react";
import { TableContainer } from "../../shared/styles/TableContainer";
import { TableProduct } from "../components/TableProduct";
import productService from "../api/ProductService";
import { useParams } from "react-router-dom";

export const SearchProduct = () => {
    const [products, setProducts] = useState([]);
    
    let { id } = useParams();

    useEffect(() => {
        async function listProducts() {
            const products = await productService.findAllByIdStore(id);
            if(products.status === 200) {
                setProducts(products.data);
            }
        };

        listProducts();
    }, [id]);
    
    return (
        <div>
            <TableContainer>
                <TableProduct products={products} isOnlyView={false}/>
            </TableContainer>
        </div>
    );
};