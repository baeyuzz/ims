<template>
  <div>
    <v-sheet>
      <br />
      <div style="text-align: center">
        <h2>가장 잘 드러난 역량</h2>
        <h3>
          {{ labels[maxlabel] }}
          {{ max.toString().substring(0, 5) }} %
        </h3>
      </div>
      <br />
      <div class="resultCard">
        <div class="cards" v-for="(res, i) in unordered" :key="i">
          <div>{{ i + 1 }}. {{ res[0] }}</div>
          <div style="text-align: left">
            {{ res[1].toString().substring(0, 5) }}
          </div>
        </div>
      </div>
      <br />
    </v-sheet>
  </div>
</template>
<script>
export default {
  name: "Result",

  data: () => ({
    result: [],
    labels: [
      "글로벌",
      "도전정신",
      "성실성",
      "의사소통능력",
      "성취지향성",
      "책임감",
      "창의성",
      "정직함",
    ],
    max: 0,
    maxlabel: "",
    unordered: [],
  }),
  methods: {},
  created() {
    this.result = this.$store.state.result;
    for (var i = 0; i < 8; i++) {
      if (this.result[i] > this.max) {
        this.max = this.result[i];
        this.maxlabel = i;
      }
    }
    for (i = 0; i < 8; i++) {
      this.unordered.push([this.labels[i], this.result[i]]);
    }

    this.unordered.sort(function (first, second) {
      return second[1] - first[1];
    });
  },
};
</script>
<style scoped>
.resultCard {
  display: flex;
  flex-wrap: wrap;
  text-align: center;
  justify-content: center;
}
.cards {
  margin: 20px;
}
</style>