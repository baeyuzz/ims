<template>
  <div class="input">
    <div class="intro">
      <h1>AI-Analysis</h1>
      <hr
        style="
          border: solid 1px #1c2341ad;
          width: 25px;
          margin: auto;
          margin-top: 10px;
          margin-bottom: 10px;
        "
      />
      <div id="h2">자기소개서 분석</div>
    </div>

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
