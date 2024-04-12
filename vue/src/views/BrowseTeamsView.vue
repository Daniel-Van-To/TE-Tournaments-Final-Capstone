<template>
    <div class="home">
      <h1>Browse Teams</h1>
      <TeamsList v-bind:teams="teams"/>
      <p></p>
    </div>
  </template>
  
  <script>

  import TeamsList from '../components/TeamsList.vue'
  import TeamService from '../services/TeamService.js';
  
  export default {
    components: {
      TeamsList
    },
    data() {
      return {
        teams: [],
      }
    },
  
    methods: {
      
      getTeams() {
        TeamService.getTeams()
        .then((response) => {
          this.teams = response.data;
        })
        .catch((error) => {
          if (error.response) {
            this.$store.commit('SET_NOTIFICATION',
              "Error getting teams list. Response received was '" + error.response.statusText + "'.");
          } else if (error.request) {
            this.$store.commit('SET_NOTIFICATION', "Error getting teams list. Server could not be reached.");
          } else {
            this.$store.commit('SET_NOTIFICATION', "Error getting teams list. Request could not be created.");
          }
        });
      }
  
    },
    created() {
      this.getTeams();
    }
  };
  </script>