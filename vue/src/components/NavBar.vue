<template>
    <div class="nav-bar">
        <img class="logo" src="../img/logo3.jpg" v-on:click.prevent="home" />
        <div class="nav-links-bttns">
        
            <router-link class="links" v-bind:to="{name: 'home'}">Home</router-link>
            <router-link class="links" v-bind:to="{name: 'browse-tournaments'}">Browse Tournaments</router-link>
            <!-- <router-link class="links" v-bind:to="{name: 'browse-tournaments-temp'}">Browse Teams (TEMP *WIP*)</router-link> -->
            <router-link class="links" v-if="isLoggedIn" v-bind:to="{name: 'teams'}">Browse Teams</router-link>
            <router-link class="links" v-if="isLoggedIn" v-bind:to="{name: 'create-team'}">Create a team</router-link>
            <router-link class="links" v-bind:to="{name: 'about-us'}">About Us</router-link>
            <router-link class="links" v-bind:to="{name: 'faq'}">FAQ</router-link>

            <span v-if="isLoggedIn" class="nav-bar-buttons">
                <input class="loggedIn" type="button" value="Logout" v-on:click.prevent="logout">
                <h4 class="loggedIn">Hello, {{this.$store.state.user.username}}</h4>
            </span>
            <span v-else class="nav-bar-buttons">
                <input class="loggedOut" type="button" value="Register" v-on:click.prevent="register">
                <input class="loggedOut" type="button" value="Log In" v-on:click.prevent="logIn">
            </span>
        </div>
    </div>
    
</template>


<script>
export default {
    data() {
        return { };
    },

    computed : {
        isLoggedIn() {
            return this.$store.state.token;
        }
    },
    methods: {

        register() {
            this.$router.push({name:'register'});
        },
        logIn() {
            this.$router.push({name:'login'});
        },
        home() {
            this.$router.push({name:'home'});
        },
        logout() {
            this.$store.commit('LOGOUT');
            this.$router.push({name:'home'});
        }
    }
}
</script>


<style scoped>

.nav-bar {
    margin: none;
    padding: none;
    display: flex;
    background-color: var(--pinkish);
    /* justify-content: space-between; */
    height: fit-content;

}

.nav-links-bttns {
    /* font-family: "Avenir", Helvetica, Arial, sans-serif; */
    display:flex;
    justify-content:right;
    align-items:center;
    flex-grow: 1;

    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
    color: black;
}

input {
    border: 1px solid black;
    cursor: pointer;
}

.logo {
    display: flex;
    max-width: 10%;
    max-height: 10%;
    flex-grow: 2;
    height: fit-content;
    align-self: center;
    margin: 0.5rem;
    cursor: pointer;
}

.links {
    display:flex;
    font-size: 15px;
    margin-right: 0.25rem;
    margin-left: 0.25rem;
    border-radius: 5px;

}

.nav-bar-buttons {
    display:flex;
    align-items: center;
    justify-content: space-between;
    margin-left: 1rem;
    margin-right: 1rem;

    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;

}

span input {
    margin-left: 0.25rem;
    margin-right: 0.25rem;
}

.loggedIn {
    margin-left: 0.25rem;
    margin-right: 0.25rem;
}

.links {
    text-decoration: none;
    color:black;
    background-color: white;
    align-self: center;
    padding-top: 0.5rem;
    padding-bottom: 0.5rem; 
    padding-left: 0.1rem;
    padding-right: 0.1rem;
    border: 1px solid black;
}

.loggedIn,
.loggedOut {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
}

.links:hover {
    background-color: gray;
}


</style>