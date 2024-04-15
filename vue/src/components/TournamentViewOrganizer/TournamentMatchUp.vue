<template>
    <ul class="matchup">
        <li class="team team-top">{{firstTeam.teamName}}
            <span class="score">{{firstPositionScore.score}}</span>
        </li>
        <li class="team team-bottom">{{secondTeam.teamName}}
            <span class="score">{{secondPositionScore.score}}</span>
        </li>
    </ul>
</template>

<script>
import ScoreService from '../../services/ScoreService';

export default {

    props: ["tournamentId", "firstPosition", "firstTeam", "secondPosition", "secondTeam"],

    data() {

        return {
            firstPositionScore: "",
            secondPositionScore: ""
        }
    },
    created() {
        //first team
        ScoreService.getScoreByBracketPositionTeamIdTournamentId(this.firstPosition, this.firstTeam.teamId, this.tournamentId)
            .then(response => {
                if (response.status == 200){
                    this.firstPositionScore = response.data;
                }
            })
            .catch(error => {
                this.$store.commit('SET_NOTIFICATION', 'error in TournamentMatchUp component getting firstpositionscore');
            });
        ScoreService.getScoreByBracketPositionTeamIdTournamentId(this.secondPosition, this.secondTeam.teamId, this.tournamentId)
        .then(response => {
                if (response.status == 200){
                    this.secondPositionScore = response.data;
                }
            })
            .catch(error => {
                this.$store.commit('SET_NOTIFICATION', 'error in TournamentMatchUp component getting secondpositionscore');
            });
    }

}

</script>

<style scoped>

.matchup {
    margin: 0;
    width: 100%;
    padding: 10px 0;
    height: 60px;
    /* -webkit-transition: all 0.2s;
    transition: all 0.2s; */
}

.score {
    font-size: 11px;
    text-transform: uppercase;
    float: right;
    color: #2C7399;
    font-weight: bold;
    font-family: 'Roboto Condensed', sans-serif;
    position: absolute;
    right: 5px;
}

.team {
    padding: 0 5px;
    margin: 3px 0;
    height: 25px;
    line-height: 25px;
    white-space: nowrap;
    overflow: hidden;
    position: relative;
}

.round-two .matchup {
    margin: 0;
    height: 60px;
    padding: 50px 0;
}

.round-three .matchup {
    margin: 0;
    height: 60px;
    padding: 130px 0;
}

</style>