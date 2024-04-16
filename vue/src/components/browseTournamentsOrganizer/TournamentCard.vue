<template>
    
    <div class="card-container" v-on:click="pushToTournamentView">
        <h3>{{ tournament.tournamentName }}</h3>
        <span class="game-name">{{ tournament.gameName }}</span>
        <!-- <span class="participants">{{ currentNumberOfParticipants }} / {{ tournamentDto.maximumParticipants }}</span> -->
        <span class="entry-fee">{{ tournament.entryFee }}  {{ tournamentDto.tournamentStatus }}</span>
    </div>

</template>

<script>
import tournamentService from '../../services/TournamentService.js';
export default {
    

    data() {
        return {
            tournamentDto: {},
            numberOfParticipants: ""
        }
  },

    props: [
        'tournament'
    ],

    computed: {
        currentNumberOfParticipants() {
                return this.tournamentDto.participants.length;
        },
    },

    methods: {
        pushToTournamentView() {
            this.$router.push({name: "tournament-details-view", 
            params: {tournamentId: this.tournament.tournamentId}});
        },
        // findNumberOfParticipants() {
        //     let participants = this.tournamentDto.participants;
        //     this.numberOfParticipants = participants.length;
        // }
    },

    created() {
        tournamentService.getTournamentDetail(this.tournament.tournamentId).then((response) => {
            this.tournamentDto = response.data;
        })
    }

}

</script>

<style scoped>

div {
    border: 4px solid pink;
}

.card-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
        "t_name t_name t_participants"
        "t_game .      t_entry"
    ;
    /* width: 100;
    height:auto; */
}

h3 {
    grid-area: t_name;
    margin: 0%;
}

.game-name {
    grid-area: t_game;
}
.entry-fee {
    grid-area: t_entry;
}
.participants {
    grid-area: t_participants;
}

</style>