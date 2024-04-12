<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
        <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import teamService from "../services/TeamService";

export default {
  components: {
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService.login(this.user)
        .then(response => {
          console.log('response status: '+ response.status)
          if (response.status == 200) {
            console.log('response status: '+ response.status)
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            const userId = response.data.user.id;
            const teams = teamService.returnTeamsAndHandleErrors(userId);

            this.$store.commit("UPDATE_MY_TEAMS", teams);
            
            // this.$store.commit("UPDATE_MY_TOURNAMENTS", this.)

            this.$router.push({name: 'home'});
          }
        })
        .catch( (error) => {
          console.log('error')
          if (error.response) {
            console.log(error.response.status);
          }
          else if(error.request) {
            console.log('error with request, no response')
          }
          else {
            console.log('no request or response, we have a code problem' + error.message);
          }
        });
    },

  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}
</style>