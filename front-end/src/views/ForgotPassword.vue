<template>
  <div>
    <h2>Réinitialisation du mot de passe</h2>
    <form @submit.prevent="resetPassword">
      <div>
        <label for="email">Adresse e-mail :</label>
        <input type="email" id="email" v-model="email">
      </div>
      <button type="submit">Réinitialiser le mot de passe</button>
    </form>
  </div>
</template>

<script setup>
import axios from '@/axios/instance'
import { ref } from 'vue'

const email = ref('');
const successMessage = ref('');
const errorMessage = ref('');

async function resetPassword() {
  try {
    // Envoyer une demande de réinitialisation de mot de passe à votre backend
    const response = await axios.post('/reset-password', { email: email.value });
    successMessage.value = response.data.message;
  } catch (error) {
    errorMessage.value = 'Erreur lors de la réinitialisation du mot de passe.';
  }
}
</script>
