<template>
  <v-app>
    <home-app-bar />
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <router-view />
    <home-footer />
  </v-app>
</template>

<script>
  export default {
    name: 'HomeLayout',
    components: {
      HomeAppBar: () => import('@/layouts/home/AppBar'),
      HomeFooter: () => import('@/layouts/home/Footer'),
    },
  }
</script>
