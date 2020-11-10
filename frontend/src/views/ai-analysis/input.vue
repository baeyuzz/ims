<template>
  <div class="input">
    <img src="@/assets/ai-intro.png" width="100%" />
    <v-toolbar class="toolbar" color="primary" dark>
      <v-toolbar-title>AI-Analysis</v-toolbar-title>
    </v-toolbar>
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
.toolbar {
  display: none;
}
@media (max-width: 950px) {
  .essay {
    width: 90%;
    margin: auto;
    padding: 3% 0 5% 0;
  }
  img {
    display: none;
  }
  .toolbar {
    display: block;
  }
  .text {
    width: 95%;
  }
}
@media (max-width: 450px) {
  img {
    display: none;
  }
  .toolbar {
    display: block;
  }
}
</style>
