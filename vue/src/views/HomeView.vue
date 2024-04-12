<template>
  <div class="home">
    <h1>Home</h1>
    {{ JSON.stringify(this.$store.state.userPackage.myTeams) }}
    
  </div>
</template>

<script>

export default {

  components: {
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
