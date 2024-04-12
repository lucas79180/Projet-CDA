<script setup>
import {ref, onMounted} from 'vue'
import axios from "@/axios/instance.js";

// Définir des variables de référence pour les informations de l'utilisateur
const userInfo = ref({
  pseudo: '',
  mot_de_passe: ''
  // Ajoutez d'autres champs ici selon les informations que vous avez
})

onMounted(async () => {
  try {
    const result = await axios.get('/login')
    userInfo.value = result.data // Assigner les données récupérées à userInfo
  } catch (error) {
    console.error('Erreur lors de la récupération des données utilisateur:', error)
  }
})
</script>

<template>
  <main>
    <h1>Mon Profil</h1>

    <!-- Afficher les informations de l'utilisateur -->
    <div>
      <p>Pseudo: {{ userInfo.pseudo }}</p>
      <p>Prenom : {{ userInfo.prenom }}</p>
      <p>Nom : {{ userInfo.nom }}</p>
      <p>Email : {{ userInfo.email }}</p>
      <p>Téléphone : {{ userInfo.telephone }}</p>
      <p>Rue : {{ userInfo.rue }}</p>
      <p>Code postal : {{ userInfo.code_postal }}</p>
      <p>Ville : {{ userInfo.ville }}</p>


      <!-- Ajoutez d'autres champs ici selon les informations que vous avez -->
    </div>
  </main>
</template>

<style scoped>
/* Ajoutez votre style CSS ici si nécessaire */
</style>
