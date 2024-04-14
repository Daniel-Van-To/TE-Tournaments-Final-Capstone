<template>
    <ul class="matchup">
        <li class="team team-top">{{firstTeam.teamName}}
            <span class="score">{{firstPositionScore}}</span>
        </li>
        <li class="team team-bottom">{{secondTeam.teamName}}
            <span class="score">{{secondPositionScore}}</span>
        </li>
    </ul>
</template>

<script>

export default {

    props: ["tournamentId", "firstPosition", "firstTeam", "secondPosition", "secondTeam"],

    data() {

        return {
            firstPositionScore: "",
            secondPositionScore: ""
        }
    },
    created() {
        // get scores with score service

    }

}

</script>