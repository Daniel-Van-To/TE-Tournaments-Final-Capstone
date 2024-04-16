<template>
    <div class="home">
      <h1>Browse Tournaments</h1>
      <button @click.prevent="pushToCreateTournamentView">Create A Tournament</button>
      <br><br/>
      <div class="tournamentSections">
        <tournament-section title="Ongoing" :tournaments="ongoing" />
      </div>
    </div>
  </template>
    
  <script>
  import TournamentSection from '../components/browseTournamentsOrganizer/TournamentSection.vue';
  import TournamentService from '../services/TournamentService';
  
  export default {
  
  
    components: { TournamentSection },
  
    data() {
      return {
        tournaments: [],
        
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
  
  
    }
  };
  </script>
  
  <style scoped>
  
  .tournamentSections {
    display: flex;
    border: 1px solid black;
    flex-basis: 100%;
    padding: 0;
  
  
  }
  
  .tournamentSections>* {
    border: 2px dashed blue;
    margin: 2rem;
  }
  
  
  
  </style>