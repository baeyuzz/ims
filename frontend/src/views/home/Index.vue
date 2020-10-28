<template>
  <div>
    <div class="cal">
      <intro />
    </div>
    <func/>
    <about-us/>

  </div>
</template>

<script>
export default {
  name: "Home",
  components: {
    Intro: () => import("@/components/intro/Intro"),
    AboutUs : () => import("@/components/intro/AboutUs"),
    Func : () => import("@/components/intro/Func")
  },

};
</script>
<style scoped>
.cards {
  display: flex;
  text-align: left;
  justify-content: center;
}
</style>
