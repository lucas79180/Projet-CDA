<template>
  <div class="container">
    <h1 class="title">Modifier Mon Profil</h1>
    <!-- Formulaire de modification -->
    <form class="profile-form" @submit.prevent="modifierProfil(userInfo.value)">
      <div class="form-group">
        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" v-model="userInfo.pseudo">
      </div>
      <div class="form-group">
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" v-model="userInfo.prenom">
      </div>
      <div class="form-group">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" v-model="userInfo.nom">
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="userInfo.email">
      </div>
      <div class="form-group">
        <label for="telephone">Téléphone:</label>
        <input type="text" id="telephone" v-model="userInfo.telephone">
      </div>
      <div class="form-group">
        <label for="rue">Rue:</label>
        <input type="text" id="rue" v-model="userInfo.rue">
      </div>
      <div class="form-group">
        <label for="code_postal">Code Postal:</label>
        <input type="text" id="code_postal" v-model="userInfo.code_postal">
      </div>
      <div class="form-group">
        <label for="ville">Ville:</label>
        <input type="text" id="ville" v-model="userInfo.ville">
      </div>

      <!-- Ajoutez d'autres champs ici selon les informations que vous avez -->

      <button type="submit" class="submit-btn">Enregistrer</button>
    </form>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import axios from "@/axios/instance.js";
import {useRouter} from 'vue-router'

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
