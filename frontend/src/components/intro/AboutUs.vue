<template>
  <v-parallax src="@/assets/parallax.png">
    <div style="text-align: center">
      <h1>About Us</h1>
      <br />
      <hr style="width: 90%; margin: auto" />
      <br />
      <br />
      <v-container>
        <v-row>
          <v-col v-for="(feature, i) in features" :key="i" cols="12" md="4">
            <div style="padding: 20px">
              <h2>
                <v-icon x-large color="white">{{ feature.icon }}</v-icon>
                {{ feature.name }}
              </h2>
              <div style="margin-top: 15px">
                {{ feature.desc }}
              </div>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-parallax>
</template>
<script>
export default {
    data: () => ({
    features: [
      {
        name: "Team IMS",
        desc: "자기소개서 역량 분석 웹 서비스",
        icon: '',
      },
      {
        name: "윤경준",
        desc: "팀장 / Full Stack",
        icon: "mdi-music",
      },
      {
        name: "배재원",
        desc: "Back-End",
        icon: "mdi-ice-pop",
      },
      {
        name: "김승기",
        desc: "BE - Spring & Flask",
        icon: "mdi-bug",
      },
      {
        name: "박재우",
        desc: "Front-End / Firebase",
        icon: "mdi-gamepad-variant",
      },
      {
        name: "배유진",
        desc: "Front-End / NLP",
        icon: "mdi-cat",
      },
    ],
  }),
}
</script>