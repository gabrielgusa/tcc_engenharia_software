import React from "react";
import { FormSignUp } from "../components/FormSignUp";
import { FormContainer } from "../../shared/styles/FormContainer";
import userService from "../api/userService";

export const SignUp = () => {
    
    return (
        <FormContainer>
            <FormSignUp action={userService.signup}/>
        </FormContainer>
    );
};