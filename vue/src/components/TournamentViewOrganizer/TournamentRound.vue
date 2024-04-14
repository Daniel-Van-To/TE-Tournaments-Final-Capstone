<template>
     <div class="round round-one current">
          <div class="round-details">{{this.currentRound}}<br />
            <!-- <span class="date">March 16</span> -->
            {{ JSON.stringify(this.matchUps) }}
             <TournamentMatchUp v-for="matchup in this.matchUps"
             v-bind:key="matchup[0].teamId"
             v-bind:tournamentId="this.tournamentId"
             /> <!--TournamentMatchUp requires 2 more v-bind-->

          </div>
    </div>
</template>

<script>
import TournamentMatchUp from './TournamentMatchUp.vue';
export default {
    data() {
        return {
           matchUps: []
        }
    },

    methods: {
        packageMatchUps() {
            let returnArray = [];
            for(let i = 0; i < this.teams.length; i+=2) {
                const tempArray = [this.teams[i], this.teams[i+1]];
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

    props: ["currentRound", "teams", "tournamentId", "startPosition"],


}

</script>