<template>
    <section>
        <header>
            <h2>{{ title }}</h2>
        </header>
        <div>
            <TournamentCard v-for="tournament in tournaments" v-bind:key="tournament.tournamentId"
            v-bind:tournament="tournament"/>
        </div>
    </section>
</template>

<script>
import TournamentCard from './TournamentCard.vue';


export default {

    components: { TournamentCard },
    props: ['title', 'tournaments']
}


</script>

<style scoped>

section {
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    flex-basis: 100%;
    padding: 0;
}

div {
    /* align-self:center; */
}


</style>