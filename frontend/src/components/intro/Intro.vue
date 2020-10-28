<template>
  <div>

      <v-carousel :cycle="true" hide-delimiters height="365" interval="5000">
        <v-carousel-item>
          <img src="@/assets/intro1.png" width="100%" height="100%" />
        </v-carousel-item>
        <v-carousel-item>
          <img src="@/assets/intro2.png" width="100%" height="100%" />
        </v-carousel-item>
      </v-carousel>

  </div>
</template>
<script>
export default {
  name: "Intro",

  data() {
    return {
      items: [
        {
          src:
            "https://user-images.githubusercontent.com/32058420/97274652-4e13e180-1878-11eb-978c-a3f901ee5049.png",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
        },
      ],
    };
  },
};
</script>
<style scoped>
.slide {
  font-size: 20px;
}
</style>
