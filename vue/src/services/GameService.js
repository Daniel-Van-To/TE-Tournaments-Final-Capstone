import axios from 'axios'


export default {
    getGamesList() {
        return axios.get(`/games-list`);
    }
}
