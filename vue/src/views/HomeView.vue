<template>
  <div class="home">
    <div id="header">
      <h1>Welcome to Tourna-Mental!</h1>
      <h3 class="home-view-text">A dynamic tournament hosting webpage where you can manage all your tournaments in one place. </h3>
    </div>
    
    <!-- <slide-show/> -->
    <vueper-slides :touchable="false" slide-content-outside="bottom" autoplay>
      <vueper-slide 
      v-for="(slide,i) in slides"
      :content="slide.content"
      :link="slide.link"
      :key="i" 
      :title="slide.title"
      :image="slide.image"/>
    </vueper-slides>

    <div class="description">
      <h3 class="home-view-text">Beautifully organized tournament brackets that show head-to-head 
        matchup between individual teams. The winners of each matchup move inward until only 
        two remain at the root to play for the championship.</h3>
      <h3 class="home-view-text">Need to host tournaments? its time to explore Tourna-Mental!</h3>
    </div>
  </div>
</template>

<script>
import {VueperSlides, VueperSlide} from 'vueperslides';
import 'vueperslides/dist/vueperslides.css';

export default {

  components: {
    VueperSlides,
     VueperSlide
  },

  data() {
    return {

      slideIndex: 0,
      slides: [
        {
          // title: 'Slide #1',
          content: 'Browse Chess Tournaments!',
          image: '/florian-cordier-zOTpWmLyxdU-unsplash.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments/chess'
        },
        {
         // title: 'Browse Kayaking Tournaments in an Area Near You!',
           content: 'Browse Kayaking Tournaments in an Area Near You!',
          image: '/xavier-foucrier-UlGCxZVw078-unsplash.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments/kayaking'
        },
        {
         // title: 'Slide #3',
          content: 'Come Take a Peek at Soccer Tournaments!',
          image: '/carouselFootball.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments/soccer'
        },
        {
          // title: 'Slide #4',
          content: 'Browse our Cricket Tournaments Fun for All Ages!',
          image: '/cricket.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments/cricket'
        },
        {
          // title: 'Slide #5',
          content: 'Browse Baseball Tournaments!',
          image: '/josh-hemsley-e-f17L4EBBc-unsplash.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments/baseball'
        },
        
      ]

    }
  },


  methods: {
    pushToBrowseTournaments() {
      this.$router.push({name: 'browse-tournaments'});
    }

  },
  created() {

  }
};

</script>

<style>
  .vueperslide__content-wrapper {
    display: flex;
    justify-content: center;

  }
  .vueperslide__title {
    padding-right: 20px;
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 14px;
    font-weight: bold;
  }
  .vueperslide__content {
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 20px;
    font-weight: bold;
    padding-bottom: 4rem;


  }

  h1{
    text-align: center;
    font-family: 'Roboto Condensed', sans-serif;
    
  }

  .home-view-text {
    text-align: center;
    font-family: 'Roboto Condensed', sans-serif;
  }

  .description {
    border: 2px solid var(--purple);
    border-radius:10px;
    
  }

  #header {
    /* background-color: darkgrey; */
  }

  .vueperslides__track {
    position: absolute;
    top: 0;
    height: 100%;
    left: 0;
    right: 0;
    overflow: hidden;
    z-index: 1;
    border-radius:10px;

}
</style>


