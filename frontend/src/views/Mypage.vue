<template>
  <v-app>
    <v-content>
      <Calendar></Calendar>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "Mypage",
  components: {
    Calendar: () => import("@/components/Calendar"),
  },
};
</script>
<style scoped>
.cal {
  margin: auto;
  text-align: center;
  width: 70%;
}
</style>
