<template>
    <ul v-if="edit" class="matchup">
        <li class="team team-top">{{ firstTeamName }}
            <span class="score"><input :disabled="inputDisabledFirst" @focusout="this.updateScoreInServer(1)" v-model="firstPositionScore" type="text" /></span>
        </li>
        <li class="team team-bottom">{{ secondTeamName }}
            <span class="score"><input :disabled="inputDisabledSecond" @focusout="this.updateScoreInServer(2)" v-model="secondPositionScore" type="text" /></span>
        </li>
    </ul>
    <ul v-else-if="this.tournament.tournamentStatus == 's'" class="matchup">
        <li class="team team-top">{{ this.round == 1 ? this.firstPosition : '' }}
            <span class="score"></span>
        </li>
        <li class="team team-bottom">{{ this.round == 1 ? this.secondPosition : '' }}
            <span class="score"></span>
        </li>
    </ul>
    <ul v-else class="matchup">
        <li class="team team-top">{{ firstTeamName  }}
            <span class="score">{{ firstPositionScore }}</span>
        </li>
        <li class="team team-bottom">{{ secondTeamName }}
            <span class="score">{{ secondPositionScore }}</span>
        </li>
    </ul>
</template>

<script>
import ScoreService from '../../services/ScoreService';

export default {

    computed: {

        inputDisabledFirst() {
            if (this.firstTeamName === '') {
                return true;
            }
            return false;
        },

        inputDisabledSecond() {
            if (this.secondTeamName === '') {
                return true;
            }
            return false;
        }
    },      

    props: [
        "tournament",
        "firstPosition",
        "firstTeam",
        "secondPosition",
        "secondTeam",
        "scores",
        "edit",
        "round",
    ],

    data() {

        return {
            firstPositionScore: "",
            secondPositionScore: "",
            firstTeamName: '',
            secondTeamName: '',
        }
    },

    methods: {

        updateScoreInServer(num) {
            let score = {
                tournamentId: this.tournament.tournamentId,
            }

            if (num == 1) {
                score.teamId = this.firstTeam.teamId;
                score.bracketPosition = this.firstPosition;
                score.score = this.firstPositionScore;
                ScoreService.addScore(score)
                    .then(response => {
                        if (response.status == 201) {
                            this.firstPositionScore = response.data.score;
                        }
                    })
                    .catch(error => {
                        this.$store.commit('SET_NOTIFICATION', 
                        `error setting score 1 for bracket position ${this.firstPosition}. 
                        error: ` + error.message);
                    })
            }
            else {
                score.teamId = this.secondTeam.teamId;
                score.bracketPosition = this.secondPosition;
                score.score = this.secondPositionScore;
                ScoreService.addScore(score)
                    .then(response => {
                        if (response.status == 201) {
                            this.secondPositionScore = response.data.score;
                        }
                    })
                    .catch(error => {
                        this.$store.commit('SET_NOTIFICATION', 
                        `error setting score for bracket position ${this.secondPosition}. 
                        error: ` + error.message);
                    })

            }
        },

        returnsScoreOrEmptyStringGivenBracketPosition(position) {
            if (this.scores.length < position) {
                return '';
            }
            return this.scores[position - 1];
        },

    },
    beforeMount() {
        
        if (this.firstTeam?.teamName == undefined || this.secondTeam?.teamName == undefined) {
            this.secondTeamName = '';
            this.firstTeamName = '';
        }

        else {
            this.firstTeamName = this.firstTeam.teamName;
            this.secondTeamName = this.secondTeam.teamName;
        }


        const score1 = this.returnsScoreOrEmptyStringGivenBracketPosition(this.firstPosition);
        const score2 = this.returnsScoreOrEmptyStringGivenBracketPosition(this.secondPosition);

        this.firstPositionScore = score1.score;
        this.secondPositionScore = score2.score;
        



        
    }

}

</script>

<style scoped>

input {
    width: 15px;
}
.matchup {
    min-width: 125px;
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