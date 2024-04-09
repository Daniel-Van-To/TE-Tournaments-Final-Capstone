<template>
    <table>
        <thead>
            <button class="btn btn-join-request" v-on:click="submitRequest">Submit a Join Request</button>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="member in teamMembers" v-bind:key="member.id">
                <td>{{ member.id }}</td>
                <td>{{ member.username }}</td>
            </tr>
        </tbody>
    </table>
</template>


<script>
import TeamService from '../services/TeamService';
export default {
    props: ["teamMembers"],
    data() {
        return {
            request: {
                teamId: this.$route.params.id,
                userName: this.$store.state.user.username,      
            }
        }
    },
    
    methods: {
        submitRequest() {
            TeamService.sendJoinRequest(this.request).then((response) => {
                if (response.status === 201) {
                    this.$store.commit(
                        'SET_NOTIFICATION',
                        {
                        message: 'A new card was added.',
                        type: 'success'
                        }
                    );
                    this.$router.push({name: 'teams'});
                    }
            })
        }
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
}

tr:nth-child(even) {
  background-color: rgb(238, 238, 238);
}

.btn {
  display: inline-block;
  font-weight: 400;
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
}

.btn-join-request {
  color: rgb(255, 255, 255);
  background-color: #0062cc;
  border-color: #005cbf;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
}

thead {
    justify-items: center;
}
</style>