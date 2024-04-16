<template>
    <!-- TODO - programmatically add the round-one class, and change for 
                other rounds. should be simple enough with computed property. -->
    <div class="round ">
        <div class="round-details" :class="isCurrentRound ? 'current' : ''">
            Round {{ this.currentRound }}<br />
            <TournamentMatchUp v-for="(matchup, index) in this.matchUps" v-bind:key="matchup[0].teamId"
                v-bind:tournamentId="this.tournamentId" 
                :firstTeam="matchup[0]" 
                :secondTeam="matchup[1]"
                :firstPosition="startPosition + (2 * index)" 
                :secondPosition="startPosition + (2 * index) + 1" 
                :scores="this.scores"/>

        </div>
    </div>
</template>

<script>
import TournamentMatchUp from './TournamentMatchUp.vue';
export default {

    props: [
        "currentRound",
        "round",
        "teams",
        "tournamentId",
        "startPosition",
        "scores",
    ],

    computed: {
        isCurrentRound() {
            if (this.round <= this.currentRound) {
                return true;
            }
            return false;
        },

    },

    data() {
        return {
            matchUps: []
        }
    },

    methods: {
        packageMatchUps() {
            let returnArray = [];
            for (let i = 0; i < this.teams.length; i += 2) {
                const tempArray = [this.teams[i], this.teams[i + 1]];
                returnArray.push(tempArray);
            }
            this.matchUps = returnArray;
        }

    },

    created() {
        this.packageMatchUps();
    },

    components: {
        TournamentMatchUp
    },

}

</script>

<style scoped>
.round {
    display: block;
    float: left;
    /* display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex; */
    display: flex;
    -webkit-flex-direction: column;
    flex-direction: column;
    width: 95%;
    width: 30.8333%\9;
    background-color: gray;
}

.split-one .round {
    margin: 0 2.5% 0 0;
}

.split-two .round {
    margin: 0 0 0 2.5%;
}

.round-two :deep(.matchup) {
    margin: 0;
    height: 60px;
    padding: 50px 0;
}

.round-three :deep(.matchup) {
    margin: 0;
    height: 60px;
    padding: 130px 0;
}

.round-details {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 13px;
    color: #2C7399;
    text-transform: uppercase;
    text-align: center;
    height: 40px;
}

/* .champion li, */
.round :deep(li) {
    background-color: #fff;
    box-shadow: none;
    opacity: 0.45;
}

.current :deep(li) {
    opacity: 1;
}

.current :deep(li.team) {
    background-color: #fff;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    opacity: 1;
}
</style>