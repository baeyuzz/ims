<template>
  <div>
    <v-app-bar
      id="home-app-bar"
      app
      color="white"
      elevation="1"
      height="80"
    >
      <div> HAPPY JOB</div>
      <v-spacer />

      <div>
        <v-tabs
          class="hidden-sm-and-down"
          optional
        >
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

          <v-tab active-class="text--primary"
            class="font-weight-bold"
            @click.stop="signup = true"> Login
          </v-tab>
        </v-tabs>
      </div>

        <v-dialog v-model="signup" max-width="500" min-width="300">
          <Login></Login>
        </v-dialog>
      <v-app-bar-nav-icon
        class="hidden-md-and-up"
        @click="drawer = !drawer"
      />
      </v-app-bar>

    <home-drawer
      v-model="drawer"
      :items="items"
    />
  </div>
</template>

<script>
import Login from "./Login";
import Signup from "./Signup";
  export default {
    name: 'HomeAppBar',

    components: {
      HomeDrawer: () => import('./Drawer'),
      Login,
    },

    data: () => ({
      drawer: null,
      items: [
        'Home',
        'AI-Analysis',
        'Notice',
      ],
      signup: false,
    }),
  }
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
