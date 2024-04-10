<template>
  <div id="capstone-app">
    <div id="nav">
      <nav-bar/>
    </div>
    <router-view />
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'

export default {
  components:  {
    NavBar,
  }

}



</script>

<style>


</style>