<template>
    <div class="home">
      <h1>Browse Teams</h1>
        <div id="team-modifiers">
            <div id="filter-header"><h4>Filter by Games</h4></div>
            <div id="full-teams-header">
                <h4 id="table-header" v-if="fullTeams">Hide Teams that are full?</h4>
                <h4 v-else>Show Teams that are full?</h4>
            </div>
            <div id="dropdown">
                <select class="game-filter" v-model="this.gameFilter">  
                    <option v-for="(game,index) in games" v-bind:value="game.name" v-bind:key="index">
                        {{ game.name }}
                    </option>
                 </select>
            </div>
            <div id="full-team-bttn">
                <button id="hide-teams" v-if="fullTeams" v-on:click="toggleFullTeams()">Hide Them!</button>
                <button id="show-teams" v-else v-on:click="toggleFullTeams()">Show Them!</button>
            </div>
        </div>
        <div v-if="this.fullTeams == false">
            <TeamCard  v-for="(team,index) in filterTeamsByGame" v-bind:key="index" v-bind:team="team"/>
        </div>
        <div v-else>
            <TeamCard  v-for="(team,index) in filterAllTeamsByGame" v-bind:key="index" v-bind:team="team"/>
        </div>
      
      
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
        openTeams: [],
        allTeams: [],
        games: [],
        gameFilter: "",
        fullTeams: false
      }
    },

    computed: {
        filterTeamsByGame() {
          if(this.gameFilter.length > 0 && !(this.gameFilter === "Reset Filter")) {
              return this.openTeams.filter((team) => {   
              return team.gameName == this.gameFilter;
              });    
          }
          else {
              return this.openTeams;
          }
      },

      filterAllTeamsByGame() {
          if(this.gameFilter.length > 0 && !(this.gameFilter === "Reset Filter")) {
              return this.allTeams.filter((team) => {   
              return team.gameName == this.gameFilter;
              });    
          }
          else {
              return this.allTeams;
          }
      }

    },
  
    methods: {
      
      getOpenTeams() {
        TeamService.getTeams()
        .then((response) => {
          this.openTeams = response.data;
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
      },

      getAllTeams() {
        TeamService.getAllTeams()
        .then((response) => {
          this.allTeams = response.data;
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
      },

      toggleFullTeams() {
        this.fullTeams = !this.fullTeams;
      }
  
    },
    created() {
      this.getOpenTeams();
      this.getAllTeams();
      GameService.getGamesList()
        .then((response) => {
        this.games = response.data;
        });
    }
  };
  </script>

<style>
    #team-modifiers {
        display: grid;
        grid-template-columns: 1rem 1 rem 1rem;
        grid-template-areas:
        "filter-header filter-header full-teams-header"
        "filter        filter        full-teams-bttn";
    }

    #filter-header {
        grid-area: filter-header;
    }

    #full-teams-header {
        grid-area: full-teams-header;
    }
    #dropdown {
        grid-area: filter;
    }
    #full-team-bttn {
        grid-area:full-teams-bttn;
    }    
</style>