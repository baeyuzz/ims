<template>
  <v-navigation-drawer
    bottom
    color="transparent"
    fixed
    height="auto"
    overlay-color="secondary"
    overlay-opacity=".8"
    temporary
    v-bind="$attrs"
    v-on="$listeners"
  >
    <v-list color="white" shaped>
      <v-list-item
        v-for="name in items"
        :key="name"
        :to="{ name }"
        :exact="name === 'Home'"
        color="primary"
      >
        <v-list-item-content>
          <v-list-item-title v-text="name" />
        </v-list-item-content>
      </v-list-item>
      <v-list-item color="primary" v-if="$store.state.isLogin" to='/mypage'>
        <v-list-item-content>
          My Page
        </v-list-item-content>
      </v-list-item>
      <v-list-item color="primary" v-if="$store.state.isLogin" @click="logout">
        <v-list-item-content>
          Logout
        </v-list-item-content>
      </v-list-item>
      <v-list-item
        color="primary"
        v-if="!$store.state.isLogin"
        @click.stop="login = true"
      >
        <v-list-item-content>
          Login
        </v-list-item-content>
      </v-list-item>
    </v-list>
    <v-dialog v-model="login" max-width="500" min-width="300">
      <Login close="close"></Login>
    </v-dialog>
  </v-navigation-drawer>
</template>

<script>
import Login from "./Login";
export default {
  name: "HomeDrawer",

  components: {
    Login
  },

  props: {
    items: {
      type: Array,
      default: () => []
    }
  },
  data: () => ({
    login: false
  }),

  methods: {
    logout() {
      this.$store.commit("setName", "");
      this.$store.commit("setEmail", "");
      this.$store.commit("setIsLogin", false);
    }
  }
};
</script>
