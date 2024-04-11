<template>
    <h1>Join Requests for {{  }}</h1>
    <table>
        <thead>
            <tr>
                <th>Player username</th>
             <!-- <th>Age</th>
                <th>Email</th>
                <th>Recent Standings</th> -->
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <join-request v-for="request in requests" v-bind:key="request.requestId" 
            v-bind:columns="1" v-bind:request="request"/>

        </tbody>
    </table>

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



<style scoped>


</style>