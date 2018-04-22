<template>
  <div class="main-container">
    <tags-view></tags-view>
  </div>
</template>

<script>
  import {TagsView} from '@/components'

  export default {
    name: "layout",
    components: {TagsView},
    data() {
      return {
      }
    }
  }
</script>

<style scoped>

</style>
