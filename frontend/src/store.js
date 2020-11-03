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
    company1: "",
    company2: "",
    company3: "",
    isLogin: false,
    result: [],
    competency: [
      '글로벌', '도전정신', '성실성', '의사소통능력', '성취지향성', '책임감', '창의성', '정직함'
    ],
    content: "",
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
      state.content = content;
    },
    setResult(state, result) {
      state.result = result;
    },
    setCompany1(state, company) {
      state.company1 = company;
    },
    setCompany2(state, company) {
      state.company2 = company;
    },
    setCompany3(state, company) {
      state.company3 = company;
    },
    clearMember(state) {
      state.email = "";
      state.name = "";
    },
    clearContent(state) {
      state.content = "";
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
