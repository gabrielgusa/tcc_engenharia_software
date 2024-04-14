
export const storageService = {

    setToken(token){
        localStorage.setItem("token", token);
    },
    
    setUser(user){
        localStorage.setItem("id", user.id);
        localStorage.setItem("name", user.name);
        localStorage.setItem("email", user.email);
    },

    getId(){
        return localStorage.getItem("id");
    },

    getEmail(){
        return localStorage.getItem("email");
    },

    getToken(){
        return localStorage.getItem("token");
    },

    setIsAutenticated(isAuthenticated){
        localStorage.setItem("isAuthenticated", isAuthenticated);
    },

    isAuthenticated(){
        return localStorage.getItem("isAuthenticated");
    },

    clear(){
        localStorage.clear();
    }
}

export default storageService;