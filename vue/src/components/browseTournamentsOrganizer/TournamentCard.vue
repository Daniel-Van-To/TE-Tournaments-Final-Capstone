<template>
    
    <div class="card-container" v-if="this.tournamentDto.tournamentId > 0" v-on:click="pushToTournamentView">
        <h3>{{ tournament.tournamentName }}</h3>
        <span class="game-name">{{ tournament.gameName }}</span>
        <span class="participants">{{ numberOfParticipants }} / {{ tournamentDto.maximumParticipants }} Teams Participating</span>
        <span class="entry-fee">{{ tournament.entryFee }}  {{ tournamentStatus }}</span>
    </div>

</template>

<script>
import tournamentService from '../../services/TournamentService.js';
export default {
    

    data() {
        return {
            tournamentDto: [],
            numberOfParticipants: ""
        }
  },

    props: [
        'tournament'
    ],

    computed: {
        tournamentStatus() {
            if(this.tournamentDto.tournamentStatus == 'c') {
                return 'Completed';
            }
            else if(this.tournamentDto.tournamentStatus == 'o') {
                return 'Ongoing';
            }
            else {
                return 'Scheduled';
            }
        }
    },


    methods: {
        pushToTournamentView() {
            this.$router.push({name: "tournament-details-view", 
            params: {tournamentId: this.tournament.tournamentId}});
        },
    },

    created() {
        tournamentService.getTournamentDetail(this.tournament.tournamentId).then((response) => {
            this.tournamentDto = response.data;
            this.numberOfParticipants = response.data.participants.length;
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
    padding: 20px;
    border: 1px solid var(--purple);
    box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
    margin:0em;
    cursor: pointer;
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

.game-name,
.entry-fee,
.participants {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}

</style>