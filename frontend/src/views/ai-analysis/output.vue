<template>
  <div>
    <!-- <div class="intro">
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
    </div> -->
    <img src="@/assets/ai-intro.png" width="100%" />

    <br />
    <div class="comps">
      <chart />
      <result />
    </div>
    <div class="btns">
      <v-btn class="mx-1 mb-8" width="150" color="primary" @click="go2analysis"
        >다시 분석하기</v-btn
      >

      <v-btn
        v-if="$store.state.isLogin"
        width="150"
        class="mx-1 mb-8"
        color="warning"
        @click="save"
        ><v-icon>mdi-download</v-icon> 저장하기</v-btn
      >
      <!-- <v-btn v-if="isLogin"
      class="mx-1 mb-8" color="teal" dark>
        <v-icon>mdi-share</v-icon>
        공유
      </v-btn> -->
    </div>
  </div>
</template>

<script>
import Chart from "chart.js";
import { saveEssay } from "../../api/essay.js";

export default {
  name: "AI-Result",
  components: {
    Chart: () => import("@/components/ai/Chart"),
    Result: () => import("@/components/ai/Result"),
  },
  data: () => ({}),
  methods: {
    go2analysis() {
      this.$router.push("/ai-analysis");
    },
    save() {
      saveEssay();
    },
  },
};
</script>
<style scoped>
.input {
  width: 80%;
  margin: auto;
  color: #7ebda3;
}
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
.btns {
  text-align: center;
}
</style>
