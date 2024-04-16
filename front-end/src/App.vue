<template>
  <div class="app">
    <Navbar />
    <div class="content">
      <router-view></router-view>
      <div v-if="isHomePage" class="filters">
        <p>Filtres :</p>
      </div>
      <div v-if="isHomePage" class="search-container">
        <input type="text" placeholder="Recherche...">
      </div>
      <div v-if="isHomePage" class="filters">
        <p>Catégories :</p>
      </div>
      <div v-if="isHomePage" class="select-container">
        <select>
          <option value="">Choisissez une catégorie</option>
          <option value="informatiques">Informatiques</option>
          <option value="ameublement">Ameublement</option>
          <option value="vêtements">Vêtements</option>
          <option value="sports-loisirs">Sports & Loisirs</option>
        </select>
      </div>
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
import TablesEncheres from "@/views/TablesEncheres.vue";
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
