<template>
  <header>
    <a href="/">
      <img src="../components/img/logoEni.jpg" alt="Logo" class="logo">
    </a>    <nav>
      <router-link to="/">
        <h1 class="title">ENI-Encheres</h1>
      </router-link>
    </nav>
    <section v-if="user.id">
      <nav>
        <router-link to="/nouveau-article/add">Vendre un article</router-link>
        <router-link to="/mon-profil"> Mon profil</router-link>

        <router-link  v-if="user.administrateur" to="/utilisateurs"> Utilisateurs </router-link>
        <button class="nav-link" type="button" @click="logout"> Déconnexion</button>
      </nav>
    </section>
    <section v-else>
      <nav>
        <router-link to="/connexion">Connexion</router-link>
        <router-link to="/inscription">Inscription</router-link>
      </nav>
    </section>
  </header>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { onMounted, onUnmounted, ref } from 'vue'
import axios from '@/axios/instance'
import router from "@/router.js";

const user = ref({})

function logout() {
  localStorage.removeItem('jwt')
  user.value = {}
  router.push('/')
}

async function init() {
  try {
    const result = await axios.get('/login')
    user.value = result.data
    startInactivityTimer(); // Démarrer le minuteur d'inactivité une fois l'utilisateur connecté
  } catch (error) {
    console.log('Erreur lors de la récupération du user', error);
  }
}

function startInactivityTimer() {
  const inactivityThreshold = 300 * 1000; // 1 minute en millisecondes
  let lastActivityTime = Date.now();

  const checkInactivity = () => {
    const currentTime = Date.now();
    if (currentTime - lastActivityTime >= inactivityThreshold) {
      logout();
    }
  };

  const intervalId = setInterval(checkInactivity, 1000); // Vérifie l'inactivité toutes les secondes

  const handleActivity = () => {
    lastActivityTime = Date.now();
  };

  document.addEventListener('mousemove', handleActivity);
  document.addEventListener('keypress', handleActivity);

  // Nettoie les écouteurs et le minuteur lorsque le composant est démonté
  onUnmounted(() => {
    clearInterval(intervalId);
    document.removeEventListener('mousemove', handleActivity);
    document.removeEventListener('keypress', handleActivity);
  });
}

onMounted(() => {
  init();
})
</script>

<style lang="css" src="../assets/main.css"></style>
