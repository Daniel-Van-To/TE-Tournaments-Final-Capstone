<template>
    <div class="home">
      <NavBar/>
      <!-- team requirements:
            team_name, game_name, current_user, is_accepting_members, 
              -->
      <h1>Create a team</h1>
      <form v-on:submit.prevent="submitForm" class="createTeamForm">
        <div class="teamFormField">
            <label for="teamName">Team name: </label>
            <input id="teamName" type="text" class="formControl" v-model="team.team_name"/>
        </div> 
        <div class="teamFormField">
            <label for="gameName">Game name: </label>
            <input id="gameName" type="text" class="formControl" v-model="team.game_name"/>
        </div> 
        <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
     </form>
      <p></p>
    </div>
  </template>
  
  <script>
  import NavBar from '../components/NavBar.vue';
  import CreateTeamService from '../services/CreateTeamService.js';
  
  export default {
    components: {
      NavBar,
      CreateTeamService
    },
    data() {
      return {
        team: {
            team_name: "",
            game_name: "",
            current_user: this.$store.user.username,
            is_accepting_members: true
        }
      }
    },
  
  
    methods: {
        submitForm(){
            if(!this.validateForm()){
                return ;
            }
            CreateTeamService.createTeam(this.team).then((response)=> {
                if(response.status === 201) {
                    this.$store.commit('SET_NOTIFICATION',
                    {
                        message: 'Team was created',
                        type:'SUCCESS'
                    });
                    this.$router.push({name: 'home'});
                }
            })
            .catch(error => {
                this.handleErrorResponse(error, 'adding');
            });
        },
        cancelForm(){
            this.$router.push({name:'home'})
        },
        handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " team. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " team. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " team. Request could not be created.");
      }
    },
        validateForm() {
      let msg = '';
      if (this.team.team_name.length === 0) {
        msg += 'The new team must have a team name. ';
      }
      if (this.team.game_name.length === 0) {
        msg += 'The new game must have a game name.';
      }
      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    }
    },
    created() {
  
    }
  };
  </script>