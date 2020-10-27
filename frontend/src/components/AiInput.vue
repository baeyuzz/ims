<template>
  <div>
    <v-container fluid>
      <div style="font-size : 25px;">자기소개서 입력</div>
      <v-textarea
        clearable
        clear-icon="mdi-close-circle"
        placeholder="200자 이상 입력해주세요"
        rows="16"
        v-model="content"
      />
      <br>
      <div style="margin : auto;text-align : center">
      <v-btn elevation="2" rounded x-large @click='submit'>분석하기</v-btn>
      </div>
      <br>
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
    submit(){
      console.log(this.content);
      this.$store.commit("setContent", this.content);
      console.log(this.$store.state.content)

    }

  },
};
</script>
