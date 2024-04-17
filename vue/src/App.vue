<template>
  <div id="capstone-app">
    <div id="notificationContainer">
      <notification/>
    </div>
    <div id="nav">
      <nav-bar/>
    </div>
    <router-view /> 
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';
import Notification from './components/Notification.vue';

export default {
  components:  {
    NavBar,
    Notification,
  },

  created() {
    window.addEventListener('beforeunload', (event) => {
      this.$store.commit("LOGOUT");
      
    })
  },

}

</script>

<style>
/*

#capstone-app{
  font-family: "Avenir", Helvetica, Arial, sans-serif;
   /* -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  max-width: 800px;
  margin: 60px auto 0 auto;  
} */

</style>