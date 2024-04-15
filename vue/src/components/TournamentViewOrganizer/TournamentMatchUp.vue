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
        

    }

}

</script>

<style scoped>

.matchup {
    margin: 0;
    width: 100%;
    padding: 10px 0;
    height: 60px;
    /* -webkit-transition: all 0.2s;
    transition: all 0.2s; */
}

.score {
    font-size: 11px;
    text-transform: uppercase;
    float: right;
    color: #2C7399;
    font-weight: bold;
    font-family: 'Roboto Condensed', sans-serif;
    position: absolute;
    right: 5px;
}

.team {
    padding: 0 5px;
    margin: 3px 0;
    height: 25px;
    line-height: 25px;
    white-space: nowrap;
    overflow: hidden;
    position: relative;
}

.round-two .matchup {
    margin: 0;
    height: 60px;
    padding: 50px 0;
}

.round-three .matchup {
    margin: 0;
    height: 60px;
    padding: 130px 0;
}

</style>