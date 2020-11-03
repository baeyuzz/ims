<template>
  <v-card>
    <v-container>
      <v-card-title v-if="isSignUp" class="headline">Sign Up</v-card-title>
      <v-card-title v-if="!isSignUp" class="headline"
        >회원정보수정</v-card-title
      >
      <v-text-field
        v-if="isSignUp"
        label="이메일"
        v-model="user.email"
      ></v-text-field>
      <v-text-field
        v-if="isSignUp"
        label="이름"
        v-model="user.name"
      ></v-text-field>
      <v-text-field
        v-if="!isSignUp"
        label="이메일"
        v-model="user.email"
        readonly
      ></v-text-field>
      <v-text-field
        v-if="!isSignUp"
        label="이름"
        v-model="user.name"
        readonly
      ></v-text-field>
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
        <v-btn v-if="isSignUp" text @click="register" min-width="100px">
          <v-icon>mdi-account-multiple-plus</v-icon>
          SignUp
        </v-btn>
        <v-btn v-if="!isSignUp" text @click="update" min-width="100px">
          <v-icon>mdi-account-multiple-plus</v-icon>
          정보 수정
        </v-btn>
      </v-card-actions>
    </v-container>
  </v-card>
</template>

<script>
import { signup } from "../../api/user.js";
import { update } from "../../api/user.js";

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
      },
      isSignUp: true
    };
  },
  mounted() {
    this.user.email = this.$store.state.email;
    this.user.name = this.$store.state.name;
    this.user.company1 = this.$store.state.company1;
    this.user.company2 = this.$store.state.company2;
    this.user.company3 = this.$store.state.company3;
    if (this.user.email == "" || this.user.email == null) this.isSignUp = true;
    else this.isSignUp = false;
<<<<<<< HEAD

=======
>>>>>>> 0b136fc1f4e4d558a8b4e53ba334c232cf4af3ff
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
    },
    update() {
      var vm = this;
      if (this.user.password === this.user.passwordConfirm) {
        update(
          this.user.email,
          this.user.name,
          this.user.password,
          this.user.company1,
          this.user.company2,
          this.user.company3,
          function() {
            alert("회원정보가 수정되었습니다.");
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
