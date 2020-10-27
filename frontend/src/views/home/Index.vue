<template>
  <div>
    <div class="cal">
      <intro/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {
    Intro: () => import('@/components/Intro'),
  },
}
</script>
<style scoped>

</style>
