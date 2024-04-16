import axios from 'axios'


export default {

    getListOfScoresByTournamentId(tournamentId) {
        return axios.get(`/tournaments/${tournamentId}/scores`);
    },

    // getScoreByBracketPosition()

}