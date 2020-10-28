<template>
  <div>
    <div class="output">
      <ai-output />
    </div>
  </div>
</template>

<script>
export default {
  name: "AiAnalysis",
  components: {
    AiOutput: () => import("@/components/ai/AiOutput"),
  },
};
</script>
<style scoped>
.input {
  width: 80%;
  margin: auto;
}
</style>
