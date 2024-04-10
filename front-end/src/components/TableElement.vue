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

  <!-- Confirmation Dialog -->
  <div v-if="showDialog" class="confirmation-dialog">
    <p>Etes-vous sûr de vouloir supprimer cet utilisateur ?</p>
    <button @click="deleteElement">Oui</button>
    <button @click="cancelDelete">Annuler</button>
  </div>
</template>

<script setup>
import {defineProps, defineEmits, ref} from 'vue';

const props = defineProps(['listeUser', 'labels', 'fields']);
const emit = defineEmits(['edit', 'delete']);

const showDialog = ref(false);
let userToDelete = null;

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
  emit('edit', element);
}
</script>

<style scoped>
</style>
