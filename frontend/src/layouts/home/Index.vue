<template>
  <v-app>
    <home-app-bar />
    <router-view style="padding-top:80px"/>
    <home-footer />
  </v-app>
</template>

<script>
export default {
  name: "HomeLayout",
  components: {
    HomeAppBar: () => import("@/layouts/home/AppBar"),
    HomeFooter: () => import("@/layouts/home/Footer"),
  },
};
</script>
<style scoped>
</style>