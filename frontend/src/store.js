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
    result: [],
    competency: [
      '글로벌', '도전정신', '성실성', '의사소통능력', '성취지향성', '책임감', '창의성', '정직함'
    ]
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
    setContent(state, content) {
      state.content = content
    },
    setResult(state, result) {
      state.result = result
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
