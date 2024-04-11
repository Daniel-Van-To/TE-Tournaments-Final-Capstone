import axios from 'axios'

export default {

    createTeam(team){
        return axios.post('/create-team', team);
    },

    getTeams() {
        return axios.get('/teams');
    },

    getUsersOnTeam(teamId) {
        return axios.get(`/users/${teamId}`);
    },

    sendJoinRequest(request) {
        return axios.post(`/teams/${request.teamId}`, request);
    },

    isCurrentUserCaptain(teamId) {
        return axios.get(`/teams/${teamId}/captain-view`, this.$store.state.user.username);
    },

    getPendingJoinRequests(teamId) {
        return axios.get(`/teams/${teamId}/requests`);
    },
    

}