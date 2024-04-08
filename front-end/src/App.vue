<template>
  <div class="app">
    <header>
      <img src="./components/img/logoEni.jpg" alt="Logo" class="logo">
      <h1 class="title">ENI-Encheres</h1>
      <nav>
        <router-link to="/connexion">Connexion</router-link>
        <router-link to="/inscription">Inscription</router-link>
      </nav>

    </header>
    <div class="content">
      <router-view></router-view>
      <div class="filters">
        <p>Filtres :</p>
      </div>
      <div class="search-container">
        <input type="text" placeholder="Recherche...">
      </div>
      <div class="select-container">
        <p> Catégories</p>
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
};
</script>

<style lang="css" src="./assets/main.css"></style>
