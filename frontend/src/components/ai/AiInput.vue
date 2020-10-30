<template>
  <div class="input">
    <v-container fluid>
      <v-textarea
        label="자기소개서 입력"
        outlined
        clearable
        clear-icon="mdi-close-circle"
        placeholder="200자 이상 입력해주세요"
        v-model="content"
        rows="15"
      />
      <div style="text-align:right">글자수 - {{content.length}}자</div>
      <div style="margin: auto; text-align: center">
        <v-btn elevation="2" color="primary" rounded x-large @click="submit">
          분석하기
        </v-btn>
      </div>
      <br />
    </v-container>
  </div>
</template>
<script>
export default {
  name: "AiInput",

  data: () => ({
    content: "",
  }),
  methods: {
    submit() {
      console.log(this.content);
      this.$store.commit("setContent", this.content);
      console.log(this.$store.state.content);
    },
  },
};
</script>
<style scoped>
.input {
  margin-top: 40px;
}
</style>