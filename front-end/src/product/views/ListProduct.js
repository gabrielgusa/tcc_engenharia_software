import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { TableContainer } from "../../shared/styles/TableContainer";
import { TableProduct } from "../components/TableProduct";
import storeService from "../../store/api/StoreService";
import productService from "../api/ProductService";
import userService from "../../user/api/userService";

export const ListProduct = () => {
    const [products, setProducts] = useState([]);
    const [error, setError] = useState("");
    
    const navigate = useNavigate();

    async function remover(id){
        const store = await storeService.findByIdUser();
        if(store.status === 200) {
            const res = await productService.delete(id, store.data.id);
            if(res.status === 200) {
                listProducts(store.data.id);
            }
            if(res.status === 401) {
                setError("Efetue login para prosseguir.");
            }
        }
        if(store.status === 401) {
            setError("Efetue login para prosseguir.");
        }
    }   

    async function listProducts(id) {
        const resProducts = await productService.findAllByIdUserAndIdStore(id);
        if(resProducts.status === 200) {
            setProducts(resProducts.data);
        }
    };

    function openViewUpdate(id){
        navigate("/update-product/" + id, );
    }

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200){
                const store = await storeService.findByIdUser();
                if(store.status === 200) {
                    await listProducts(store.data.id);
                }
                if(store.status === 400) {
                    setError(store.data.message);
                }
            } else {
                setError("Efetue login para prosseguir.");
            }
        }
        onLoad();
    }, [setProducts, setError]);
    
    return (
        <div>
            <p hidden={!error ? true : false}>{error}</p>
            <TableContainer hidden={error ? true : false}>
                <TableProduct products={products} openViewUpdate={openViewUpdate} remover={remover} isOnlyView={true}/>
            </TableContainer>
        </div>
    );
};