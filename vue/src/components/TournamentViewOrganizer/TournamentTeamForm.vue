<template>
    <!-- <div>Loading...</div> -->
    <form class="placeTeams">
        <div class="formTitle">Assign Teams</div>
        <div v-for="position in size" :key="position" class="formField">
            <label :for="this.returnInputId(position)">Bracket Position {{ position }}: {{ position > 9 ? " " : "" }} </label>
            <select v-model="this.positions[position-1]" :id="this.returnInputId(position)" :name="this.returnInputId(position)">
                <option value="">-----</option>
                <option v-for="team in teams" :key="team.teamId" :value="team.teamId">
                    {{ team.teamName }}
                </option>
            </select>
        </div>
        <button class="input" @click.prevent="this.assignValues()">Assign Automatically</button>
        <button class="input" @click.prevent="this.savePositions()">Save</button>
        <input class="input" type="submit" @click.prevent="this.showWarning()"/>
        {{ this.positions }}
        <div v-show="this.warn" class="warning">
            Warning!
            <br><br>
            Once you submit, you will be unable to edit starting bracket position. 
            <br><br>
            Proceed?
            <span class="buttonHolder">
                <button @click.prevent="this.submit()">Yes</button>
                <button @click.prevent="this.toggleWarn()">No</button>
            </span>
        </div>
    </form>
</template>


<script>
import ScoreService from '../../services/ScoreService';
import TournamentService from '../../services/TournamentService';


export default {

    computed: {
        readyToStart() {
            for (let i = 0; i < this.positions.length; i++) {
                if (this.positions[i] == '') {
                    return false;
                }
            }
            return true;
        }
    },  

    methods: {
        returnInputId(position) {
            return `team-${position}`;
        },

        savePositions() {
            let scoreList = [];

            for (let i = 0; i < this.positions.length; i++) {
                if (this.positions[i] != '') {
                    scoreList.push({
                    tournamentId: this.tournament.tournamentId,
                    bracketPosition: (i+1),
                    teamId: this.positions[i],
                    score: '',
                });
                }
            }

            ScoreService.initializeTournamentScores(this.tournament.tournamentId, scoreList)
                .then()
                .catch(error => {
                    this.$store.commit('SET_NOTIFICATION', 'error saving scores: ' + error.message);
                });

        },

        showWarning() {
            this.warn = true;
        },

        toggleWarn() {
            if (this.warn) {
                this.warn = false;
            }
            else {
                this.warn = true;
            }
        },

        submit() {
            if (this.readyToStart) {
              this.changeTournamentStatus();
              this.initializeScores();
            }
            else {
                this.$store.commit('SET_NOTIFICATION', 'Insufficient teams - press save to hold current positions, or add more teams to the tournament.')
            }
        },

        changeTournamentStatus() {
            let tournamentToUpdate = this.tournament;
            tournamentToUpdate.tournamentStatus = 'o';
            tournamentToUpdate.acceptingTeams = false;
            TournamentService.updateTournament(tournamentToUpdate)
                .then(response => {
                    if (response.status == 200) {
                        this.$store.commit('SET_NOTIFICATION', 
                        {message: 'success updating tournament status', type: 'success',}
                    )}
                })
                .catch(error => {
                    this.$store.commit('SET_NOTIFICATION', 'error updating tournament status' + error.message);
                })
        },

        initializeScores() {
            //Score model:
                //score_id
                //tournament_id
                //team_id
                //bracket_position
                //score

            //create scores
            let scoresList = [];
            for (let i = 0; i < this.positions.length; i++) {
                scoresList.push( {
                    tournamentId: this.tournament.tournamentId,
                    bracketPosition: (i+1),
                    teamId: this.positions[i],
                    score: '',
                })
            }
            ScoreService.initializeTournamentScores(this.tournament.tournamentId, scoresList)
                // .then(response => {
                //     if (response.data) {
                        
                //     }
                // })
                    .catch(error => {
                        this.$store.commit('SET_NOTIFICATION', 'error initializing scores');
                    });

        },

        assignValues() {
            for(let i = 0; i < this.teams.length; i++) {
                this.positions[i] = this.teams[i].teamId;
            }
        }
    },  

    props: [
        'teams',
        'size',
        'tournament',
    ],

    data() {
        return {
            //index = bracketPosition-1
            //value = team.teamId
            positions: [],
            warn: false,
            isLoadingScores: true,
        }
    },

    beforeMount() {

        for(let i = 0; i < this.size; i++) {
            this.positions.push('');
        }

        ScoreService.getListOfScoresByTournamentId(this.tournament.tournamentId)
            .then(response => {
                if (response.data) {
                    response.data.forEach((score) => {
                        this.positions[score.bracketPosition-1] = score.teamId
                    })
                }
                this.isLoadingScores=false;
            })
            .catch(error =>{
                this.$store.commit('SET_NOTIFICATION', 'error loading existing tournament scores' + error.message);
            })
    }



}
</script>

<style scoped>

form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-content: center;
    margin:0 10px 10px 10px;
    font-size: 14px;
    max-width: 250px;

}

.warning {
    display: flex;
    flex-direction: column;
    width: 210px;
}

.buttonHolder {
    display: flex;
    flex-grow: 1;
    justify-content: center;
    width: 250px;
}

.buttonHolder button {
    margin: 0 10px;
}

.formField {
    display: flex;
    justify-self: center;
    flex-grow: 1;
}

select {
    width: 100px
}

.input {
    margin: 5px;
    width: 200px;
}

.formField:nth-child(odd) {
    margin-bottom: 10px;
}

.formTitle {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
    color: black;
    background-color: var(--pinkish);
    border: 2px solid var(--darkGreen);
    text-transform: uppercase;
    text-align: center;
    align-content: center;
    height: 40px;
    width: fit-content;
    padding: 0 0.5rem 0 0.5rem;
    justify-self: center;
    display: block;
    align-self: center;
    margin-bottom: 10px;
}
</style>