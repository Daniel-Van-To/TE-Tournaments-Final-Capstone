<template>
  <div class="home">
    <h1>About Us</h1>
    <p></p>
    <div class="theHomies">
      <section class="oneOfUs">
        <img src="../assets/images/Selected/headshot4 (1).jpg">
        <div class="reverser">
          <h1>Revathi Ajjarapu</h1>
          <p> My objective has always been to become a software engineer. 
            With a fair amount of gap in my career, which cannot keep me from being successful, 
            I started my journey towards achieving my goal, by enrolling in a full-stack Java bootcamp at Tech Elevator. 
            I am looking forward to get a job as a junior software engineer, where I can continue to learn endlessly, 
            and build my career.</p>
        </div>
      </section>
      <section class="oneOfUs">
        <img src="../assets/images/Selected/Linked in profile picture.jpg">
        <div class="reverser">
          <h1>Daniel To</h1>
          <p> Hi my name is Daniel. I attended the University of Texas at Arlington
            where I received both my Bachelors and Masters in Biomedical Engineering.
            After college, I joined a startup company as a Laser Field Service Technician
            where I was responsible for scheduling service with the hospital staff, and
            completing laser maintenance and repairs.</p>
          <p> I studied Java for the 4 years I was in High school, took several courses in C in college,
            and even taught my self Python to complete my Senior capstone project at the university. Looking forward
          to what comes next! </p>
        </div>
      </section>
      <section class="oneOfUs">
        <img src="../assets/images/Selected/xXxDablorDxXx.jpg">
        <div class="reverser">
          <h1>Harry Simpson</h1>
          <p> Just a fella with some ambiguously food-like artwork near his workstation.
            I got a lot of questions about that painting throughout the cohort. It's a painting of saturn. </p>
          <p> I'm big into tournaments and dabbin'. Check out my cool tournament hosting service.
            It's bloody tourna-ment-oh! </p>
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