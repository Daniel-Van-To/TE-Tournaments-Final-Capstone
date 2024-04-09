import axios from 'axios'

export default {

    createTeam(team){
        return axios.post('/create-team', team);
    },

    getTeams() {
        return axios.get('/teams');
    },

    getTeam(teamId) {
        return axios.get(`/teams/${teamId}`);
    }
}