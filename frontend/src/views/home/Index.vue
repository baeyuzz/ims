<template>
  <div>
    <intro />
    <func />
    <about-us />
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {
    Intro: () => import("@/components/intro/Intro"),
    AboutUs: () => import("@/components/intro/AboutUs"),
    Func: () => import("@/components/intro/Func"),
  },
};
</script>
<style scoped>
</style>
