<template>
  <div class="app">
    <header>
      <img src="./components/img/logoEni.jpg" alt="Logo" class="logo">
      <nav>
      <router-link  to="./" > <h1 class="title">ENI-Encheres</h1> </router-link>
      </nav>
      <section  v-if="user.id">
        <nav>
          <router-link to="/nouvelle-vente">Nouvelle Vente</router-link>
          <router-link to="/monprofil"> Mon profil</router-link>
          <router-link to="/utilisateurs"> Utilisateurs </router-link>
          <button type="button" @click="logout"> Déconnecter</button>
        </nav>
      </section>
      <section v-else>
      <nav>
        <router-link to="/connexion">Connexion</router-link>
        <router-link to="/inscription">Inscription</router-link>

      </nav>
      </section>
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
<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { onMounted, ref, provide } from 'vue'
import axios from '@/axios/instance'
import router from "@/router.js";



const user = ref({})
provide('userConnect', user)

function logout(){
  localStorage.removeItem('jwt')
  user.value = {}
  router.push('/')
  window.location.reload();
}

async function init(){
  try {
    const result = await axios.get('/login')
    // si tout se passe bien,
    user.value = result.data
  } catch (error) {
    console.log('erreur lors de la récupération du user', error);
  }
}

onMounted(() => {
  init()
})
</script>

<style lang="css" src="./assets/main.css"></style>
