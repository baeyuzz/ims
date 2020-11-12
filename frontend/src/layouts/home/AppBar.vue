<template>
  <div>
    <v-app-bar id="home-app-bar" app color="white" elevation="1" height="80">
      <div>
        <img
          class="logo"
          src="@/assets/logo.png"
          height="60"
          style="margin: 5px 0 0 10px; cursor: pointer"
          @click="goHome"
        />
      </div>
      <v-spacer />

      <div>
        <v-tabs class="hidden-sm-and-down" optional>
          <v-tab
            v-for="(name, i) in items"
            :key="i"
            :to="{ name }"
            :exact="name === 'Home'"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
            text
          >
            {{ name }}
          </v-tab>
          <v-tab
            active-class="text--primary"
            class="font-weight-bold"
            @click.stop="login = true"
            v-if="!$store.state.isLogin"
          >
            Login
          </v-tab>
          <v-tab
            active-class="text--primary"
            class="font-weight-bold"
            v-if="$store.state.isLogin"
            to="/essays"
          >
            Essays
          </v-tab>
          <v-tab
            active-class="text--primary"
            class="font-weight-bold"
            v-if="$store.state.isLogin"
            to="/mypage"
          >
            My Page
          </v-tab>
          <v-tab
            active-class="text--primary"
            class="font-weight-bold"
            v-if="$store.state.isLogin"
            @click="logout"
          >
            Logout
          </v-tab>
        </v-tabs>
      </div>

      <v-dialog v-model="login" max-width="500" min-width="300">
        <Login close="close"></Login>
      </v-dialog>
      <v-app-bar-nav-icon class="hidden-md-and-up" @click="drawer = !drawer" />
    </v-app-bar>

    <home-drawer v-model="drawer" :items="items" />
  </div>
</template>

<script>
import Login from "./Login";
import Signup from "./Signup";
export default {
  name: "HomeAppBar",

  components: {
    HomeDrawer: () => import("./Drawer"),
    Login,
  },

  data: () => ({
    drawer: null,
    items: ["Home", "AI-Analysis"],
    login: false,
  }),

  methods: {
    close() {
      this.login = false;
    },
    logout() {
      this.$store.commit("setId", 0);
      this.$store.commit("setName", "");
      this.$store.commit("setEmail", "");
      this.$store.commit("setIsLogin", false);
      this.$store.commit("setCompany1", "");
      this.$store.commit("setCompany2", "");
      this.$store.commit("setCompany3", "");
      this.$router.push("/");
    },
    goHome() {
      // this.$router.go(this.$router.currentRoute); 이건 새로고침
      const path = "/";
      if (this.$route.path !== path) this.$router.push(path);
    },
  },
};
</script>

<style lang="sass">
#home-app-bar
  .v-tabs-slider
    max-width: 24px
    margin: 0 auto

  .v-tab
    &::before
      display: none
</style>
<style scoped>
@media (max-width: 450px) {
  .logo {
    height : 40px;
  }
}
</style>
