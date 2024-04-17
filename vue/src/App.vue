<template>
  <div id="capstone-app">
    <div id="notificationContainer">
      <notification/>
    </div>
    <div id="nav">
      <nav-bar/>
    </div>
    <div id="leftSidebar">
      <img src="./assets/images/Selected/left_Vertical.jpg"/>
    </div>
    <div id="rightSidebar">
      <img src="./assets/images/Selected/Right_Vertical.jpg"/>
    </div>
    <div id="routerHolder">
      <router-view />
    </div>   
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

:root {
  --pinkish: #FAAAA9;
  --darkGreen: #245032;
  --brownGray: #BAA2A2;
  --lightGreen: #AAFAC3;
  --dullGreen: #82A58D;
  --maroonKindof: #7A4D4C;
}

#capstone-app {
  display: grid;
  grid-template-columns: 300px 1fr 300px;
  grid-template-areas: 
    "notif  notif   notif"
    "nav    nav     nav"
    "LSide  router  RSide"
  
    ;

}

#notificationContainer {
  grid-area: notif;
}

#leftSidebar {
  grid-area: LSide;
  position:fixed;
  top:75px;
  left:0px;
  bottom:0px;
}

div img {
  width: 300px;
}

#rightSidebar {
  grid-area: RSide;
  position:fixed;
  top:75px;
  bottom:0px;
  right:0px;
}

#nav {
  grid-area: nav;
  position:fixed;
  width: 100%;
  height: 75px;
  top:0px;
  left:0px;
  z-index: 2;
  
}

#routerHolder {
  grid-area: router;
  top:77px;
  position: relative;
  padding: 0 10px;
}


body {
  margin: 0 0 0 0;
}
#capstone-app{
  /* font-family: "Avenir", Helvetica, Arial, sans-serif; */

  /* text-align: center; */
  /* color: #2c3e50; */
  /* max-width: 800px; */
  /* margin: 60px auto 0 auto;   */
}

</style>