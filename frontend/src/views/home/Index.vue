<template>
  <div>
    <div class="cal">
      <calendar/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {
    Calendar: () => import('@/components/Calendar'),
  },
}
</script>
<style scoped>
.cal {
  margin: auto;
  text-align: center;
  width: 70%;
}
</style>
