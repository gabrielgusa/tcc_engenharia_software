import React from "react";
import { HeaderContainer } from "../styles/HeaderContainer";
import { MainMenu } from "./MainMenu";

export const Header = () => (
    <HeaderContainer>
        <h1>Find Food to Save</h1>
        
        <MainMenu />
    </HeaderContainer>
);