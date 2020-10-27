<template>
  <div>
    <div class="input">
      <ai-input />
    </div>
  </div>
</template>

<script>
export default {
  name: "AiAnalysis",
  components: {
    // AiIntro: () => import("@/components/AiIntro"),
    AiInput: () => import("@/components/AiInput"),
  },
};
</script>
<style scoped>
.input {
  width: 80%;
  margin: auto;
}
</style>
