<template>
    <div class="home">
      <NavBar/>
      <h1>BrowseTeams</h1>
      <TeamDisplay v-bind:teamMembers="teamMembers"/>
      <p></p>
    </div>
  </template>
  
  <script>
  import NavBar from '../components/NavBar.vue';
  import TeamDisplay from '../components/TeamDisplay.vue';
  import TeamService from '../services/TeamService.js';
  
  export default {
    components: {
      NavBar,
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
      })
    }
  };
  </script>