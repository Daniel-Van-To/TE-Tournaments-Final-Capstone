<template>
    <div class="teams">
        <router-link v-for="team in teams" v-bind:key="team.teamId"
            v-bind:to="{ name: 'team-view', params: { id: team.teamId }}">
            <div class="team">{{ team.teamName }}</div>
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



