<template>
  <v-card>
    <v-container>
      <v-card-title class="headline">Sign Up</v-card-title>
      <v-text-field label="이메일" v-model="user.email"></v-text-field>
      <v-text-field label="이름" v-model="user.name"></v-text-field>
      <v-text-field
        label="비밀번호"
        v-model="user.password"
        type="password"
      ></v-text-field>
      <v-text-field
        label="비밀번호 확인"
        v-model="user.passwordConfirm"
        type="password"
      ></v-text-field>
      <v-text-field
        label="선호하는 회사 1 (선택)"
        v-model="user.company1"
      ></v-text-field>
      <v-text-field
        label="선호하는 회사 2 (선택)"
        v-model="user.company2"
      ></v-text-field>
      <v-text-field
        label="선호하는 회사 3 (선택)"
        v-model="user.company3"
      ></v-text-field>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text @click="register" min-width="100px">
          <v-icon>mdi-account-multiple-plus</v-icon>
          SignUp
        </v-btn>
      </v-card-actions>
    </v-container>
  </v-card>
</template>

<script>
import { signup } from "../../api/user.js";

export default {
  props: ["signup"],
  data() {
    return {
      user: {
        email: "",
        name: "",
        password: "",
        passwordConfirm: "",
        company1: "",
        company2: "",
        company3: ""
      }
    };
  },
  methods: {
    register() {
      var vm = this;

      if (this.user.password === this.user.passwordConfirm) {
        signup(
          this.user.email,
          this.user.name,
          this.user.password,
          this.user.company1,
          this.user.company2,
          this.user.company3,
          function() {
            alert("회원가입이 완료되었습니다.");
            vm.$emit("close");
          },
          function(error) {
            alert("실패!");
          }
        );
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    }
  }
};
</script>

<style></style>
