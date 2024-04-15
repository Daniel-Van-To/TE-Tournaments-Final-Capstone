import axios from 'axios'


export default {

    createTournament(tournament) {
        return axios.post(`/create-tournament`, tournament)
    },

    getAllTournaments() {
        return axios.get(`/browse-tournaments`)
    },

    getTournamentDetail(tournamentId) {
        return axios.get(`/browse-tournaments/${tournamentId}`)
    },

    checkUserForTournamentHost(tournamentId, userId) {
        return axios.get(`/browse-tournaments/${tournamentId}/host/${userId}`)
    }
}
