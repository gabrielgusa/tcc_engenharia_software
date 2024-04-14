import React, { useEffect, useState } from "react";
import { FormContainer } from "../../shared/styles/FormContainer";
import { FormProduct } from "../components/FormProduct";
import storeService from "../../store/api/StoreService";
import productService from "../api/ProductService";
import userService from "../../user/api/userService";

export const CreateProduct = () => {
    const [error, setError] = useState("");

    async function create(product) {
        var store = await storeService.findByIdUser();
        if(store.status === 200){
            return await productService.create(product, store.data.id);
        }
        if(store.status === 401){
            setError("Efetue login para prosseguir.");
        }
    };

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200){
                var store = await storeService.findByIdUser();
                if(store.status === 400){
                    setError(store.data.message);
                }
            }
            if(user.status === 401) {
                setError("Efetue login para prosseguir.");
            }
        }
        onLoad();
    });

    return (
        <div>
            {error && <p>{error}</p>}
            <FormContainer hidden={error}>
                <FormProduct action={create} product={{name: "", expirationDate: "", quantity: ""}} buttonText={"Cadastrar"}/>
            </FormContainer>
        </div>
    );
};