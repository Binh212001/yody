<script>
import { defineComponent } from "vue";
import { Carousel, Navigation, Pagination, Slide } from "vue3-carousel";
import slider1 from "@/assets/img/slide1.webp";
import slider2 from "@/assets/img/slide2.webp";
import slider3 from "@/assets/img/slide3.webp";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "Basic",
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },
  data() {
    return {
      slides: [
        { name: slider1, url: "" },
        { name: slider2, url: "" },
        { name: slider3, url: "" },
      ],
    };
  },
});
</script>

<template>
  <Carousel :autoplay="2000" :wrap-around="true">
    <Slide v-for="(slide, index) in slides" :key="index">
      <img :src="slide.name" alt="" />
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template>

<style></style>
