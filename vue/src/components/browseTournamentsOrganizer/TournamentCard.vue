<template>
    
    <div class="card-container" v-on:click="pushToTournamentView">
        <h3>{{ tournament.tournamentName }}</h3>
        <span class="game-name">{{ tournament.gameName }}</span>
        <span class="entry-fee">{{ tournament.entryFee }}</span>
    </div>

</template>

<script>

export default {

    props: [
        'tournament'
    ],

    methods: {
        pushToTournamentView() {
            this.$router.push({name: "tournament-details-view", 
            params: {tournamentId: this.tournament.tournamentId}});
        }
    }

}

</script>

<style scoped>

div {
    border: 4px solid pink;
}

.card-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
        "t_name t_name t_name"
        "t_game .      t_entry"
    ;
}

h3 {
    grid-area: t_name;
}

.game-name {
    grid-area: t_game;
}
.entry-fee {
    grid-area: t_entry;
}

</style>