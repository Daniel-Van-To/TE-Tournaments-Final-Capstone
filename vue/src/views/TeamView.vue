<template>
    <div class="home">
      <h1></h1>
      <TeamDisplay v-bind:teamMembers="teamMembers" v-bind:isCaptain="currentUserIsCaptain"/>
      <p></p>
    </div>
  </template>
  
  <script>
  import TeamDisplay from '../components/TeamDisplay.vue';
  import TeamService from '../services/TeamService.js';
  
  export default {
//we will be able to populate this with team info, we do require a get team by team id 
//path to be able to do this. 
    components: {
      TeamDisplay
    },

    computed: {
      teamId() {
        return parseInt(this.$route.params.teamId);
      },
      currentUserIsCaptain() {
        let holder = false;
        this.$store.state.userPackage.myTeams.forEach((team) => {
          if (parseInt(team.teamId) === this.teamId) {
            holder = true;
          }
        })

        // THIS NEEDS TO BE CHANGED BACK TO HOLDER - RETURNING TRUE FOR TESTING/DEBUGGING AS 
        // CAPTAIN

        return true;
      }
    },
  

    data() {
      return {
        teamMembers: [],
      }
    },
  
    methods: {
    },
    
    created() {
      TeamService.getUsersOnTeam(this.teamId)
      .then((response) => {
        this.teamMembers = response.data;
      });
    }
  };
  </script>