<template>
  <div>
    <v-container fluid>
      <v-textarea
        clearable
        clear-icon="mdi-close-circle"
        label="자기소개서"
        placeholder="200자 이상 입력해주세요"
        rows="15"
        v-model="content"
      />
      <v-btn elevation="2" rounded x-large @click='submit'>분석하기</v-btn>
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
