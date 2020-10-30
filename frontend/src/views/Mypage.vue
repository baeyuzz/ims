<template>
  <v-app>
    <v-main>
      <div class="cal">
      <Calendar/>
      </div>
    </v-main>
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
  width: 80%;
}
</style>
