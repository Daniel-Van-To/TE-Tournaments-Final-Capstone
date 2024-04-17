<template>
    <div class="home">
      <h1>Browse Teams</h1>
      <TeamCard v-for="(team,index) in teams" v-bind:key="index" v-bind:team="team"/>
      <p></p>
    </div>
  </template>
  
  <script>

  import TeamsList from '../components/TeamsList.vue';
  import TeamService from '../services/TeamService.js';
  import TeamCard from '../components/TeamCard.vue';
  
  export default {
    components: {
      TeamCard
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