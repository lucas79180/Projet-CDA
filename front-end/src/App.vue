<template>
  <div class="app">
    <header>
      <img src="./components/img/logoEni.jpg" alt="Logo" class="logo">
      <nav>
      <router-link  to="./" > <h1 class="title">ENI-Encheres</h1> </router-link>
      </nav>
      <nav>
        <router-link to="/connexion">Connexion</router-link>
        <router-link to="/inscription">Inscription</router-link>
        <router-link to="/nouvelle-vente">Nouvelle Vente</router-link>
      </nav>
    </header>
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
    </div>
    <footer>
      <!-- Votre contenu de pied de page ici -->
    </footer>
  </div>
</template>
<script>
export default {
  name: 'App',
  computed: {
    isHomePage() {
      return this.$route.path === '/';
    }
  }
};
</script>

<style lang="css" src="./assets/main.css"></style>
