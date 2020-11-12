<template>
  <div class="share">
    <img src="@/assets/essays.png" width="100%" />
    <v-toolbar class="toolbar" flat color="primary" dark>
      <v-toolbar-title>자소서 공유 게시판</v-toolbar-title>
    </v-toolbar>
    <div class="ex">
      <v-select :items="labels" v-model="selected" label="역량"></v-select>
      <v-expansion-panels>
        <v-expansion-panel v-for="(list, i) in essay" :key="i">
          <v-expansion-panel-header
            style="font-weight: bold"
            v-if="selected == result[i] || selected == '전체보기'"
          >
            {{ result[i] }} {{ percent[i].toString().substring(0, 5) }} % 자소서
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            {{ list.content }}
          </v-expansion-panel-content>
          <v-expansion-panel-content style="text-align: right">
            {{ list.content.length }}자
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
  </div>
</template>

<script>
import { createInstance } from "@/api/index.js";

export default {
  name: "Share",
  data: () => ({
    essay: [],
    result: [],
    percent: [],
    labels: [
      "전체보기",
      "글로벌",
      "도전정신",
      "성실성",
      "의사소통능력",
      "성취지향성",
      "책임감",
      "창의성",
      "정직함",
    ],
    selected: "전체보기",
  }),
  methods: {},
  created() {
    const instance = createInstance();

    instance
      .get("/api/essay/share")
      .then((res) => {
        this.essay = res.data;

        for (let i = 0; i < res.data.length; i++) {
          let max = -1;
          let maxlabel = 0;
          let tmp = [
            res.data[i].result1,
            res.data[i].result2,
            res.data[i].result3,
            res.data[i].result4,
            res.data[i].result5,
            res.data[i].result6,
            res.data[i].result7,
            res.data[i].result8,
          ];
          for (let j = 0; j < 8; j++) {
            if (parseFloat(tmp[j]) > max) {
              max = tmp[j];
              maxlabel = j;
            }
          }
          this.result.push(this.labels[maxlabel + 1]);
          this.percent.push(max);
          max = -1;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
<style scoped>
.ex {
  width: 80%;
  margin: auto;
  margin-top : 2%;
}
.share {
  height: 100%;
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