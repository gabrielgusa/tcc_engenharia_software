import React, { useEffect, useState } from "react";
import { storageService } from "../service/StorageService";
import userService from "../../user/api/userService";

export const Home = () => {
    const [message, setMessage] = useState("")
    
    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200){
                setMessage("Bem vindo " + storageService.getEmail() + "!");
            } else {
                storageService.clear();
                setMessage("Bem vindo!");
            }
        }
        onLoad();
    }, []);

    return (
        <div>
            <h3>{message}</h3>
        </div>
    )
};