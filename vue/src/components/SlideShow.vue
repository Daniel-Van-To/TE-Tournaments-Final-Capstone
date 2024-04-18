<template>
    <!-- Slideshow container -->
    <div class="slideshow-container">

        <!-- Full-width images with number and caption text -->
        <div class="mySlides fade" v-if="this.slideIndex == 1">
            <img class="slide-images" src="../assets/images/Selected/florian-cordier-zOTpWmLyxdU-unsplash.jpg">
            <div class="text">Caption One</div>
        </div>
        <div class="mySlides fade" v-else-if="this.slideIndex == 2">
            <img class="slide-images" src="../assets/images/Selected/xavier-foucrier-UlGCxZVw078-unsplash.jpg">
            <div class="text">Caption Two</div>
        </div>

        <div class="mySlides fade" v-else-if="this.slideIndex == 3">
            <img class="slide-images" src="../assets/images/Selected/carouselFootball.jpg">
            <div class="text">Caption Three</div>
        </div>

        <div class="mySlides fade" v-else-if="this.slideIndex == 4">
            <img class="slide-images" src="../assets/images/Selected/pexels-patrick-case-3628912.jpg">
            <div class="text">Caption Four</div>
        </div>

        <div class="mySlides fade" v-else-if="this.slideIndex == 5">
            <img class="slide-images" src="../assets/images/Selected/josh-hemsley-e-f17L4EBBc-unsplash.jpg">
            <div class="text">Caption Five</div>
        </div>

        <!-- Next and previous buttons -->
        <a class="prev" v-on:click="this.plusSlides(-1)">&#10094;</a>
        <a class="next" v-on:click="this.plusSlides(1)">&#10095;</a>
    </div>
    <br>

    <!-- The dots/circles -->
    <div style="text-align:center">
        <span class="dot" v-on:click="currentSlide(1)"></span>
        <span class="dot" v-on:click="currentSlide(2)"></span>
        <span class="dot" v-on:click="currentSlide(3)"></span>
        <span class="dot" v-on:click="currentSlide(4)"></span>
        <span class="dot" v-on:click="currentSlide(5)"></span>
    </div>
</template>

<script>

export default {
    data() {
        return {
            slideIndex: 1,
        };
    },
    methods: {
        plusSlides(modifier) {
            this.slideIndex += modifier;
            if (this.slideIndex > 5) {
                this.slideIndex = 1;
            }

            if (this.slideIndex < 1) {
                this.slideIndex = 5;
            }
        },

        currentSlide(index) {
            this.slideIndex = index;
        },

    }
}
</script>

<style>
* {
    box-sizing: border-box
}

/* Slideshow container */
.slideshow-container {
    max-width: 1000px;
    height: 100%;
    width: 100%;
    overflow: hidden;
}

/* .mySlides {
  display: none;
} */

/* Next & previous buttons */
.prev,
.next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    margin-top: -22px;
    padding: 16px;
    color: white;
    font-weight: bold;
    font-size: 18px;
    transition: 0.6s ease;
    border-radius: 0 3px 3px 0;
    user-select: none;
}

/* Position the "next button" to the right */
.next {
    right: 0;
    border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover,
.next:hover {
    background-color: rgba(0, 0, 0, 0.8);
}

/* Caption text */
.text {
    color: #f2f2f2;
    font-size: 15px;
    padding: 8px 12px;
    position: absolute;
    bottom: 8px;
    width: 100%;
    text-align: center;
}

.slide-images {
    width: 50%;
    height: 50%;
    border: 20px solid #555;
    object-fit: fill;
    margin-left: 180px;
}

/* Number text (1/3 etc) */
.numbertext {
    color: #f2f2f2;
    font-size: 12px;
    padding: 8px 12px;
    position: absolute;
    top: 0;
}

/* The dots/bullets/indicators */
.dot {
    cursor: pointer;
    height: 15px;
    width: 15px;
    margin: 0 2px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
}

.active,
.dot:hover {
    background-color: #717171;
}

/* Fading animation */
.fade {
    animation-name: fade;
    animation-duration: 1.5s;
}

@keyframes fade {
    from {
        opacity: .4
    }

    to {
        opacity: 1
    }
}</style>