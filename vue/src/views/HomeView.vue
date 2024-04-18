<template>
  <div class="home">
    <h1>Home</h1>
    <!-- <slide-show/> -->
    <vueper-slides fade :touchable="false" slide-content-outside="bottom">
      <vueper-slide 
      v-for="(slide,i) in slides"
      :content="slide.content"
      :link="slide.link"
      :key="i" 
      :title="slide.title"
      :image="slide.image"/>
    </vueper-slides>
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
          title: 'Slide #1',
          content: 'Slide 1 content',
          image: '/florian-cordier-zOTpWmLyxdU-unsplash.jpg',
          link: 'http://127.0.0.1:5173/browse-tournaments'
        },
        {
          title: 'Slide #2',
          content: 'Slide 2 content',
          image: '/xavier-foucrier-UlGCxZVw078-unsplash.jpg'
        },
        {
          title: 'Slide #3',
          content: 'Slide 3 content',
          image: '/carouselFootball.jpg'
        },
        {
          title: 'Slide #4',
          content: 'Slide 4 content',
          image: '/cricket.jpg'
        },
        {
          title: 'Slide #5',
          content: 'Slide 5 content',
          image: '/josh-hemsley-e-f17L4EBBc-unsplash.jpg'
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
    font-size: 14px;
    font-weight: bold;

  }
</style>


