<template>
    <ul class="matchup">
        <li class="team team-top">{{firstTeamName}}
            <span class="score">{{firstPositionScore}}</span>
        </li>
        <li class="team team-bottom">{{secondTeamName}}
            <span class="score">{{secondPositionScore}}</span>
        </li>
    </ul>
</template>

<script>
import ScoreService from '../../services/ScoreService';

export default {

    props: ["tournamentId", "firstPosition", "firstTeam", "secondPosition", "secondTeam", "scores"],

    data() {

        return {
            firstPositionScore: "",
            secondPositionScore: "",
            firstTeamName: this.firstTeam.teamName,
            secondTeamName: this.secondTeam.teamName,
        }
    },

    methods: {
        returnsScoreOrEmptyStringGivenBracketPosition(position) {
            if (this.scores.length < position) {
                return '';
            }
            return this.scores[position-1];
        },

    },  
    beforeMount() {
        if (this.firstPosition > this.scores.length) {
            this.firstTeamName = '';
            this.secondTeamName = '';
        }

        else {
            const score1 = this.returnsScoreOrEmptyStringGivenBracketPosition(this.firstPosition)
            const score2 = this.returnsScoreOrEmptyStringGivenBracketPosition(this.secondPosition)
            
            this.firstPositionScore = score1.score;       
            this.secondPositionScore = score2.score;
        }



        //first team
        // ScoreService.getScoreByBracketPositionTeamIdTournamentId(this.firstPosition, this.firstTeam.teamId, this.tournamentId)
        //     .then(response => {
        //         if (response.status == 200){
        //             this.firstPositionScore = response.data;
        //         }
        //     })
        //     .catch(error => {
        //         this.$store.commit('SET_NOTIFICATION', 'error in TournamentMatchUp component getting firstpositionscore');
        //     });
        // ScoreService.getScoreByBracketPositionTeamIdTournamentId(this.secondPosition, this.secondTeam.teamId, this.tournamentId)
        // .then(response => {
        //         if (response.status == 200){
        //             this.secondPositionScore = response.data;
        //         }
        //     })
        //     .catch(error => {
        //         this.$store.commit('SET_NOTIFICATION', 'error in TournamentMatchUp component getting secondpositionscore');
        //     });
    }

}

</script>

<style scoped>

.matchup {
    margin: 0;
    width: 100%;
    padding: 10px 0;
    height: 60px;
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
    text-transform: uppercase;
    font-weight: bold;
    font-family: 'Roboto Condensed', sans-serif;
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