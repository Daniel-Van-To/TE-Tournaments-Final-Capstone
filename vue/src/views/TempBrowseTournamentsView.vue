<template>
    <div class="home">
      <h1>Browse Teams</h1>
      <table>
            <tr><th>Filter Teams by Games:</th></tr>
            <tr>
                <td>
                    <select class="game-filter" v-model="this.gameFilter">  
                        <option v-for="(game,index) in games" v-bind:value="game.name" v-bind:key="index">
                            {{ game.name }}
                        </option>
                    </select>
                </td>
            </tr>
            
        </table>
      <TeamCard v-for="(team,index) in filterTeamsByGame" v-bind:key="index" v-bind:team="team"/>
      <p></p>
    </div>
  </template>
  
  <script>

  import GameService from '../services/GameService.js';
  import TeamService from '../services/TeamService.js';
  import TeamCard from '../components/TeamCard.vue';
  
  export default {
    components: {
      TeamCard
    },
    data() {
      return {
        teams: [],
        games: [],
        gameFilter: ""
      }
    },

    computed: {
        filterTeamsByGame() {
          if(this.gameFilter.length > 0 && !(this.gameFilter === "Reset Filter")) {
              return this.teams.filter((team) => {   
              return team.gameName == this.gameFilter;
              });    
          }
          else {
              return this.teams;
          }
      },
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
      GameService.getGamesList()
        .then((response) => {
        this.games = response.data;
        });
    }
  };
  </script>