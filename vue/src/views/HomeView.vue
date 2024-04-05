<template>
  <div class="home">
    <NavBar/>
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';

export default {
  components: {
    NavBar
  },
  data() {
    return {

    }
  },


  methods: {

  },
  created() {

  }
};
</script>
