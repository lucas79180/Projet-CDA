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

      <!-- Bouton Modifier -->
      <button @click="modifierProfil">Modifier</button>
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from "@/axios/instance.js";
import { useRouter } from 'vue-router'

const router = useRouter()

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

// Fonction pour rediriger vers la page de modification du profil
const modifierProfil = () => {
  router.push('/update')
}
</script>

<style scoped>
/* Ajoutez votre style CSS ici si nécessaire */
</style>
