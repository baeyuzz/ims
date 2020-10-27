import Vue from "vue";
import Vuex from "vuex";
import cookies from 'vue-cookies';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    email : '',
    name : '',
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
    clearMember(state) {
        state.id = '';
        state.name = '';
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