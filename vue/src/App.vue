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
import NavBar from './components/NavBar.vue'
import Notification from './components/Notification.vue';

export default {
  components:  {
    NavBar,
    Notification,
  }

}



</script>

<style>


</style>