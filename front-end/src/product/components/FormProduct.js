import React, { useEffect } from "react";
import { useState,  } from "react";
import { useNavigate } from "react-router-dom";

export const FormProduct = ({action, product, buttonText}) => {
    const [formData, setFormData] = useState({...product});
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
            navigate("/list-product")
        } else {
            if("errors" in res.data) {
                setErrors(res.data.errors);
            } else {
                setError(res.data);
            }
        }
    };

    useEffect(() => {
        setFormData(product);
    }, [setFormData, product]);

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
                        <label htmlFor="expirationDate">Data de Validade:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="date" id="expirationDate" name="expirationDate" value={formData.expirationDate} onChange={handleChange}/><br/>
                    </div>
                </div>
                
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="quantity">Quantidade:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="quantity" name="quantity" value={formData.quantity} onChange={handleChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <button type="submit">{buttonText}</button>
                </div>
            </form>
        </div>
    );
};