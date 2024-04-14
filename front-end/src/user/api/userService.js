import { baseApi } from "../../shared/api/BaseApi";
import axios from "axios";
import { storageService } from "../../shared/service/StorageService";

const SIGNUP_PATH = "api/v1/users";
const SIGNIN_PATH = "api/v1/login";
const ME_PATH = "api/v1/users/me";

export const userService = {

    async signup(user) {
        try {
            var response = await axios({
                url: `${baseApi.BASE_URL}/${SIGNUP_PATH}`,
                method: "POST",
                data: user
            });
            
            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async signin(user) {
        try {
            const response = await axios({
                url: `${baseApi.BASE_URL}/${SIGNIN_PATH}`,
                method: "POST",
                data: user
            })
            
            return {
                status: response.status,
                data: response.data
            }
        } catch(err) {
            return baseApi.errorHandler(err);
        }
    },

    async me(){
        try {
            const token = storageService.getToken();
            
            var response = await axios({
                url: `${baseApi.BASE_URL}/${ME_PATH}`,
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + token
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

export default userService;