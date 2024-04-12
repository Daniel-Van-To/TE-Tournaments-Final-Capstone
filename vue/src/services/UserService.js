import axios from 'axios'

export default {

    getUser(id) {
        return axios.get(`/users/${id}`);
    },

}