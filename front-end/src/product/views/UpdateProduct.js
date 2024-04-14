import React, { useEffect } from "react";
import { useState } from "react";
import { useParams } from 'react-router';
import { FormContainer } from "../../shared/styles/FormContainer";
import { FormProduct } from "../components/FormProduct";
import storeService from "../../store/api/StoreService";
import productService from "../api/ProductService";
import userService from "../../user/api/userService";

export const UpdateProduct = () => {
    const [product, setProduct] = useState({});
    const [error, setError] = useState("");

    let { id } = useParams();
    
    async function update(product) {
        var store = await storeService.findByIdUser();
        if(store.status === 200){
            return await productService.update(product, id, store.data.id);
        } 
        if(store.status === 401){
            setError("Efetue login para prosseguir.");
        }
    };
    
    async function findById(id) {
        var store = await storeService.findByIdUser();
        if(store.status === 200){
            var res = await productService.findById(id, store.data.id);
            if(res.status === 200) {
                setProduct(res.data);
            }
        } 
        if(store.status === 401){
            setError("Efetue login para prosseguir.");
        }
    }

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200){
                var store = await storeService.findByIdUser();
                if(store.status === 400){
                    setError(store.data.message);
                }
            } 
            if(user.status === 401){
                setError("Efetue login para prosseguir.");
            }
        }
        onLoad();
        findById(id)
    }, [id]);

    return (
        <div>
            {error && <p>{error}</p>}
            <FormContainer hidden={error}>
                <FormProduct action={update} product={{...product}} buttonText={"Atualizar"}/>
            </FormContainer>
        </div>
    );
};