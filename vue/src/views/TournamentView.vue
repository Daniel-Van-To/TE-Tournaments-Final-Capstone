<template>
    <button v-if="isTournamentHost" v-on:click="pushToSeeTournamentJoinRequestsView">
        See Join Requests for {{ this.tournament.tournamentName }}
    </button>
    <button v-if="isNotTournamentHost" v-on:click="pushToSendTournamentJoinRequestView">
        Send Join Requests for {{ this.tournament.tournamentName }}
    </button>
    <section v-if="isLoading" class="loading">
        Loading...
    </section>
    <section v-else id="bracket">
        <div class="container">
            {{ JSON.stringify(this.scores) }}
            <br/>
            {{ teams }}
            <div class="split split-one">
                <TournamentRound v-for="(round, index) in rounds" :key="index" class="round"
                    v-bind:class="this.roundToString(round)" v-bind:teams="this.teamsByRound(index+1)" 
                    v-bind:currentRound="round"
                    v-bind:tournamentId="tournament.tournamentId"
                    v-bind:startPosition="this.calculateStartPosition(round)" />
            </div>
        </div>
    </section>
</template>
    
<script>

import TournamentService from '../services/TournamentService';
import ScoreService from '../services/ScoreService';

import TournamentRound from '../components/TournamentViewOrganizer/TournamentRound.vue';

export default {

    components: { TournamentRound },

    computed: {

        isLoading() {
            console.log(`this.isLoadingScores: ${this.isLoadingScores}
            this.isLoadingTournament: ${this.isLoadingTournament} 
            result of or statement: ${this.isLoadingScores || this.isLoadingTournament}`);
            return this.isLoadingScores || this.isLoadingTournament;
        },

        // teamsByRound(round) {
        //     let teamsForThisRound = [];

        //     const start = this.calculateStartPosition(round);
        //     const finish = this.calculateStartPosition(round + 1)
        //     //scores is ordered by bracket position, so we can iterate through.
        //     for (let i = start; i<finish; i++) {
        //         const score = this.scores[i];
        //         for (let j = 0; j < this.teams.length; j++) {
        //             if (score.teamId == this.teams[j].teamId) {
        //                 teamsForThisRound.push(this.teams[j]);
        //             }
        //         }
        //     }
        //     return teamsForThisRound;

        // },
        rounds() {
            let count = 0;
            let iterator = this.teams.length;
            while (iterator > 1) {
                iterator /= 2;
                count++;
            }
            return count;
        },
        positions() {
            return 2 ^ (this.rounds + 1) - 1;
        },
        isNotTournamentHost() {
            return !this.isCurrentUserTournamentHost;
        },
        isTournamentHost() {
            return this.isCurrentUserTournamentHost;
        },

    },

    data() {
        return {
            teams: [],
            isCurrentUserTournamentHost: false,
            tournament: {},
            scores: [],
            isLoadingScores: true,
            isLoadingTournament: true,
        }
    },

    methods: {

        teamsByRound(round) {
            let teamsForThisRound = [];
            const start = this.calculateStartPosition(round);
            const finish = this.calculateStartPosition(round + 1);

            console.log(`start: ${start} finish: ${finish}`);

            //scores is ordered by bracket position, so we can iterate through.
            for (let i = (start); i < finish; i+=1 ) {
                console.log(`i: ${i}`);
                const score = this.scores[i-1];

                if (score == undefined || score == null) {
                    break;
                }

                console.log(`score: ${JSON.stringify(score)}
                scores[0]: ${this.scores[0]}`)

                for (let j = 0; j < this.teams.length; j++) {
                    const teamToCheck = this.teams[j];

                    console.log(`teamToCheck: ${JSON.stringify(teamToCheck)}`)

                    if (score.teamId == teamToCheck.teamId) {
                        teamsForThisRound.push(this.teams[j]);

                        console.log(`teamsForThisRound after push: ${JSON.stringify(teamsForThisRound)}
                        teams in data after push: ${JSON.stringify(this.teams)}`);
                        break;
                    }
                }
            }
            return teamsForThisRound;

        },
        calculateStartPosition(round) {
            let holder = 1;
            let iterator = round;
            let count = 1;

            while (count < round) {
                holder = holder + (2 ** (this.rounds - (count - 1)));
                ++count;
            }

            return holder;
        },
        pushToSeeTournamentJoinRequestsView() {
            this.$router.push({
                name: 'see-tournament-join-requests-view',
                params: { tournamentId: this.tournament.tournamentId }
            });
        },
        pushToSendTournamentJoinRequestView() {
            this.$router.push({
                name: 'send-tournament-join-request-view',
                params: { tournamentId: this.tournament.tournamentId }
            });
        },
        roundToString(round) {
            let returnString = 'round-';
            switch (round) {
                case (round == 1):
                    return returnString + 'one';
                case (round == 2):
                    return returnString + 'two';
                case (round == 3):
                    return returnString + 'three';
                case (round == 4):
                    return returnString + 'four';
                case (round == 5):
                    return returnString + 'five';
                case (round == 6):
                    return returnString + 'six';
                case (round == 7):
                    return returnString + 'seven';
                case (round == 8):
                    return returnString + 'eight';
                case (round == 9):
                    return returnString + 'nine';
                case (round == 10):
                    return returnString + 'ten';
            }
        },
    },

    created() {
        TournamentService.getTournamentDetail(this.$route.params.tournamentId)
            .then(response => {
                this.teams = response.data.participants;
                this.tournament = response.data;
                this.isLoadingTournament = false;
            })
            .catch(error => {
                this.$store.commit("SET_NOTIFICATION", "getTournamentDetail method in tournament view failed");
            });

        TournamentService.checkUserForTournamentHost(this.$route.params.tournamentId, this.$store.state.user.id)
            .then(response => {
                this.isCurrentUserTournamentHost = response.data;
            })
            .catch(error => {
                this.$store.commit("SET_NOTIFICATION", "isCurrentUserTournamentHost method in tournament view failed");
            });

        ScoreService.getListOfScoresByTournamentId(this.$route.params.tournamentId)
            .then(response => {
                if (response.status == 200) {
                    this.scores = response.data;
                    this.isLoadingScores = false;
                }
            })
            .catch(error => {
                this.$store.commit('SET_NOTIFICATION', 'issue getting list of scores by tournamentId in TournamentView.');
            });
    }
};
</script>


