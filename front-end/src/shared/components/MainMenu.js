import React from "react";
import { MainMenuContainer } from "../styles/MainMenuContainer";
import { Link } from "react-router-dom";

export const MainMenu = ({isAuthenticated}) => {
    return (
        <MainMenuContainer>

            <div className="navbar">
                <Link to="/">Home</Link>
                <Link to="/signup">Cadastrar</Link>
                <Link to="/signin">Login</Link>
                <div className="dropdown">
                    <button className="dropbtn">Estabelecimento</button>
                    <div className="dropdown-content">
                        <Link to="/create-store">Cadastrar</Link>
                        <Link to="/update-store">Atualizar</Link>
                        <Link to="/search-store">Procurar</Link>
                        <Link to="/delete-store">Excluir</Link>
                    </div>
                </div>
                <div className="dropdown">
                    <button className="dropbtn">Produto</button>
                    <div className="dropdown-content">
                        <Link to="/create-product">Cadastrar</Link>
                        <Link to="/list-product">Listar</Link>
                    </div>
                </div>
            </div>

        </MainMenuContainer>
    );
};