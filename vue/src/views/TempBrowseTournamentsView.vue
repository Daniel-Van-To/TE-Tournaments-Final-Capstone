<template>
    <div class="home">
      <h1>Browse Tournaments</h1>
      <button @click.prevent="pushToCreateTournamentView">Create A Tournament</button>
      <br><br/>
      <select v-model="this.filterBy">  
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
        filterBy: ''
        
      }
    },
  
    computed: {
  
      completed() {
        return this.tournaments.filter( (tournament) => {
          return tournament.tournamentStatus == 'c';
        });
      },
  
      ongoing() {
        return this.tournaments.filter( (tournament) => {
          return (tournament.tournamentStatus == 'o' && tournament.tournamentId > 0);
        });
      },
  
      scheduled() {
        return this.tournaments.filter( (tournament) => {
          return tournament.tournamentStatus == 's';
        });
      },
      filterTournamentsByGame() {
        if(this.filterBy.length > 0 && !(this.filterBy === "Enter a game to filter by:")) {
            return this.tournaments.filter((tournament) => {   
             return tournament.gameName == this.filterBy;
            
        })
        }
        else {
            return this.tournaments;
        }
        
      }
  
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
      })

      GameService.getGamesList()
      .then((response) => {
        this.games = response.data;
      })
  
  
    }
  };
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
  
  
  
  </style>