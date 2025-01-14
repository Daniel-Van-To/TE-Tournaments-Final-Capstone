import axios from 'axios'


export default {

    submitUpdatedRequest(request){
        // request: {
            // requestId: ,
            // tournamentId: ,
            // teamId: ,
            // gameName: ,
            // requestStatus: ,
            // requesterId: ,
        // }



        return axios.put(`/teams/${request.teamId}/requests/${request.requestId}`, request)
    },

    submitUpdatedTournamentRequest(request) {
        return axios.put(`/tournaments/${request.tournamentId}/requests/${request.requestId}`, request)
    },

    submitTournamentJoinRequestForTeam(request) {
        return axios.post(`/tournaments/${request.tournamentId}`, request);
    }
}
