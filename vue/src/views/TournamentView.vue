<template>
    <div class="home">
        <!-- <div v-for="team in teams" v-bind:key="team.teamId">
            {{ team.teamName }}
        </div> -->
    </div>
  </template>
    
  <script>
 
  import TournamentService from '../services/TournamentService';

  export default {
  
  
    components: {
      
    },
  
    computed: {
      
    },
  
  
    data() {
      return {
       teams: {},
    //    team: {
        // teamId:
        // teamCaptainId: 
        // teamName: 
        // acceptingMembers:
        // gameName:
    // }
       tournament: {},  
        //   tournamentId:
        //   tournamentHost:
        //   tournamentName:
        //  entryFee:
        //  gameName:
        //
      }
    },
  
    methods: {
    },
  
    created() {
        TournamentService.getTournamentDetail(this.$route.params.tournamentId)
        .then(response => {
            this.teams = response.data.participants;
            this.tournament = response.data;
        })
        .catch(error => {
            this.$store.commit("SET_NOTIFICATION", "getTournamentDetail method in tournament view failed");
        });
      
    }
  };
  </script>