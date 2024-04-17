<template>
    <ul v-if="edit" class="matchup">
        <li class="team team-top">{{ firstTeamName }}
            <span class="score"><input :disabled="inputDisabledFirst" v-model="firstPositionScore" type="text" /></span>
        </li>
        <li class="team team-bottom">{{ secondTeamName }}
            <span class="score"><input :disabled="inputDisabledSecond" v-model="secondPositionScore" type="text" /></span>
        </li>
    </ul>
    <ul v-else class="matchup">
        <li class="team team-top">{{ firstTeamName }}
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
        "tournamentId",
        "firstPosition",
        "firstTeam",
        "secondPosition",
        "secondTeam",
        "scores",
        "edit",
    ],

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
            return this.scores[position - 1];
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



        
    }

}

</script>

<style scoped>

input {
    width: 15px;
}
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