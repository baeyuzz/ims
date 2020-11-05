<template>
  <div class="funcs">
    <v-card
      v-for="(card, i) in cards"
      :key="i"
      outlined
      class="mx-8 cards"
      width="400"
      color="#f0f4ff"
    >
      <v-icon x-large>{{ card.icon }}</v-icon>
      <br />
      <v-card-title class="justify-center text-h5">
        {{ card.title }}
      </v-card-title>
      <hr
        style="
          border: solid 1px #453c7aad;
          width: 25px;
          margin: auto;
          margin-bottom: 10px;
        "
      />
      <v-card-text class="content" style="color: #1c2341">
        {{ card.content }}
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    cards: [
      {
        title: "AI 자소서 분석",
        content:
          "나의 자소서에서 드러나는 역량을 그래프를 통해 확인 할 수 있습니다.",
        icon: "mdi-chart-line",
      },
      {
        title: "자기소개서 관리",
        content:
          "내가 작성한 자소서와 분석 결과를 저장하거나 공유할 수 있습니다.",
        icon: "mdi-content-duplicate",
      },
      {
        title: "일정관리",
        content: "취업 준비부터 개인 일정 관리까지 간편하게 할 수 있습니다.",
        icon: "mdi-calendar-check",
      },
    ],
  }),
};
</script>
<style scoped>
.funcs {
  text-align: center;
  display: flex;
  justify-content: center;
  margin: auto;
  background-color: #f0f4ff;
}
i.v-icon.v-icon {
  color: #453c7aad;
}
.content {
  font-size: 18px;
}
.funcs * {
  color: #1c2341;
}
.cards {
  padding: 150px 10px;
}

@media (max-width: 960px){
  .funcs {
    display: grid;
  }
  .cards {
    padding: 0;
    margin: 5%;

  }
}
</style>