<template>
  <div class="app">
    <Navbar />
    <div class="content">
      <router-view></router-view>

      <div v-if="isHomePage" class="filters">
        <TablesEncheres />
      </div>
    </div>
    <footer>
      <!-- Votre contenu de pied de page ici -->
    </footer>
  </div>
</template>

<script setup>
import Navbar from '@/views/Navbar.vue';
import TablesEncheres from "@/views/TablesArticles.vue";
</script>

<script>
export default {
  name: 'App',
  computed: {
    isHomePage() {
      return this.$route.path === '/';
    }}}
</script>

<style lang="css" src="./assets/main.css"></style>
