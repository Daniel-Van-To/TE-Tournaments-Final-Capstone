<template>
    <div class="home">
      <h1>Teams Able to Join this Tournament</h1>
      <table>
        <tbody v-for="(team,index) in this.teamsUserIsCaptainOf" v-bind:key="index">
            <td >{{ team.teamName }} <button @click="submitRequest(team.teamId)" class="acceptBtn">Send Join Request for {{ team.teamName }}</button> </td>
                
        </tbody>
      </table> 
    </div>
  </template>
  
  <script>
    import teamService from '../services/TeamService.js';
    import tournamentService from '../services/TournamentService.js';
    import requestService from '../services/RequestService.js';
  
  export default {
    
  
    components: {

    },
  
    data() {
      return {

        request: {
            tournamentId: "",
            teamId: "",
            gameName: "",
            requestStatus: 'p',
            requesterId: this.$store.state.user.id
      },
        teamsUserIsCaptainOf: [],
        tournament: []
  
      }
    },
  
  
  

  
    methods: {
        submitRequest(teamId) {
            this.request.teamId = teamId;
            this.request.tournamentId = this.tournament.tournamentId;
            this.request.gameName = this.tournament.gameName;
            requestService.submitTournamentJoinRequestForTeam(this.request)
            .then((response) => {
                if (response.status === 201) {
                    this.$store.commit(
                        'SET_NOTIFICATION',
                        {
                        message: 'Request sent.',
                        type: 'success'
                        }
                    );
                    this.$router.push({name: 'tournament-details-view'});
                    }
                else if(response.status === 208) {
                    this.$store.commit('SET_NOTIFICATION', {
                        message: 'Request not sent - that team already has a pending join request for this tournament.',
                        type: 'error'
                    });
                }
                this.$router.push({name: 'tournament-details-view'});
            })
            .catch((error) => {
                if(error.response.status === 208) {
                    this.$store.commit('SET_NOTIFICATION', {
                        message: 'Request not sent - user already have a pending join request for this team.',
                        type: 'error'
                    });
                }
                else if(error.response.status === 418) {
                    this.$store.commit('SET_NOTIFICATION', {
                        message: 'Request Sent.',
                        type: 'success'
                    });
                    this.$router.push({name: 'tournament-details-view'});
                }
            });
        },
        

    },
    created() {

        teamService.getListOfTeamsMatchingTournamentGame(this.$route.params.tournamentId, this.$store.state.user.id)
        .then((response) => {
            this.teamsUserIsCaptainOf = response.data;
        });

        tournamentService.getTournamentDetail(this.$route.params.tournamentId)
        .then((response) => {
            this.tournament = response.data;
        });

    }
  };
  
  </script>
  