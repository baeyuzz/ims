<template>
  <div class="chart-container">
    <canvas id="chart" height="500"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  name: "AI-Result",
  mounted() {
    this.makeChart();
  },
  data: () => ({}),
  methods: {
    makeChart() {
      var canvas = document.getElementById("chart");
      var ctx = canvas.getContext("2d");
      let chart = new Chart(ctx, {
        data: {
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
          datasets: [
            {
              data: this.$store.state.result,
              label: "역량",
              backgroundColor: [
                "#f8797999",
                "#0099ff99",
                "#ccdf2599",
                "#003d6699",
                "#fff04e99",
                "#eed0ff99",
                "#ff946a99",
                "#7ebda399",
              ],
            },
          ],
        },
        type: "polarArea",
        options: {
          responsive: true,
          maintainAspectRatio: false,
        },
      });
    },
  },
};
</script>
<style scoped>
.chart-container {
  margin: 30px;
}
</style>
