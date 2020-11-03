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
      <div style="text-align: right">글자수 - {{ content.length }}자</div>
      <div style="margin: auto; text-align: center">
        <v-btn elevation="2" color="primary" rounded x-large @click="submit">
          분석하기
        </v-btn>
      </div>
      <br />
    </v-container>


    <div id="app">
      <v-app id="inspire">
        <div class="text-center">             
          <v-overlay :value="overlay">
            <v-progress-circular
              :size="70"
              color="primary"
              indeterminate
            ></v-progress-circular>
          </v-overlay>
        </div>
      </v-app>
    </div>


  </div>
  
</template>
<script>
import { createInstance2 } from "@/api/index.js";
export default {
  
  name: "AiInput",
  data: () => ({
    content: "",
    overlay: false,
  }),

  watch: {
    overlay (val) {
      val && setTimeout(() => {
        this.overlay = false
      }, 3000)
    },
  },

  methods: {
    submit() {

      if(this.content.length < 200){
        alert("200자 이상 입력해주세요")
        return;
      }

      this.overlay = true;


      this.$store.commit("setContent", this.content);

      const instance = createInstance2();
      instance
        .post("/analysis", { text: this.content })
        .then((res) => {
          this.overlay = false;
          if (res.data.result1 != null) {
            let list = [];
            list.push(res.data.result1);
            list.push(res.data.result2);
            list.push(res.data.result3);
            list.push(res.data.result4);
            list.push(res.data.result5);
            list.push(res.data.result6);
            list.push(res.data.result7);
            list.push(res.data.result8);
            this.$store.commit("setResult", list);

            this.$router.push('/ai-result')
          }
          else (            
            alert('분석 실패')
          )
        })
        .catch((err) => {
          this.overlay = false;
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
.input {
  margin-top: 40px;
}
</style>