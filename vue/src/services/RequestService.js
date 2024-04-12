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
}
