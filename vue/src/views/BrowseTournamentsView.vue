<template>
  <div class="home">
    <h1>Browse Tournaments</h1>
    <button class="create-tournament-button" @click.prevent="pushToCreateTournamentView">Create A Tournament</button>
    <br><br/>
    <label class="filter-label" for="game-filter">Filter Tournaments by Game: </label>
    <select class="game-filter" v-model="this.gameFilter">  
      <option v-for="(game,index) in games" v-bind:value="game.name" v-bind:key="index">
          {{ game.name }}
      </option>
    </select>
      <TournamentCard class="card" v-for="tournament in filterTournamentsByGame" v-bind:key="tournament.tournamentId"
          v-bind:tournament="tournament"/>
      
  </div>
</template>
  
<script>
import TournamentCard from '../components/browseTournamentsOrganizer/TournamentCard.vue';
import TournamentService from '../services/TournamentService';
import GameService from '../services/GameService.js';

export default {


  components: { TournamentCard },

  data() {
    return {
      tournaments: [],
      games: [],
      // tournamentStatus: ['Reset Filter', 'Completed', 'Ongoing', 'scheduled'],
      gameFilter: ''
      
    }
  },

  computed: {

    filterTournamentsByGame() {
          if(this.gameFilter.length > 0 && !(this.gameFilter === "Reset Filter")) {
              return this.tournaments.filter((tournament) => {   
              return tournament.gameName == this.gameFilter;
              });    
          }
          else {
              return this.tournaments;
          }
      },
  },

  methods: {

    pushToCreateTournamentView() {
      this.$router.push({ name: 'create-tournament-view' });
    }

  },
  

  created() {
    TournamentService.getAllTournaments()
    .then(response => {
      this.tournaments = response.data;
    });

    GameService.getGamesList()
    .then((response) => {
      this.games = response.data;
    });


  }

}


</script>

<style scoped>

.tournamentSections {
  display: flex;
  flex-direction:column;
  border: 1px solid black;
  flex-basis: 100%;
  padding: 0;


}

.tournamentSections>* {
  border: 2px dashed blue;
  padding-bottom: 20px;
}

.card {
  margin-bottom: 1rem;
}

.create-tournament-button,
.filter-label,
.game-filter
{
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}


</style>