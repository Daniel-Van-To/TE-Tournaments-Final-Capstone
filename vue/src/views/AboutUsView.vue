<template>
  <div class="home">
    <h1>About Us</h1>
    <p></p>
    <div class="theHomies">
    <section class="oneOfUs">
      <img>
      <div class="reverser">
        <h1>Revathi Ajjarapu</h1>
        <p> description </p>
      </div>
    </section>
    <section class="oneOfUs">
      <img>
      <div class="reverser">
        <h1>Daniel To</h1>
        <p> description </p>
      </div>
    </section>
    <section class="oneOfUs">
      <img src="../assets/images/Selected/xXxDablorDxXx.jpg">
      <div class="reverser">
        <h1>Harry Simpson</h1>
        <p> Just a fella with some ambiguous food-like artwork near his workstation.
          I got a lot of questions about that painting throughout the cohort. Its saturn. </p>
          <p> I just like dabbin' </p>
      </div>
    </section>
  </div>
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

<style scoped>

.oneOfUs {
  display: flex;
  border: 2px solid var(--purple);
  background-color: white;
  margin: 2rem;
}

.reverser {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-grow: 2;
}

.oneOfUs img {
  max-height: 200px;
  width: auto;
  border: 2px solid black;
  margin: 1rem;
  justify-self: left;
}


</style>