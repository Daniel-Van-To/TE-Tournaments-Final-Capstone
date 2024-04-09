<template>
    <div class="nav-bar">
        <img class="logo" src="../img/te-logo.png" v-on:click.prevent="home" />
        <div class="nav-links-bttns">
            <a class="links"><router-link v-bind:to="{name: 'home'}">Home</router-link></a>
            <a class="links"><router-link v-bind:to="{name: 'browse-tournaments'}">Browse Tournaments</router-link> </a>
            <a class="links" v-if="isLoggedIn"><router-link v-bind:to="{name: 'teams'}">Browse Teams</router-link></a>
            <a class="links" v-if="isLoggedIn"><router-link v-bind:to="{name: 'create-team'}">Create a team</router-link> </a>
            <a class="links"><router-link v-bind:to="{name: 'about-us'}">About Us</router-link></a>
            <a class="links"><router-link v-bind:to="{name: 'faq'}">FAQ</router-link></a>
            <span v-if="isLoggedIn" class="nav-bar-buttons">
                <input type="button" value="Logout" v-on:click.prevent="logout">
                <h4>Hello, {{this.$store.state.user.username}}</h4>
            </span>
            <span v-else class="nav-bar-buttons">
                <input type="button" value="Register" v-on:click.prevent="register">
                <input type="button" value="Log In" v-on:click.prevent="logIn">
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


<style>

.nav-bar {
    display: flex;
    background-color: rgb(171, 174, 177);
    /* justify-content: space-between; */

}

.nav-links-bttns {
    display:flex;
    justify-content:space-around;
    align-items:center;
    flex-grow: 1;
}

.logo {
    display: flex;
    max-width: 10%;
    max-height: 10%;
    flex-grow: 2;

    
}

.links {
    display:flex;
    font-size: 15px;

}

.nav-bar-buttons {
    display:flex;
    align-items: center;
    justify-content: space-between;
}


</style>