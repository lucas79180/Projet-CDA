<template>
  <table>
    <tr>
      <!-- je crée un th pour chaque label spécifié dans la prop : labels -->
      <th v-for="label in labels" :key="label">{{ label }}</th>
      <th></th>
    </tr>

    <!-- je crée une ligne pour chaque élément de ma liste en model -->
    <tr v-for="element in listeUser" :key="element.id">
      <!-- Affichage des données de l'utilisateur -->
      <td v-for="field in fields" :key="field">{{ element[field] }}</td>

      <!-- Boutons Modifier / Supprimer -->
      <td>
        <button class="link-button" @click="editElement(element)">Modifier</button>
        <button class="link-button delete-button" @click="showConfirmationDialog(element)">Supprimer</button>
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
</style>
