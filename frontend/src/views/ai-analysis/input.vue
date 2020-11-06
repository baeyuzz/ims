<template>
  <div class="input">
    <img src="@/assets/ai-intro.png" width="100%" />
    <div class="text">
      <ai-input />
    </div>
  </div>
</template>

<script>
export default {
  name: "AiAnalysis",
  components: {
    AiInput: () => import("@/components/ai/AiInput"),
  },
};
</script>
<style scoped>
.text {
  width: 65%;
  margin: auto;
}

.intro {
  background-color: #ebf0ff;
  color: #1c2341;
  padding: 70px;
  text-align: center;
}

#h2 {
  font-size: 20px;
}
</style>
