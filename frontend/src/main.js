import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import VueTextareaAutosize from "vue-textarea-autosize";
import firebase from "firebase/app";
import "firebase/firestore";
// import './plugins'

// Vue.use(VueTextareaAutosize);
Vue.config.productionTip = false;

// firebase.initializeApp({
//   apiKey: "AIzaSyBtDf0M4d6DCLLXsZ7BRlc4hzQHHI0kGZc",
//   authDomain: "vuetify-calendar.firebaseapp.com",
//   databaseURL: "https://vuetify-calendar.firebaseio.com",
//   projectId: "vuetify-calendar",
//   storageBucket: "vuetify-calendar.appspot.com",
//   messagingSenderId: "205712808747",
//   appId: "1:205712808747:web:d2ef315814ebaa966a036e"
// });

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
  vuetify,
  render: h => h(App)
}).$mount("#app");
