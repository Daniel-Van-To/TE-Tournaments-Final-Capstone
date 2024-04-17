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
    },
    getPendingTournamentJoinRequests(tournamentId) {
        return axios.get(`/tournaments/${tournamentId}/requests`)
    },
    sendTournamentJoinRequest (request) {
        // request: {
            // requestId: ,
            // tournamentId: ,
            // teamId: ,
            // gameName: ,
            // requestStatus: ,
            // requesterId: ,
        // }
        return axios.put(`/tournament/${request.tournamentId}`, request)
    },

    updateTournament(tournament) {
        //required fields:
            //tournament_name
            //entry_fee
            //accepting_teams
            //tournament_status
            //maximum_participants

        return axios.put(`/tournaments/${tournament.tournamentId}/update`, tournament)
    }
    
}
