<template>
  <div id="app">
    <v-card class="text-center" color="green" dark>
      <h1>이건 메인 페이지 말고 나중에 result page 에서 사용할 거임</h1>
      <v-card-text>
        <v-sheet color="rgba(0, 0, 0, .12)">
          <v-sparkline
            :value="value"
            color="rgba(255, 255, 255, .7)"
            height="70"
            padding="10"
            stroke-linecap="round"
            line-width="2"
            smooth
          >
            <template v-slot:label="item">${{ item.value }}</template>
          </v-sparkline>
        </v-sheet>
      </v-card-text>

      <v-card-text>
        <div class="display-1 font-weight-thin">Sales Last 24h</div>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions class="justify-center">
        <v-btn block text> Go to Report </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    value: [423, 446, 675, 510, 590, 610, 760],
  }),
};
</script>
