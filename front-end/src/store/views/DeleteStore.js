import React, { useEffect, useState } from "react";
import storeService from "../api/StoreService";
import userService from "../../user/api/userService";
import { Link, useNavigate } from "react-router-dom";

export const DeleteStore = () => {
    const [error, setError] = useState("");
    const [message, setMessage] = useState("");
    
    const navigate = useNavigate();

    async function deleteStore() {
        let user = await userService.me();
        if(user.status === 200) {
            var store = await storeService.findByIdUser();
            if(store.status === 200) {
                var deleteResponse = await storeService.deleteStore(store.data.id);
                if(deleteResponse.status === 200) {
                    navigate("/");
                }
            }
            return store;
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
                var store = await storeService.findByIdUser();
                if(store.status === 200) {
                    setMessage("Deseja excluir o estabelecimento e todos produtos relacionados?");
                }
                if(store.status === 400) {
                    setError(store.data.message);
                }
            }
            if(user.status === 401){
                setError("Efetue login para prosseguir.");
            } 
        }
        onLoad();
    }, [error, message]);

    return (
        <div>
            {error && <p>{error}</p>}
            {!error && <p>{message} <Link onClick={() => deleteStore()}>Confirmar</Link></p>}
        </div>
    );
};