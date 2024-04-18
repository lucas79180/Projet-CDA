<template>
  <div class="container">
    <h1 class="title">Modifier Mon Profil</h1>
    <!-- Formulaire de modification -->

    <form class="profile-form" @submit.prevent="modifierProfil()">
      <div>
        <label for="nom">Nom de la personne:</label>
        <input type="text" id="nom" required>
      </div>

      <div>
        <label for="carte">Numéro de carte bleue:</label>
        <input type="text" id="carte"  required>
      </div>
      <div>
        <label for="expiration">Date d'expiration:</label>
        <input type="text" id="expiration"  placeholder="MM/YY" required>
      </div>
      <div>
        <label for="code">cryptogramme  :</label>
        <input type="password" id="code" required>
      </div>
      <div class="form-group">
        <label for="credit">Crédit actuel:</label>
        <input type="text" id="credit" v-model="userInfo.credit" disabled>
      </div>
      <div class="form-group">
        <label for="creditAjoute">Crédit à ajouter:</label>
        <input type="text" id="creditAjoute" v-model="creditAjoute" required>
      </div>
      <!-- Ajoutez d'autres champs ici selon les informations que vous avez -->

      <button type="submit" class="submit-btn">Payer</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from "@/axios/instance.js";
import { useRouter } from 'vue-router';

const router = useRouter();

// Définir des variables de référence pour les informations de l'utilisateur
const userInfo = ref({
  credit: ''
  // Ajoutez d'autres champs ici selon les informations que vous avez
});

const creditAjoute = ref('');

onMounted(async () => {
  try {
    const result = await axios.get('/login');
    userInfo.value = result.data; // Assigner les données récupérées à userInfo
  } catch (error) {
    console.error('Erreur lors de la récupération des données utilisateur:', error);
  }
});

// Fonction pour modifier le profil
const modifierProfil = async () => {
  try {
    const creditAAjouter = parseFloat(creditAjoute.value); // Convertir la saisie en nombre
    const ancienCredit = parseFloat(userInfo.value.credit); // Récupérer le crédit actuel de l'utilisateur
    const nouveauCredit = ancienCredit + creditAAjouter; // Ajouter le crédit saisi à l'ancien crédit
    userInfo.value.credit = nouveauCredit.toString(); // Mettre à jour la valeur du crédit dans userInfo

    await axios.put(`/utilisateurs/${userInfo.value.id}`, userInfo.value);
    await router.push('/mon-profil'); // Rediriger vers la page de profil après l'ajout de crédit
  } catch (error) {
    console.error('Erreur lors de l\'ajout de crédit:', error);
  }
};
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
