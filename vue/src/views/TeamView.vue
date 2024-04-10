<template>
    <div class="home">
      <h1>BrowseTeams</h1>
      <TeamDisplay v-bind:teamMembers="teamMembers"/>
      <p></p>
    </div>
  </template>
  
  <script>
  import TeamDisplay from '../components/TeamDisplay.vue';
  import TeamService from '../services/TeamService.js';
  
  export default {
    components: {
      TeamDisplay
    },
    data() {
      return {
        teamMembers: [],
      }
    },
  
    methods: {
    },
    
    created() {
      let teamId = parseInt(this.$route.params.id);
      TeamService.getTeam(teamId).then((response) => {
        this.teamMembers = response.data;
      });
    }
  };
  </script>