import axios from 'axios'


export default {

    getListOfScoresByTournamentId(tournamentId) {
        return axios.get(`/tournaments/${tournamentId}/scores`);
    },

    getScoreByBracketPositionTeamIdTournamentId(position, teamId, tournamentId) {
        return axios.get(`/tournaments/${tournamentId}/team/${teamId}/scores/${position}`);
    },

    initializeTournamentScores(tournamentId, listOfScores) {
        return axios.post(`/tournaments/${tournamentId}/initializeScores`, listOfScores);
    },

    updateScore(score) {
        return axios.put(`/tournaments/updateScore/${score.scoreId}`, score);
    },

    moveRoundsAndGetUpdatedListOfScores(tournamentId, round) {
        return axios.get(`/tournaments/${tournamentId}/team/${round}/update`);
    }



}