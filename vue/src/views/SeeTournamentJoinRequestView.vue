<template>
    <h1>Join Requests for {{ }}</h1>
    <tournament-join-request v-for="request in requests" v-bind:key="request.requestId" 
    v-bind:request="request" />
</template>




<script>
import tournamentService from '../services/TournamentService.js'
import TournamentJoinRequest from '../components/TournamentJoinRequest.vue'



export default {

    components: { TournamentJoinRequest },

    data() {
        return {
            requests: [],
        }
    },

    created() {
        tournamentService.getPendingTournamentJoinRequests(this.$route.params.tournamentId)
            .then((response) => {
                console.log(this.$route.params.tournamentId);
                if (response.status === 200) {
                    this.requests = response.data;
                }
            });

    }
}


</script>



<style scoped></style>