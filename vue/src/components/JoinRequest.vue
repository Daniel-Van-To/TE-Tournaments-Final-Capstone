<template >
    <!-- this might be easier with a grid -->
    <table v-if="tournamentIdIsNull">
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
            <td>{{ user.username }}</td>
            <td  v-if="pendingStatus" ><button @click="submitRequest('a')" class="acceptBtn">Accept</button></td>
            <td v-if="pendingStatus"><button @click="submitRequest('d')" class="denyBtn">Deny</button></td>
            <td v-if="acceptedRequestStatus">Request Accepted!</td>
            <td v-if="deniedRequestStatus">Request Denied!</td>

        </tbody>
    </table>
   <!-- <p v-if="tournamentIdIsNull">{{ JSON.stringify(updatedRequest) }}</p>  -->
</template>

<script>
import requestService from '../services/RequestService';
import userService from '../services/UserService';

export default {

    data() {
        return {
            user: {},
            updatedRequest: {},
        }
    },


    props: ['request'],

    computed : {
        pendingStatus() {
            return this.updatedRequest.requestStatus === 'p';
        },

        acceptedRequestStatus() {
            return this.updatedRequest.requestStatus === 'a';
        },

        deniedRequestStatus() {
            return this.updatedRequest.requestStatus === 'd';
        },

        tournamentIdIsNull() {
            return this.updatedRequest.tournamentId === 0;
        }
    },

    methods: {

        updateRequestStatus(newStatus) {
            this.updatedRequest.requestStatus = newStatus
        },

        submitRequest(newStatus) {
        // request: {
            // requestId: ,
            // tournamentId: ,
            // teamId: ,
            // gameName: ,
            // requestStatus: ,
            // requesterId: ,
        // }
            this.updatedRequest = this.request;
            this.updatedRequest.requestStatus = newStatus;
            requestService.submitUpdatedRequest(this.updatedRequest);

        }
    },

    created() {
        userService.getUser(this.request.requesterId)
        .then(response => {
            this.user = response.data;
        });
        this.updatedRequest = this.request;
    }
}


</script>

<style scoped></style>