import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const FormSignUp = ({action}) => {
    const [formData, setFormData] = useState({name: "", email: "", password: ""});
    const [errors, setErrors] = useState([]);
    const [error, setError] = useState({});

    const navigate = useNavigate();
    
    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
    };
    
    const handleSubmit = async (event) => {
        event.preventDefault();
        var res = await action(formData);
        if(res.status === 200) {
            navigate("/signin")
        } else {
            if("errors" in res.data) {
                setErrors(res.data.errors);
            } else {
                setError(res.data);
            }
        }
    };

    return (
        <div className="container">
            {"message" in error && <p>{error.message}</p>}
            {errors.length > 0 && errors.map(e => <p key={e.message}>{e.message}</p>)}
            <form onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="name">Nome:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="name" name="name" value={formData.name} onChange={handleChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="email">Email:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="email" id="email" name="email" value={formData.email} onChange={handleChange}/><br/>
                    </div>
                </div>
                
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="password">Password:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="password" id="password" name="password" value={formData.password} onChange={handleChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <button type="submit">Cadastrar</button>
                </div>
            </form>
        </div>
    );
};