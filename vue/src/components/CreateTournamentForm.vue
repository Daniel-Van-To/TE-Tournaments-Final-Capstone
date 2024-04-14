<template>
    <div class="home">
      <!-- tournament requirements:
             host_id, tournament_name(50 char), entry_fee, game_name, accepting_teams
              -->
      <form v-on:submit.prevent="submitForm" class="createTournamentForm">
        <div class="tournamentFormField">
            <label for="tournamentName">Tournament name: </label>
            <input id="tournamentName" type="text" class="formControl" v-model="tournament.tournamentName"/>
        </div> 
        <div class="tournamentFormField">
            <label for="gameName">Game name: </label>
            <input id="gameName" type="text" class="formControl" v-model="tournament.gameName"/>
        </div> 
        <div class="tournamentFormField">
            <label for="entryFee">Entry fee: $</label>
            <input id="entryFee" type="number" class="formControl" v-model="tournament.entryFee"/>
        </div> 
        <span>When you press submit, this tournament will appear in the list of tournaments.</span>
        <br/>
        <button class="btn btn-submit" type="submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
     </form>
      <p></p>
    </div>
  </template>
  
  <script>
  import tournamentService from '../services/TournamentService'
  
  export default {
    components: {
    },
    data() {
      return {
        tournament: {
            tournamentName: "",
            gameName: "",
            hostId: this.$store.state.user.id,
            entryFee: "",
            tournamentStatus: "s",
            acceptingTeams: true
        }
      }
    },
  
  
    methods: {
        submitForm(){
            if(!this.validateForm()){
                return ;
            }
            tournamentService.createTournament(this.tournament)
            .then((response)=> {
                if(response.status === 201) {
                    this.$store.commit('SET_NOTIFICATION',
                    {
                        message: 'Tournament was created',
                        type:'SUCCESS'
                    });

                    this.$router.push({name: 'browse-tournaments'});
                }
            })
            .catch(error => {
                this.handleErrorResponse(error, 'creating');
            });
        },
        cancelForm(){
            this.$router.push({name:'browse-tournaments'})
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
            if (this.tournament.tournamentName.length === 0) {
              msg += 'A new tournament must have a name. ';
            }
            if (this.tournament.tournamentName.length > 50) {
              msg += 'Tournament name is too long (over 50 characters). '
            }
            if (this.tournament.gameName.length === 0) {
              msg += 'A new tournament must have a game name.';
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
.createTournamentForm {
  padding: 10px;
  margin-bottom: 10px;
}

.tournamentFormField {
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

</style>