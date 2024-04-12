<template>
    <!-- this might be easier with a grid -->
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
            <td>{{ user.username }}</td>
            <td><button @click="submitRequest('a')" class="acceptBtn">Accept</button></td>
            <td><button @click="submitRequest('d')" class="denyBtn">Deny</button></td>
        </tbody>
    </table>
    {{ JSON.stringify(updatedRequest) }}
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
            const request = this.request;
            request.requestStatus = newStatus;
            requestService.submitUpdatedRequest(request);

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