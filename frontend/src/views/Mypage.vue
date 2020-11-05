<template>
  <v-app>
    <v-main>
      <div class="cal">
        <h1>일정 관리</h1>
        <Calendar />
        <v-divider style="margin-bottom:50px; margin-top:50px"></v-divider>
        <h1>자기소개서 관리</h1>
        <v-list>
          <v-row v-for="(essay, index) in essays" :key="essay.id">
            <v-col
              style="text-align:left; cursor:pointer"
              cols="11"
              @click="gotoEssay(index)"
            >
              {{ essay.content.substring(0, 50) }}...
            </v-col>
            <v-col cols="1">
              <v-icon @click="deleteEssay(index)">mdi-delete</v-icon>
            </v-col>
          </v-row>
        </v-list>
        <v-divider style="margin-bottom:20px; margin-top:50px"></v-divider>
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
        <v-dialog v-model="dialog" max-width="500">
          <Signup @close="close"></Signup>
        </v-dialog>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Signup from "@/layouts/home/Signup";
import { signout } from "../api/user.js";
import { getEssayByUser } from "../api/essay.js";
import { deleteEssay } from "../api/essay.js";
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
    gotoEssay(index) {
      let list = [];
      list.push(this.essays[index].result1);
      list.push(this.essays[index].result2);
      list.push(this.essays[index].result3);
      list.push(this.essays[index].result4);
      list.push(this.essays[index].result5);
      list.push(this.essays[index].result6);
      list.push(this.essays[index].result7);
      list.push(this.essays[index].result8);

      this.$store.commit("setResult", list);

      console.log(list);

      this.$router.push("/ai-result");
    },
    deleteEssay(index) {
      var scope = this;
      var check = confirm("삭제하시겠습니까?");
      if (!check) return;
      deleteEssay(
        this.essays[index].id,
        function(response) {
          alert("삭제되었습니다.");
          scope.$router.go(scope.$router.currentRoute);
        },
        function(error) {
          alert("실패");
        }
      );
    },
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
