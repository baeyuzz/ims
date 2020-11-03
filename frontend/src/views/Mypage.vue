<template>
  <v-app>
    <v-main>
      <div class="cal">
        <Calendar />
        <v-btn
          color="error"
          class="mr-4"
          @click="signout"
          dark
          style="float:right; margin-bottom:20px"
        >
          회원탈퇴
        </v-btn>
        <v-btn
          color="primary"
          class="mr-4"
          @click="dialog = true"
          dark
          style="float:right"
        >
          회원정보수정
        </v-btn>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import { signout } from "../api/user.js";
export default {
  name: "Mypage",
  components: {
    Calendar: () => import("@/components/Calendar")
  },
  methods: {
    signout() {
      var scope = this;
      var check = confirm("회원탈퇴 하시겠습니까?");
      if (!check) return;
      signout(
        this.$store.state.email,
        function(response) {
          alert("회원탈퇴 되었습니다");
          scope.$store.commit("setName", "");
          scope.$store.commit("setEmail", "");
          scope.$store.commit("setIsLogin", false);
          scope.$router.push("/");
        },
        function(error) {
          console.error(error);
          alert("회원탈퇴에 실패하셨습니다.");
        }
      );
    }
  }
};
</script>
<style scoped>
.cal {
  margin: auto;
  text-align: center;
  width: 80%;
}
</style>
