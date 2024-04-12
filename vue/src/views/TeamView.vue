<template>
  <div class="home">
    <h1></h1>
    <TeamDisplay v-bind:teamMembers="teamMembers" v-bind:isCaptain="this.isCaptain" />
    <p></p>
  </div>
</template>
  
<script>
import TeamDisplay from '../components/TeamDisplay.vue';
import TeamService from '../services/TeamService.js';

export default {


  components: {
    TeamDisplay
  },

  computed: {
    teamId() {
      return parseInt(this.$route.params.teamId);
    },

    // teamCaptainId() {
    //   return parseInt(this.$store.state.user.id);
    // },

  },


  data() {
    return {
      //both fields filled with created()
      teamMembers: [],
      isCaptain: false,

    }
  },

  methods: {
  },

  created() {

    TeamService.getUsersOnTeam(this.teamId)
      .then((response) => {
        this.teamMembers = response.data;
      });

    TeamService.isCurrentUserCaptain(this.teamId, this.$store.state.user.id)
      .then((response) => {
        this.isCaptain = response.data;
      });
  }
};
</script>