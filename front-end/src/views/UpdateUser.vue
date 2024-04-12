<template>
  <div>
    <h1>Modifier Mon Profil</h1>

    <!-- Formulaire de modification -->
    <form @submit.prevent="modifierProfil(userInfo.value)">
      <label for="pseudo">Pseudo:</label>
      <input type="text" id="pseudo" v-model="userInfo.pseudo">
      <input type="text" id="prenom" v-model="userInfo.prenom">
      <input type="text" id="nom" v-model="userInfo.nom">
      <input type="text" id="email" v-model="userInfo.email">
      <input type="text" id="telephone" v-model="userInfo.telephone">
      <input type="text" id="rue" v-model="userInfo.rue">
      <input type="text" id="code_postal" v-model="userInfo.code_postal">
      <input type="text" id="ville" v-model="userInfo.ville">

      <!-- Ajoutez d'autres champs ici selon les informations que vous avez -->

      <button type="submit">Enregistrer</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from "@/axios/instance.js";
import { useRouter } from 'vue-router'

const router = useRouter()

// Définir des variables de référence pour les informations de l'utilisateur
const userInfo = ref({
  pseudo: '',
  prenom: '',
  nom: '',
  email: '',
  telephone: '',
  rue: '',
  code_postal: '',
  ville: '',
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

// Fonction pour modifier le profil
const modifierProfil = async () => {
  try {
    console.log(userInfo.value)
    await axios.put(`/utilisateurs/${userInfo.value.id}`, userInfo.value)
    await router.push('/monprofil') // Rediriger vers la page de profil après la modification
  } catch (error) {
    console.error('Erreur lors de la modification du profil:', error)
  }
}
</script>
