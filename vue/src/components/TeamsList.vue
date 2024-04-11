<template>
    <div class="teams">

        
        <router-link class="team" v-for="team in teams" v-bind:key="team.teamId"
            v-bind:to="{ name: 'team-view', params: { teamId: team.teamId } }">

            {{ team.teamName }}

        </router-link>
        

    </div>
</template>


<script>

export default {
    props: ["teams"],

    methods: {


    }
};

</script>

<style scoped>

.team {
    display: flex;
    flex-direction: column;
}

</style>


