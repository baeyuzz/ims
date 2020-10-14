<template>
  <v-theme-provider dark>
    <base-section
      id="info-alt"
      class="grey darken-1"
    >
      <v-responsive
        class="mx-auto"
        max-width="1400"
      >
        <v-container fluid>
          <v-row>

            <v-col
              cols="12"
              md="12"
            >
            SSAFY 3기 1반 1조 | 윤경준 김승기 박재우 배유진 배재원
            </v-col>
          </v-row>
        </v-container>
      </v-responsive>
    </base-section>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'Footer',

    components: {
    },
  }
</script>

<style lang="sass">
  #info-alt a
    text-decoration: none
</style>
