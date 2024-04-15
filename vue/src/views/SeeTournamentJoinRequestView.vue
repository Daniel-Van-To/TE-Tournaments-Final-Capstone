<template>
    <h1>Join Requests for {{ }}</h1>
    <join-request v-for="request in requests" v-bind:key="request.requestId" 
    v-bind:request="request" />
</template>




<script>
import teamService from '../services/TeamService.js'
import JoinRequest from '../components/JoinRequest.vue';



export default {

    components: { JoinRequest },

    data() {
        return {
            requests: [],
        }
    },

    created() {
        teamService.getPendingJoinRequests(this.$route.params.teamId)
            .then((response) => {
                if (response.status === 200) {
                    this.requests = response.data;
                }
            });

    }
}


</script>



<style scoped></style>