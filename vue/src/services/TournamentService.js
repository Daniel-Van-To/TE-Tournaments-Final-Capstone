import axios from 'axios'


export default {

    createTournament(tournament) {
        return axios.post(`/create-tournament`, tournament)
    },

    getAllTournaments() {
        return axios.get(`/browse-tournaments`)
    }
}