<style scoped>
/* This refers to our sources html body - we will want our own fonts and such. 
*/
/* body {
    font-family: 'Istok Web', sans-serif;
    background: url("http://picjumbo.com/wp-content/uploads/HNCK2189-1300x866.jpg") no-repeat #000;
    background-size: cover;
    min-height: 100%;
    margin: 0;
} */

#bracket {
    /* overflow: hidden; */
    background-color: #e1e1e1;
    background-color: rgba(225, 225, 225, 0.9);
    padding-top: 20px;
    font-size: 12px;
    padding: 40px 0;
    height: 100vh;
}

.container {
    max-width: 1100px;
    margin: 0 auto;
    display: block;
    /* display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: -webkit-flex; */
    display: flex;
    /* -webkit-flex-direction: row; */
    flex-direction: row;
    min-height: 100%;
}

.split {
    display: block;
    float: left;
    /* display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex; */
    display: flex;
    width: 42%;
    -webkit-flex-direction: row;
    -moz-flex-direction: row;
    flex-direction: row;
}

/* This refers to the source's semi-final to final to champion section. 
We aren't using that section as of yet. 
*/

/* .champion {
    float: left;
    display: block;
    width: 16%;
    -webkit-flex-direction: row;
    flex-direction: row;
    -webkit-align-self: center;
    align-self: center;
    margin-top: -15px;
    text-align: center;
    padding: 230px 0\9;
} 

.champion i {
    color: #a0a6a8;
    font-size: 45px;
    padding: 10px 0;
} */

.round {
    display: block;
    float: left;
    /* display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex; */
    display: flex;
    /* -webkit-flex-direction: column; */
    flex-direction: column;
    width: 95%;
    width: 30.8333%\9;
}

/* .split-two {} */

/* moved to TournamentRound.vue, leaving as is because .split-one has a ref */

.split-one .round {
    margin: 0 2.5% 0 0;
}

.split-two .round {
    margin: 0 0 0 2.5%;
}

