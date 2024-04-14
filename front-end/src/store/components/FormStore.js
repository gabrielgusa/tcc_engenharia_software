import React, { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const FormStore = ({action, store, address}) => {
    const [formStore, setFormStore] = useState({});
    const [formAddress, setFormAddress] = useState({});
    
    const [errors, setErrors] = useState([]);
    const [error, setError] = useState({});

    const navigate = useNavigate();

    const handleStoreChange = (event) => {
        const { name, value } = event.target;
        setFormStore((prev) => ({ ...prev, [name]: value }));
    };

    const handleAddressChange = (event) => {
        const { name, value } = event.target;
        setFormAddress((prev) => ({ ...prev, [name]: value }));
    };
    
    const handleSubmit = async (event) => {
        event.preventDefault();
        var res = await action({
            ...formStore,
            address: formAddress
        });
        if(res.status === 200) {
            navigate("/search-store")
        } else {
            if("errors" in res.data) {
                setErrors(res.data.errors);
            } else {
                setError(res.data);
            }
        }
    };

    useEffect(() => {
        setFormStore(store);
        setFormAddress(address);
    }, [setFormStore, store, setFormAddress, address]);

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
                        <input type="text" id="name" name="name" value={formStore.name} onChange={handleStoreChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="timeOpen">Horario Abertura:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="time" id="timeOpen" name="timeOpen" value={formStore.timeOpen} onChange={handleStoreChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="timeClose">Horário Fechamento:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="time" id="timeClose" name="timeClose" value={formStore.timeClose} onChange={handleStoreChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="state">Estado:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="state" name="state" maxLength={2} value={formAddress.state} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="city">Cidade:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="city" name="city" value={formAddress.city} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="district">Bairro:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="district" name="district" value={formAddress.district} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="street">Rua:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="street" name="street" value={formAddress.street} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="number">Número:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="number" name="number" value={formAddress.number} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="complement">Complemento:</label><br/>
                    </div>
                    <div className="col-75">
                        <input type="text" id="complement" name="complement" value={formAddress.complement} onChange={handleAddressChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <button type="submit">Confirmar</button>
                </div>
            </form>
        </div>
    );
};