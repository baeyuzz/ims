<template>
  <div>
    <img src="@/assets/ai-intro.png" width="100%" />
    <v-toolbar class="toolbar" color="primary" dark>
      <v-toolbar-title>AI-Analysis</v-toolbar-title>
    </v-toolbar>
    <div class="comps">
      <chart />
      <result />
    </div>
    <div class="essay">
      <h2>자기소개서</h2>
      <br />
      {{ this.$store.state.content }}
    </div>

    <div style="text-align: center; margin: auto; margin-bottom: 2%">
      <v-btn
        v-if="$store.state.isLogin"
        width="150"
        color="primary"
        @click="go2mypage"
        >마이페이지로 이동</v-btn
      >
    </div>
  </div>
</template>

<script>
import Chart from "chart.js";
import { saveEssay } from "@/api/essay.js";

export default {
  name: "My-Essay",
  components: {
    Chart: () => import("@/components/ai/Chart"),
    Result: () => import("@/components/ai/Result"),
  },
  data: () => ({}),
  methods: {
    go2mypage() {
      this.$router.push("/mypage");
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
.essay {
  width: 60%;
  margin: auto;
  padding: 2%;
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
