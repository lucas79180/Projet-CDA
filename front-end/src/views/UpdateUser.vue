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

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  font-family: Arial, sans-serif;
}

/* Style pour les cellules d'en-tête */
th {
  background-color: #004981;
  color: #fff;
  padding: 12px 15px;
  text-align: left;
}

/* Style pour les cellules de données */
td {
  padding: 10px 15px;
  border-bottom: 1px solid #ddd;
}

/* Style pour les boutons Modifier/Supprimer */
.link-button {
  background-color: #004981;
  border: none;
  color: #fff;
  cursor: pointer;
  padding: 8px 12px;
  text-decoration: none;
  border-radius: 4px;
}

.link-button:hover {
  background-color: #0056b3;
}

.delete-button {
  background-color: #dc3545;
}

.delete-button:hover {
  background-color: #c82333;
}

/* Style pour la confirmation dialog */
.confirmation-dialog {
  border: 1px solid #ddd;
  padding: 20px;
  background-color: #f9f9f9;
  margin-top: 10px;
}

.confirmation-dialog p {
  margin-bottom: 10px;
}

.confirmation-dialog button {
  background-color: #004981;
  color: #fff;
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 4px;
  margin-right: 5px;
}

.confirmation-dialog button:hover {
  background-color: #0056b3;
}

/* Style pour le formulaire d'édition */
form {
  margin-top: 20px;
  width: 100%;
}

form label {
  font-weight: bold;
  margin-bottom: 10px;
  display: block;
  text-align: center; /* Centrer les labels */
}

form input[type="text"],
form input[type="email"],
form input[type="tel"] {
  width: calc(50% - 12px); /* Raccourcir les champs de saisie */
  padding: 8px;
  border: 1px solid #cccccc;
  border-radius: 6px;
  font-size: 14px;
  margin-bottom: 15px; /* Augmentation de l'espace entre les champs de saisie */
  margin-left: auto; /* Centrer les champs de saisie */
  margin-right: auto; /* Centrer les champs de saisie */
  display: block; /* Pour centrer */
}

form button[type="submit"],
form button {
  width: calc(50% - 12px); /* Raccourcir les boutons */
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #004981;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 10px;
  margin-left: auto; /* Centrer les boutons */
  margin-right: auto; /* Centrer les boutons */
}

form button:last-child {
  margin-left: 24px; /* Ajout d'espace entre les boutons */
}

form button:hover {
  background-color: #0056b3;
}

</style>
