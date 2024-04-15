<template>
    <div class="home">
      <h1>Home</h1>
    </div>
  </template>
  
  <script>
    import teamService from '../services/TeamService.js';
    import tournamentService from '../services/TournamentService.js';
  
  export default {
    
  
    components: {

    },
  
    data() {
      return {

        requests: [],
        teamsUserIsCaptainOf: []
  
      }
    },
  
  
    methods: {
  
    },
    created() {

        teamService.getListOfTeamsUserIsCaptainOf(this.$store.state.user.id)
        .then((response) => {
            this.teamsUserIsCaptainOf = response.data;
        });
  
    }
  };
  
  </script>
  