import axios from "axios";
import { storageService } from "../../shared/service/StorageService";
import { baseApi } from "../../shared/api/BaseApi";

const USERS_PATH = "api/v1/users";
const STORES_PATH = "api/v1/stores";

export const storeService = {

    async create(store){
        try {
            var idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores`,
                method: "POST",
                data: store,
                headers: {
                    "Authorization": "Bearer " + storageService.getToken()
                }
            });

            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async update(idStore, store){
        try {
            var idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}`,
                method: "PUT",
                data: store,
                headers: {
                    "Authorization": "Bearer " + storageService.getToken()
                }
            });

            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async deleteStore(idStore){
        try {
            var idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}`,
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + storageService.getToken()
                }
            });

            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async findByIdUser(){
        try {
            var idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores`,
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + storageService.getToken()
                }
            });

            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async findByAddress(search){
        try {
            var response = await axios({
                url: `${baseApi.BASE_URL}/${STORES_PATH}`,
                method: "GET",
                params: search,
                headers: {
                    "Authorization": "Bearer " + storageService.getToken()
                }
            });

            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    }

}

export default storeService;