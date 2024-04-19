<template>
    <div class="container">
    <div class="card-container" v-if="this.team.teamId > 0" v-on:click="pushToTeamView">
        <h3>Team Name: {{ team.teamName }}</h3>
        <span class="game-name">Team Game: {{ team.gameName }}</span>
        <span class="participants">{{ numberOfTeamMembers }} / {{ teamDto.maximumMembers }} Team Members</span>
    </div>
    </div>

</template>

<script>
import tournamentService from '../services/TournamentService.js';
import teamService from '../services/TeamService.js';
export default {
    

    data() {
        return {
            teamDto: [],
            numberOfTeamMembers: ""
        }
  },

    props: [
        'team'
    ],

    computed: {

    },


    methods: {
        pushToTeamView() {
            this.$router.push({name: "team-view", params: {teamId:this.team.teamId}});
        },
    },

    created() {
        teamService.getTeamInfo(this.team.teamId).then((response) => {
            this.teamDto = response.data;
            this.numberOfTeamMembers = response.data.members.length;
        })
    }

}

</script>

<style scoped>

div {
    /* border: 4px solid var(--purple); */
    padding-bottom:20px;
}

.card-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
        "t_name t_name t_participants"
        "t_game .      t_entry"
    ;
    padding: 20px;
    box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
    margin:0em;
    cursor: pointer;
    border: 1px solid var(--purple);
}


h3 {
    grid-area: t_name;
    margin: 0%;
}

.game-name {
    grid-area: t_game;
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}
.entry-fee {
    grid-area: t_entry;
}
.participants {
    grid-area: t_participants;
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}


</style>