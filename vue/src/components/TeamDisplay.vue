<template>
    <table>
        <thead>
            <div v-if="this.fullTeam == false">
                <button class="btn btn-see-requests" v-if="isCaptain" v-on:click="pushToSeeTeamJoinRequestsView">See Join Requests</button>
                <button class="btn btn-join-request" v-else v-on:click="submitRequest">Submit a Join Request</button>
            </div>
            
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(member,index) in teamMembers" v-bind:key="member.id">
                <td>{{ index+1 }}</td>
                <td>{{ member.displayName }}</td>
            </tr>
        </tbody>
    </table>
</template>


<script>
import TeamService from '../services/TeamService';

export default {

    props: ["teamMembers", "isCaptain"],

    data() {
        return {
            request: {
                teamId: this.$route.params.teamId,
                userName: this.$store.state.user.username,      
            },

            fullTeam: false
        }
    },

    computed : {

    },
    
    methods: {
        submitRequest() {
            TeamService.sendJoinRequest(this.request)
            .then((response) => {
                if (response.status === 201) {
                    this.$store.commit(
                        'SET_NOTIFICATION',
                        {
                        message: 'Request sent.',
                        type: 'success'
                        }
                    );
                    this.$router.push({name: 'home'});
                    }
                else if(response.status === 208) {
                    this.$store.commit('SET_NOTIFICATION', {
                        message: 'Request not sent - user already have a pending join request for this team.',
                        type: 'error'
                    });
                }
                this.$router.push({name: 'teams'});
            })
            .catch((error) => {
                if(error.response.status === 208) {
                    this.$store.commit('SET_NOTIFICATION', {
                        message: 'Request not sent - user already have a pending join request for this team.',
                        type: 'error'
                    });
                }
            });
        },

        pushToSeeTeamJoinRequestsView() {
            this.$router.push({name: 'see-team-join-requests-view', params: {teamId: this.$route.params.teamId}});
        },

    },
    
    created() {
        TeamService.checkIfTeamIsFull(this.$route.params.teamId)
        .then((response) => {
            this.fullTeam = response.data;
        })
    }
};

</script>

<style scoped>
table {
  margin: auto;

}

th, td {
  text-align: left;
  padding: 10px;
  vertical-align: top;
  border: 1px solid var(--purple);
}

tr:nth-child(even) {
  background-color: rgb(238, 238, 238);
  
}

.btn {
  color: #212529;
  text-align: center;
  vertical-align: middle;
  border: 1px solid transparent;
  padding: .375rem .75rem;
  margin-right: 10px;
  margin-bottom: 5px;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: .25rem;
  cursor: pointer;
  border: 1px solid var(--purple);

  font-family: 'Roboto Condensed', sans-serif;
  font-weight: bold;
}

.btn-join-request {
  color: rgb(255, 255, 255);
  background-color: #0062cc;
  border-color: #005cbf;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
  font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}

thead {
    justify-items: center;
}

* {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}
</style>