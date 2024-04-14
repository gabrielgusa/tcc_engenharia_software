import React, { useEffect, useState } from "react";
import { FormStore } from "../components/FormStore";
import { FormContainer } from "../../shared/styles/FormContainer";
import storeService from "../api/StoreService";
import userService from "../../user/api/userService";

export const UpdateStore = () => {
    const [store, setStore] = useState({});
    const [address, setAddress] = useState({});
    const [error, setError] = useState("");

    async function update(store) {
        let user = await userService.me();
        if(user.status === 200) {
            var storeResponse = await storeService.findByIdUser();
            if(storeResponse.status === 200) {
                return await storeService.update(storeResponse.data.id, store);
            }
            return storeResponse;
        }
        if(user.status === 401){
            setError("Efetue login para prosseguir.");
        }
        return user;
    }

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200) {
                var storeResponse = await storeService.findByIdUser();
                if(storeResponse.status === 200) {
                    setStore(storeResponse.data);
                    setAddress(storeResponse.data.address);
                }
                if(storeResponse.status === 400) {
                    setError(storeResponse.data.message);
                }
            }
            if(user.status === 401){
                setError("Efetue login para prosseguir.");
            } 
        }
        onLoad();
    }, [error]);

    return (
        <div>
            {error && <p>{error}</p>}
            <FormContainer hidden={error}>
                <FormStore action={update} store={store} address={address}/>
            </FormContainer>
        </div>
    );
};