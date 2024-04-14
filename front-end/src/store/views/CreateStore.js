import React, { useEffect, useState } from "react";
import { FormStore } from "../components/FormStore";
import { FormContainer } from "../../shared/styles/FormContainer";
import storeService from "../api/StoreService";
import userService from "../../user/api/userService";

export const CreateStore = () => {
    const [error, setError] = useState("");

    async function create(store) {
        let user = await userService.me();
        if(user.status === 200) {
            return await storeService.create(store);
        }
        if(user.status === 401){
            setError("Efetue login para prosseguir.");
        }
        return user;
    }

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 401){
                setError("Efetue login para prosseguir.");
            } 
        }
        onLoad();
    });

    return (
        <div>
            {error && <p>{error}</p>}
            <FormContainer hidden={error}>
                <FormStore action={create} store={{name: "", timeOpen: "00:00", timeClose: "00:00"}} address={{state: "", city: "", district: "", street: "", number: "", complement: ""}}/>
            </FormContainer>
        </div>
    );
};