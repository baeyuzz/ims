import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import VueTextareaAutosize from "vue-textarea-autosize";
import firebase from "firebase/app";
import store from "./store.js";
import "firebase/firestore";
// import './plugins'

Vue.use(VueTextareaAutosize);
Vue.config.productionTip = false;

firebase.initializeApp({
  apiKey: "AIzaSyA2VUS7DtmttlWEV3iG4kIlLrHBzF6tk1Y",
  authDomain: "introduce-my-self.firebaseapp.com",
  databaseURL: "https://introduce-my-self.firebaseio.com",
  projectId: "introduce-my-self",
  storageBucket: "introduce-my-self.appspot.com",
  messagingSenderId: "795900447950",
  appId: "1:795900447950:web:39837703c56f60c8d6149f"
  // measurementId: "G-WE1L3KVE63"
});

export const db = firebase.firestore();

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