/* Moved the next 5 to TournamentMatchUp.vue, copied the last two to TournamentRound.vue */

/* .matchup {
    margin: 0;
    width: 100%;
    padding: 10px 0;
    height: 60px;
    -webkit-transition: all 0.2s;
    transition: all 0.2s;
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
} */

.round-two :deep(.matchup) {
    margin: 0;
    height: 60px;
    padding: 50px 0;
}

.round-three :deep(.matchup) {
    margin: 0;
    height: 60px;
    padding: 130px 0;
}

/* adding round-details to TournamentRound.vue */
.round-details {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 13px;
    color: #2C7399;
    text-transform: uppercase;
    text-align: center;
    height: 40px;
}

.champion li,
.round li {
    background-color: #fff;
    box-shadow: none;
    opacity: 0.45;
}

.current li {
    opacity: 1;
}

.current li.team {
    background-color: #fff;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    opacity: 1;
}

.vote-options {
    display: block;
    height: 52px;
}

.share .container {
    margin: 0 auto;
    text-align: center;
}

.share-icon {
    font-size: 24px;
    color: #fff;
    padding: 25px;
}

.share-wrap {
    max-width: 1100px;
    text-align: center;
    margin: 60px auto;
}

.final {
    margin: 4.5em 0;
}

@-webkit-keyframes pulse {
    0% {
        -webkit-transform: scale(1);
        transform: scale(1);
    }

    50% {
        -webkit-transform: scale(1.3);
        transform: scale(1.3);
    }

    100% {
        -webkit-transform: scale(1);
        transform: scale(1);
    }
}

@keyframes pulse {
    0% {
        -webkit-transform: scale(1);
        -ms-transform: scale(1);
        transform: scale(1);
    }

    50% {
        -webkit-transform: scale(1.3);
        -ms-transform: scale(1.3);
        transform: scale(1.3);
    }

    100% {
        -webkit-transform: scale(1);
        -ms-transform: scale(1);
        transform: scale(1);
    }
}

.share-icon {
    color: #fff;
    opacity: 0.35;
}

.share-icon:hover {
    opacity: 1;
    -webkit-animation: pulse 0.5s;
    animation: pulse 0.5s;
}

.date {
    font-size: 10px;
    letter-spacing: 2px;
    font-family: 'Istok Web', sans-serif;
    color: #3F915F;
}



@media screen and (min-width: 981px) and (max-width: 1099px) {
    .container {
        margin: 0 1%;
    }

    .champion {
        width: 14%;
    }

    .split {
        width: 43%;
    }

    .split-one .vote-box {
        margin-left: 138px;
    }

    .hero p.intro {
        font-size: 28px;
    }

    .hero p.year {
        margin: 5px 0 10px;
    }

}

@media screen and (max-width: 980px) {
    .container {
        -webkit-flex-direction: column;
        -moz-flex-direction: column;
        flex-direction: column;
    }

    .split,
    .champion {
        width: 90%;
        margin: 35px 5%;
    }

    .champion {
        -webkit-box-ordinal-group: 3;
        -moz-box-ordinal-group: 3;
        -ms-flex-order: 3;
        -webkit-order: 3;
        order: 3;
    }

    .split {
        border-bottom: 1px solid #b6b6b6;
        padding-bottom: 20px;
    }

    .hero p.intro {
        font-size: 24px;
    }

    .hero h1 {
        font-size: 3em;
        margin: 15px 0;
    }

    .hero p {
        font-size: 1em;
    }
}


@media screen and (max-width: 400px) {

    .split {
        width: 95%;
        margin: 25px 2.5%;
    }

    .round {
        width: 21%;
    }

    .current {
        -webkit-flex-grow: 1;
        -moz-flex-grow: 1;
        flex-grow: 1;
    }

    .hero h1 {
        font-size: 2.15em;
        letter-spacing: 0;
        margin: 0;
    }

    .hero p.intro {
        font-size: 1.15em;
        margin-bottom: -10px;
    }

    .round-details {
        font-size: 90%;
    }

    .hero-wrap {
        padding: 2.5em;
    }

    .hero p.year {
        margin: 5px 0 10px;
        font-size: 18px;
    }

}
</style>