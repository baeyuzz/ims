<template>
  <!-- <div class="text-center" style="background-color: #7FDBFF;"> -->
  <div class="text-center">
  <!-- <div class="text-center" style="background-color: #ACE7FF;"> -->
    <!-- <br> -->
    <img src="@/assets/404.png" width="70%"/>
    <br>
    <v-btn :to="{ name: 'Home' }" style="position: relative; top: -50px;  ">
      홈으로 돌아가기

      <v-icon>mdi-home-outline</v-icon>
      </v-btn>
      <br>
  </div>
</template>

<script>
export default {
  name: "FourOhFour",

  provide: {
    heading: { align: "center" },
  },
};
</script>

<style scoped>
  div {
    background-color: rgb(194, 222, 239);
  }
</style>