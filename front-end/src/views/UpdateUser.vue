<script setup>
import {defineProps, onMounted, ref} from "vue";
import axios from "../axios/instance";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";
import TableElement from "@/components/TableElement.vue";

const userProfile = ref({
  pseudo: '',
  nom: '',
  prenom: '',
  email: '',
  telephone: '',
  rue: '',
  code_postal: '',
  ville: '',
  mot_de_passe: '',
});

const listeErreurs = ref([]);

async function recupererProfil() {
  try {
    const reponseHTTP = await axios.get('/utilisateurs/profil'); // Endpoint spécifique pour récupérer le profil de l'utilisateur connecté
    Object.assign(userProfile.value, reponseHTTP.data);
  } catch (error) {
    listeErreurs.value.push(error.message);
  }
}

// Fonction pour mettre à jour le profil de l'utilisateur
async function modifierProfil() {
  try {
    await axios.put('/utilisateurs', userProfile.value);
    // Actualisation des informations du profil après modification
    recupererProfil();
  } catch (error) {
    listeErreurs.value.push(error.message);
  }
}

onMounted(() => {
  // Appel à la fonction de récupération du profil lors du montage du composant
  recupererProfil();
});

</script>

<template>
  <main>
    <!-- Affichage des erreurs -->
    <ErrorsDisplay :errors="listeErreurs"/>
    <h2>Votre profil</h2>

    <!-- Formulaire de modification du profil -->
    <form @submit.prevent="modifierProfil">
      <div>
        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" v-model="userProfile.pseudo" required>
      </div>
      <div>
        <label for="nom">Nom:</label>
        <input type="text" id="nom" v-model="userProfile.nom" required>
      </div>
      <!-- Ajoutez d'autres champs pour les autres informations du profil -->
      <!-- Assurez-vous de lier les champs aux propriétés correspondantes de userProfile -->

      <button type="submit">Enregistrer les modifications</button>
    </form>
  </main>
</template>


<style scoped>
/* Styles spécifiques au composant */
</style>
