<template>
  <table>
    <tr>
      <!-- Headers -->
      <th v-for="label in labels">{{ label }}</th>
      <th>Actions</th>
    </tr>

    <!-- Rows -->
    <tr v-for="element in listeArticle" :key="element.noArticle">
      <!-- Data -->
      <td v-for="field in fields" :key="field">{{ element[field] }}</td>

      <!-- Actions -->
    </tr>
  </table>
</template>

<script setup>
import {defineProps, ref} from 'vue';

const props = defineProps(['listeArticle', 'labels', 'fields']);

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
}</style>
