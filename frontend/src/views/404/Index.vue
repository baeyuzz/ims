<template>
  <div class="text-center">
    <img src="@/assets/404.png" width="70%"/>
    <br>
    <v-btn :to="{ name: 'Home' }" style="position: relative; top: -50px;" color="primary">
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
  .text-center{
    height : 100%;
  }
</style>