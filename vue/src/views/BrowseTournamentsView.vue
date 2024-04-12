<template>
  <div class="home">
    <h1>Browse Tournaments</h1>
    <button @click.prevent="pushToCreateTournamentView">Create A Tournament</button>
    <br><br/>
    <div class="tournamentSections">
      <tournament-section title="Completed" :tournaments="completed" />
      <tournament-section title="Ongoing" :tournaments="ongoing" />
      <tournament-section title="Scheduled" :tournaments="scheduled" />
    </div>
  </div>
</template>
  
<script>
import TournamentSection from '../components/browseTournamentsOrganizer/TournamentSection.vue';
import TournamentService from '../services/TournamentService';

export default {


  components: { TournamentSection },

  data() {
    return {
      tournaments: [],
      
    }
  },

  computed: {

    completed() {
      return this.tournaments.filter( (tournament) => {
        return tournament.tournamentStatus == 'c';
      });
    },

    ongoing() {
      return this.tournaments.filter( (tournament) => {
        return tournament.tournamentStatus == 'o';
      });
    },

    scheduled() {
      return this.tournaments.filter( (tournament) => {
        return tournament.tournamentStatus == 's';
      });
    },

  },

  methods: {

    pushToCreateTournamentView() {
      this.$router.push({ name: 'create-tournament-view' });
    }

  },

  created() {
    TournamentService.getAllTournaments()
    .then(response => {
      this.tournaments = response.data;
    })


  }
};
</script>

<style scoped>

.tournamentSections {
  display: flex;
  justify-content:space-around;
  border: 1px solid black;


}

.tournamentSections>* {
  border: 2px dashed blue;
  margin: 2rem;
}



</style>