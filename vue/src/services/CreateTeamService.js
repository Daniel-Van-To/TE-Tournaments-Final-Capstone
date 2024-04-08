import axios from 'axios'

export default {

    createTeam(team){
        return axios.post('/create-team', team);
    }
}