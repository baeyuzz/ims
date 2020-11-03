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
        <v-list>
          <div v-for="essay in essays" :key="essay.id">
            <v-list-item>
              {{ essay.content }}
            </v-list-item>
          </div>
        </v-list>
        <v-dialog v-model="dialog" max-width="500">
          <Signup :signup="signup" @close="close"></Signup>
        </v-dialog>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Signup from "@/layouts/home/Signup";
import { signout } from "../api/user.js";
import { getEssayByUser } from "../api/essay.js";
export default {
  name: "Mypage",
  data() {
    return {
      essays: [],
      dialog: false
    };
  },
  components: {
    Calendar: () => import("@/components/Calendar"),
    Signup
  },
  mounted() {
    const scope = this;
    getEssayByUser(
      this.$store.state.id,
      function(response) {
        scope.essays = response.data;
      },
      function(error) {
        console.log(error);
      }
    );
  },
  methods: {
    close() {
      this.dialog = false;
    },
    signout() {
      var scope = this;
      var check = confirm("회원탈퇴 하시겠습니까?");
      if (!check) return;
      signout(
        this.$store.state.email,
        function(response) {
          alert("회원탈퇴 되었습니다");
          scope.$store.commit("setId", 0);
          scope.$store.commit("setName", "");
          scope.$store.commit("setEmail", "");
          scope.$store.commit("setIsLogin", false);
          scope.$store.commit("setCompany1", "");
          scope.$store.commit("setCompany2", "");
          scope.$store.commit("setCompany3", "");
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
