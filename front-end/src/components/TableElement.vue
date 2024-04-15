

<template>
  <table>
    <tr>
      <!-- je crée un th pour chaque label spécifié dans la prop : labels -->
      <th v-for="label in labels">{{ label }}</th>
      <th></th>
    </tr>

    <!-- je crée une ligne pour chaque élément de ma liste en model -->
    <tr v-for="element in listeUser" :key="element.id">
      <!-- Affichage des données de l'utilisateur -->
      <td v-for="field in fields" :key="field">{{ element[field] }}</td>

      <!-- Boutons Modifier / Supprimer -->
      <td>
        <button class="link-button" @click="editElement(element)">
          <i class="fas fa-pencil-alt"></i> <!-- Icône de crayon -->
        </button>
        <button class="link-button delete-button" @click="showConfirmationDialog(element)">
          <i class="fas fa-times"></i> <!-- Icône de croix -->
        </button>
      </td>
    </tr>
  </table>

  <div v-if="userEdit">
    <h3>Modifier l'utilisateur</h3>
    <form @submit.prevent="editUser"> <!-- Utilisez editUser au lieu de submitForm -->
      <label for="pseudo">Pseudo:</label>
      <input type="text" id="pseudo" v-model="editingUser.pseudo" required>

      <label for="nom">Nom:</label>
      <input type="text" id="nom" v-model="editingUser.nom" required>

      <label for="prenom">Prénom:</label>
      <input type="text" id="prenom" v-model="editingUser.prenom" required>

      <label for="email">Email : </label>
      <input type="email" id="email" v-model="editingUser.email" required>

      <label for="tel">Téléphone :</label>
      <input type="tel" id="tel" v-model="editingUser.telephone" required>

      <label for="rue">Rue :</label>
      <input type="text" id="rue" v-model="editingUser.rue" required>

      <label for="codepostal">Code Postal:</label>
      <input type="text" id="codepostal" v-model="editingUser.code_postal" required>

      <label for="Ville">Ville :</label>
      <input type="text" id="Ville" v-model="editingUser.ville" required>

      <button type="submit">Enregistrer</button>
      <button @click="cancelEdit">Annuler</button>
    </form>
  </div>
  <div v-if="showDialog" class="confirmation-dialog">
    <p>Etes-vous sûr de vouloir supprimer cet utilisateur ?</p>
    <button @click="deleteElement">Oui</button>
    <button @click="cancelDelete">Annuler</button>
  </div>
</template>
<script setup>
import { defineProps, defineEmits, ref } from 'vue';

const props = defineProps(['listeUser', 'labels', 'fields']);
const emit = defineEmits(['edit', 'delete', 'modify']);

const showDialog = ref(false);
const userEdit = ref(false);
let userToDelete = null;

const editingUser = ref({}); // Créez une référence pour stocker l'utilisateur en cours d'édition

const showConfirmationDialog = (user) => {
  showDialog.value = true;
  userToDelete = user;
}

const deleteElement = () => {
  if (userToDelete) {
    emit('delete', userToDelete); // Passer l'objet utilisateur entier
    showDialog.value = false;
    userToDelete = null;
  }
}

const cancelDelete = () => {
  showDialog.value = false;
  userToDelete = null;
}

const editElement = (element) => {
  userEdit.value = true;
  editingUser.value = { ...element }; // Affecter l'utilisateur en cours d'édition
}

const editUser = () => {
  emit('edit', editingUser.value); // Émettre l'événement d'édition avec l'utilisateur modifié
  userEdit.value = false;
}

const cancelEdit = () => {
  userEdit.value = false;
}

const modifyElement = (element) => {
  emit('modify', element);
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
