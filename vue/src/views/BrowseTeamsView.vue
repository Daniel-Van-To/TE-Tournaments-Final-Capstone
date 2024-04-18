<template>
  <div class="home">
    <h1>Browse Teams</h1>
      <div id="team-modifiers">
          <div id="dropdown">
              <label class="field-labels" for="game-filter">Filter by Games : </label>
              <select class="game-filter" v-model="this.gameFilter">  
                  <option v-for="(game,index) in games" v-bind:value="game.name" v-bind:key="index">
                      {{ game.name }}
                  </option>
               </select>
          </div>
          <div id="full-team-bttn">
            <label class="field-labels" v-if="fullTeams">Hide Teams that are full: </label>
              <label class="field-labels" v-else>Show Teams that are full: </label>
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
  .game-filter,
  .field-labels{
    padding-right: 20px;
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
  }

  #hide-teams,
  #show-teams,
  #game-filter  {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
  }

</style>
