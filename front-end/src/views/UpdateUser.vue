<script setup>
import {defineProps, onMounted, ref} from "vue";
import axios from "../axios/instance";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";
import TableElement from "@/components/TableElement.vue";
import FormTextElement from "@/components/FormTextElement.vue";

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

const userProfil = ref([])
const listeErreurs = ref([]);

async function recupererProfil() {
  try {
    const reponseHTTP = await axios.get('/utilisateurs');
    userProfile.value = reponseHTTP.data;
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
      <FormTextElement label="Pseudo" type="text" :object="userProfile" field="pseudo"/>

      <FormTextElement label="Nom" type="text" :object="userProfile" field="nom"/>

      <FormTextElement label="Prénom" type="text" :object="userProfile" field="prenom" />

      <FormTextElement label="Email" type="email" :object="userProfile" field="email"/>

      <FormTextElement label="Mot de passe" type="password" :object="userProfile" field="mot_de_passe"/>

      <FormTextElement label="Téléphone" type="tel" :object="userProfile" field="telephone"/>

      <FormTextElement label="Rue" type="text" :object="userProfile" field="rue"/>

      <FormTextElement label="Code postal" type="text" :object="userProfile" field="code_postal"/>

      <FormTextElement label="Ville" type="text" :object="userProfile" field="ville" />

      <!-- Ajoutez d'autres champs pour les autres informations du profil -->
      <!-- Assurez-vous de lier les champs aux propriétés correspondantes de userProfile -->

      <button type="submit">Enregistrer les modifications</button>
    </form>
  </main>
</template>


<style scoped>
/* Styles spécifiques au composant */
</style>
