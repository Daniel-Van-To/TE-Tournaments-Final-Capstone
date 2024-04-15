import axios from 'axios'


export default {

    createTeam(team){
        return axios.post('/create-team', team);
    },

    getTeams() {
        return axios.get('/teams');
    },

    getTeam(teamId) {
        return axios.get(`/teams/${teamId}`)
    },

    getUsersOnTeam(teamId) {
        return axios.get(`/users/${teamId}/list`);
    },

    sendJoinRequest(request) {
        return axios.post(`/teams/${request.teamId}`, request);
    },

    isCurrentUserCaptain(teamId, userId) {
        return axios.get(`/teams/${teamId}/captain/${userId}`);
    },

    getPendingJoinRequests(teamId) {
        return axios.get(`/teams/${teamId}/requests`);
    },

    // getMyTeams(userId) {
    //     console.log(`inside the retriever getMyTeams() and here is what we're about to pass it:`);
    //     return axios.get(`/teams/${userId}/captain`);
    // },

    // returnTeamsAndHandleErrors(userId) {
    //     console.log(`inside teamService. see me?`);

    //     this.getMyTeams(userId)
    //     .then(response => {
    //         console.log(JSON.stringify(response.data));
    //         return response.data;
    //     })
    //     .catch(error => {
    //         console.log('error');
    //         if(error.response) {
    //             console.log(`error fetching teams user is captain of, but we got some data: ${error.response.data}`)
    //         }
    //         else if (error.request) {
    //             console.log(`error fetching teams user is captain of. no data, heres the request: ${error.request}`)
    //         }
    //         else {
    //             console.log(`error fetching teams user is captain of. no data, no request. heres the message: ${error.message}`)
    //         }
    //     })
    // }
    

}