// <script>
//   // Extensions
//   import View from '@/views/View'

//   // Mixins
//   import LoadSections from '@/mixins/load-sections'

//   export default {
//     name: 'About',

//     metaInfo: { title: 'About Us' },

//     extends: View,

//     mixins: [
//       LoadSections([
//         'hero-alt',
//         'about-our-product',
//         'theme-features',
//         'affiliates',
//         'newsletter',
//         'pro-features',
//         'info',
//       ]),
//     ],

//     props: {
//       id: {
//         type: String,
//         default: 'about',
//       },
//     },
//   }
// </script>

<template>
  <div>
    <div class="input">
      <ai-input/>
    </div>
  </div>
</template>

<script>
export default {
  name: "AiAnalysis",
  components: {
    AiInput: () => import('@/components/AiInput'),
  },
}
</script>
<style scoped>
.input{
  width: 80%;
  margin : auto;
}
</style>
