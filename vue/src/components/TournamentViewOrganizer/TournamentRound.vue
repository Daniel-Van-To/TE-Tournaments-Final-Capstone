<template>
    <div class="round" :class="[this.roundToString(this.round), {current: isCurrentRound}]">
        <div class="round-details">
            Round {{ this.round }}<br />
        </div>
        <TournamentMatchUp v-for="(matchup, index) in this.matchUps" v-bind:key="matchup[0].teamId"
            v-bind:tournament="this.tournament" :firstTeam="matchup[0]" :secondTeam="matchup[1]"
            :firstPosition="startPosition + (2 * index)" :secondPosition="startPosition + (2 * index) + 1"
            :scores="this.scores"
            :edit="edit"
            :round="round" />


    </div>
</template>

<script>
import TournamentMatchUp from './TournamentMatchUp.vue';
export default {

    props: [
        "currentRound",
        "round",
        "teams",
        "tournament",
        "startPosition",
        "scores",
        "edit",
    ],

    computed: {
        isCurrentRound() {
            if (this.round < this.currentRound) {
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
        },
        roundToString(round) {
            let returnString = 'round-';
            switch (round) {
                case (1):
                    returnString += 'one';
                    break;
                case (2):
                    returnString += 'two';
                    break;
                case (3):
                    returnString += 'three';
                    break;
                case (4):
                    returnString += 'four';
                    break;
                case (5):
                    returnString += 'five';
                    break;
                case (6):
                    returnString += 'six';
                    break;
                case (7):
                    returnString += 'seven';
                    break;
                case (8):
                    returnString += 'eight';
                    break;
                case (9):
                    returnString += 'nine';
                    break;
                case (10):
                    returnString += 'ten';
                    break;
            }
            return returnString;
        },

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
/* .round {
    display: flex;
    flex-direction: column;
    width: 95%;
    width: 30.8333%\9;
    background-color: gray;
} */

.split-one .round {
    margin: 0 2.5% 0 0;
}

.split-two .round {
    margin: 0 0 0 2.5%;
}

/* .round {
    width: 100px;
} */

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
    font-size: 14px;
    font-weight: bold;
    color: black;
    background-color: var(--pinkish);
    border: 2px solid var(--darkGreen);
    text-transform: uppercase;
    text-align: center;
    align-content: center;
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