import axios from "axios";

const setAuthHeader = () => {
    let token = localStorage.getItem('token');
    if (token) {
        axios.defaults.headers = {
            Authorization: 'Bearer ' + token
        };
    } else {
        delete axios.defaults.headers.Authorization;
    }
};

export default setAuthHeader;