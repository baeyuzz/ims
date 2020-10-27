import Vue from "vue";
import Vuex from "vuex";
import cookies from 'vue-cookies';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    email : '',
    name : '',
    content : '',
    token: cookies.get('token'),
  },
  actions: {

  },
  mutations: {
    setName(state, name) {
      state.name = name;
    },
    setEmail(state,id) {
      state.id = id
    },
    setContent(state, content){
      state.content = content
    },
    clearMember(state) {
        state.id = '';
        state.name = '';
    },
    clearContent(state) {
      state.content = '';
    },
    logout(state){
      state.token = '';
    },
    setToken(state, token) {
      state.token = token;
      cookies.set('token', token);
    },
  },

});