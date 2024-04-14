import React from "react";
import { FormSignIn } from "../components/FormSignIn";
import { FormContainer } from "../../shared/styles/FormContainer";
import userService from "../api/userService";
import storageService from "../../shared/service/StorageService";

export const SignIn = () => {
    
    async function signin(user) {
        var res = await userService.signin(user);
        if(res.status === 200) {
            storageService.setToken(res.data.token);
            storageService.setIsAutenticated(true);
            var me = await userService.me();
            if(me.status === 200) {
                storageService.setUser(me.data);
            }
            return me;
        }
        return res;
    };

    return (
        <FormContainer>
            <FormSignIn action={signin}/>
        </FormContainer>
    );
};