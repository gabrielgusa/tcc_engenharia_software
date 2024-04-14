import React from "react";

export const SearchStoreForm = ({formData, handleChange, error, errors, children}) => {
    
    return (
        <div className="container">
            {"message" in error && <p>{error.message}</p>}
            {errors.length > 0 && errors.map(e => <p key={e.message}>{e.message}</p>)}
            <form id="form-search-store">
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="state">Estado:</label><br/>
                    </div>
                    <div className="col-25">
                        <input type="text" id="state" name="state" value={formData.state} onChange={handleChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="city">Cidade:</label><br/>
                    </div>
                    <div className="col-25">
                        <input type="text" id="city" name="city" value={formData.city} onChange={handleChange}/><br/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-25">
                        <label htmlFor="district">Bairro:</label><br/>
                    </div>
                    <div className="col-25">
                        <input type="text" id="district" name="district" value={formData.district} onChange={handleChange}/><br/>
                    </div>
                </div>

                {children}
            </form>
        </div>
    );
};