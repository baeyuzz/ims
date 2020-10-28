import Vue from "vue";
import Vuex from "vuex";
import cookies from "vue-cookies";
import VuexPersistence from "vuex-persist";

const persistor = new VuexPersistence({
  key: "root",
  storage: window.localStorage
});

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: 0,
    email: "",
    name: "",
    isLogin: false,
    token: cookies.get("token")
  },
  actions: {},
  mutations: {
    setName(state, name) {
      state.name = name;
    },
    setEmail(state, email) {
      state.email = email;
    },
    setIsLogin(state, isLogin) {
      state.isLogin = isLogin;
    },
    setContent(state, content){
      state.content = content
    },
    clearMember(state) {
      state.email = "";
      state.name = "";
    },
    clearContent(state) {
      state.content = '';
    },
    logout(state) {
      state.email = "";
      state.name = "";
      state.isLogin = false;
      state.token = "";
    },
    setToken(state, token) {
      state.token = token;
      cookies.set("token", token);
    }
  },
  plugins: [persistor.plugin]
});
