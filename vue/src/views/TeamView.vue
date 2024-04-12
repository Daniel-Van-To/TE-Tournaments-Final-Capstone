<template>
    <div class="home">
      <h1></h1>
      <TeamDisplay v-bind:teamMembers="teamMembers" v-bind:isCaptain="isCurrentUserCaptain"/>
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

      teamCaptainId() {
        return parseInt(this.$store.state.user.id);
      },

      isCurrentUserCaptain() {
        let holder = false;
        // const myTeams = this.$store.state.userPackage.myTeams;
        // console.log(JSON.stringify(myTeams));
        // myTeams.forEach((team) => {
        //   if (parseInt(team.teamCaptainId) === this.teamCaptainId) {
        //     holder = true;
        //   }
        // })

        return true;
      }
    },
  

    data() {
      return {
        teamMembers: [],
        isCaptain: false,

      }
    },
  
    methods: {
    },
    
    created() {
      TeamService.getUsersOnTeam(this.teamId)
      .then((response) => {
        this.teamMembers = response.data;
      });
      TeamService.isCurrentUserCaptain(this.teamId)
      .then((response) => {
        this.isCaptain = response.data;
      })
      // this.$store.commit("UPDATE_MY_TEAMS", TeamService.returnTeamsAndHandleErrors(this.$store.state.user.id));
    }
  };
  </script>