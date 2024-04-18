
<script setup>
import { ref, onMounted } from 'vue'
import axios from "@/axios/instance.js";
import {RouterLink, useRouter} from 'vue-router'

const router = useRouter()

// Définir des variables de référence pour les informations de l'utilisateur
const userInfo = ref({
  pseudo: '',
  mot_de_passe: ''

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
<template>

  <main>
    <div class="container">
      <h1 class="title">Mon Profil</h1>

      <div class="profile-box">
        <div class="user-info">
          <div class="info-item">
            <p><strong>Pseudo:</strong></p>
            <p>{{ userInfo.pseudo }}</p>
          </div>

          <div class="info-item">
            <p><strong>Prénom:</strong></p>
            <p>{{ userInfo.prenom }}</p>
          </div>

          <div class="info-item">
            <p><strong>Nom:</strong></p>
            <p>{{ userInfo.nom }}</p>
          </div>

          <div class="info-item">
            <p><strong>Email:</strong></p>
            <p>{{ userInfo.email }}</p>
          </div>

          <div class="info-item">
            <p><strong>Téléphone:</strong></p>
            <p>{{ userInfo.telephone }}</p>
          </div>

          <div class="info-item">
            <p><strong>Adresse:</strong></p>
            <p>{{ userInfo.rue }}, {{ userInfo.code_postal }}, {{ userInfo.ville }}</p>
          </div>


          <div class="info-item">
            <p><strong>Credit:</strong></p>
            <p>{{ userInfo.credit }}</p>
            <nav >
            <router-link to="/ajout-credit">Acheter des crédits supplémentaires</router-link>
            </nav>
          </div>
          <!-- Ajouter d'autres champs ici selon les informations disponibles -->

          <button @click="modifierProfil">Modifier</button>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.title {
  font-size: 24px;
  margin-bottom: 20px;
}

.profile-box {
  border: 1px solid #ddd;
  border-radius: 5px;
  overflow: hidden;
  padding: 30px;
  width: 100%;
  max-width: 600px; /* ajustement de la largeur maximale */
}

.user-info {
  display: flex;
  flex-direction: column;
}

.info-item {
  justify-content: center;

}

button {
  margin-top: 20px;
  background-color: #004981;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>
