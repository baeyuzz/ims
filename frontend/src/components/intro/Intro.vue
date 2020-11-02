<template>
  <div style="margin : 0; padding : 0; width: 100%;">
    <v-carousel :cycle="true" hide-delimiters interval="5000" width="100%" height="10%">
        <v-carousel-item>
          <img src="@/assets/intro1.png" width="100%"/>
        </v-carousel-item>
        <v-carousel-item>
          <img src="@/assets/intro2.png" width="100%"/>
        </v-carousel-item>
      </v-carousel>
  </div>
</template>
<script>
export default {
  name: "Intro",

  data() {
    return {

    };
  },
};
</script>
<style scoped>
.slide {
  font-size: 20px;
}
</style>
