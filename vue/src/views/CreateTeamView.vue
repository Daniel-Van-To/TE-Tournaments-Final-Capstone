<template>
    <div class="home">
      <!-- team requirements:
            team_name, game_name, current_user, is_accepting_members, 
              -->
      <h1>Create a team</h1>
      <form v-on:submit.prevent="submitForm" class="createTeamForm">
        <div class="teamFormField">
            <label for="teamName">Team name: </label>
            <input id="teamName" type="text" class="formControl" v-model="team.teamName"/>
        </div> 
        <div class="teamFormField">
            <label for="gameName">Game name: </label>
            <input id="gameName" type="text" class="formControl" v-model="team.gameName"/>
        </div> 
        <button class="btn btn-submit" type="submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
     </form>
      <p></p>
    </div>
  </template>
  
  <script>
  import TeamService from '../services/TeamService.js';
  
  export default {
    components: {
    },
    data() {
      return {
        team: {
            teamName: "",
            gameName: "",
            username: this.$store.state.user.username,
            acceptingMembers: true
        }
      }
    },
  
  
    methods: {
        submitForm(){
            if(!this.validateForm()){
                return ;
            }
            TeamService.createTeam(this.team).then((response)=> {
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
            if (this.team.teamName.length === 0) {
              msg += 'The new team must have a team name. ';
            }
            if (this.team.gameName.length === 0) {
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

  
<style scoped>
.createTeamForm {
  padding: 10px;
  margin-bottom: 10px;
}

.teamFormField {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}

select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

</style>../services/TeamService.js