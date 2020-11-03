<template>
  <v-card>
    <v-container>
      <v-card-title class="headline">LogIn</v-card-title>

      <v-text-field label="이메일" v-model="user.email"></v-text-field>
      <v-text-field
        label="비밀번호"
        v-model="user.password"
        type="password"
      ></v-text-field>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="login" min-width="100px">
          <v-icon>mdi-account</v-icon>
          LogIn
        </v-btn>
        <v-btn text @click.stop="signup = true" min-width="100px">
          <v-icon style="margin-right:5px">mdi-account-multiple-plus</v-icon>
          Sign Up
        </v-btn>
        <v-dialog v-model="signup" max-width="500">
          <Signup :signup="signup" @close="close"></Signup>
        </v-dialog>
      </v-card-actions>
    </v-container>
  </v-card>
</template>

<script>
import Signup from "./Signup";
import { login } from "../../api/user.js";
export default {
  data() {
    return {
      signup: false,
      user: {
        email: "",
        password: ""
      }
    };
  },
  components: {
    Signup
  },
  methods: {
    close() {
      this.signup = false;
    },
    login() {
      const scope = this;

      login(
        this.user.email,
        this.user.password,
        function(response) {
          scope.$store.commit("setId", response.data.id);
          scope.$store.commit("setName", response.data.name);
          scope.$store.commit("setEmail", response.data.email);
          scope.$store.commit("setIsLogin", true);
          scope.$store.commit("setCompany1", response.data.company1);
          scope.$store.commit("setCompany2", response.data.company2);
          scope.$store.commit("setCompany3", response.data.company3);
          scope.$emit("close");
        },
        function(error) {
          console.error(error);
          alert("유저 이메일 혹은 비밀번호가 일치하지 않습니다.");
        }
      );
    }
  }
};
</script>

<style></style>
