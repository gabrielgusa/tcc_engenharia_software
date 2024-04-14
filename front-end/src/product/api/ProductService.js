import { baseApi } from "../../shared/api/BaseApi";
import axios from "axios";
import { storageService } from "../../shared/service/StorageService";

const USERS_PATH = "api/v1/users";
const STORES_PATH = "api/v1/stores";

export const productService = {

    async create(product, idStore){
        try {
            const idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}/products`,
                method: "POST",
                data: product,
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

    async update(product, idProduct, idStore){
        try {
            const idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}/products/${idProduct}`,
                method: "PUT",
                data: product,
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

    async findById(idProduct, idStore){
        try {
            const idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}/products/${idProduct}`,
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

    async findAllByIdUserAndIdStore(idStore){
        try {
            const idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}/products`,
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

    async findAllByIdStore(idStore){
        try {
            var response = await axios({
                url: `${baseApi.BASE_URL}/${STORES_PATH}/${idStore}/products`,
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

    async delete(idProduct, idStore){
        try {
            const idUser = storageService.getId();

            var response = await axios({
                url: `${baseApi.BASE_URL}/${USERS_PATH}/${idUser}/stores/${idStore}/products/${idProduct}`,
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
    }

}

export default productService;