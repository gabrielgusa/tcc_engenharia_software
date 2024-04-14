import React, { useEffect } from "react";
import { useState } from "react";
import { TableStore } from "../components/TableStore";
import { TableContainer } from "../../shared/styles/TableContainer";
import { FormContainer } from "../../shared/styles/FormContainer";
import { SearchStoreForm } from "../components/SearchStoreForm";
import storeService from "../api/StoreService";
import { useNavigate } from "react-router-dom";
import userService from "../../user/api/userService";

export const SearchStore = () => {
    const [formData, setFormData] = useState({state: "", city: "", district: ""});
    const [stores, setStores] = useState([]);
    const [errors, setErrors] = useState([]);
    const [error, setError] = useState({});
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [unauthorizedMsg, setUnauthorizedMsg] = useState("");

    const navigate = useNavigate();  

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        var res = await storeService.findByAddress(formData);
        console.log(res);
        if(res.status === 200) {
            setStores(res.data);
        } else if(res.status === 401) {
            setIsAuthenticated(false)
            setUnauthorizedMsg("Efetue login para prosseguir.");
        } else {
            if("errors" in res.data) {
                setErrors(res.data.errors);
            } else {
                setError(res.data);
            }
        }
    };

    function openSearchProducts(id){
        navigate("/search-product/" + id);
    }

    useEffect(() => {
        async function onLoad(){
            let user = await userService.me();
            if(user.status === 200) {
                setIsAuthenticated(true);
            }
            if(user.status === 401) {
                setUnauthorizedMsg("Efetue login para prosseguir.");
            }
        }
        onLoad();
    }, [isAuthenticated, unauthorizedMsg]);

    return (
        <div>
            {unauthorizedMsg && <p>{unauthorizedMsg}</p>}
            {isAuthenticated &&
                <div>
                    <FormContainer>
                        <SearchStoreForm formData={formData} handleChange={handleChange} error={error} errors={errors}>
                            <div className="row">
                                <button style={{float: 'left'}} type="submit" onClick={handleSubmit} form="form-search-store">Procurar</button>
                            </div>
                        </SearchStoreForm>
                    </FormContainer>

                    <TableContainer>
                        <TableStore stores={stores} openSearchProducts={openSearchProducts}/>
                    </TableContainer>
                </div>
            }
        </div>
    );
};