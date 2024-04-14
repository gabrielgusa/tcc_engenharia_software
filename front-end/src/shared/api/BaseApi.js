
const INTERNAL_ERROR_MESSAGE = "Internal Server Error.";

export const baseApi = {

    BASE_URL: process.env.REACT_APP_BASE_URL,

    errorHandler(err) {
        if("response" in err){
            return {
                status: err.response.status,
                data: err.response.data
            }
        } else {
            return {
                status: 500,
                data: {
                    message: INTERNAL_ERROR_MESSAGE
                }
            }
        }
    }

}

export default baseApi;