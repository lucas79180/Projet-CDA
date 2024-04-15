<template>
  <div>
    <h2>Réinitialisation du mot de passe</h2>
    <form @submit.prevent="resetPassword(user)">
    <div>
        <label for="email">Adresse e-mail :</label>
        <input type="email" id="email" v-model="user.email">
      </div>
      <div>
        <label for="password">Mot de passe :</label>
        <input type="password" id="password" v-model="user.mot_de_passe">
      </div>
      <div>
        <label for="re-password">Re-mot de passe :</label>
        <input type="password" id="re-password" v-model="user.re_mot_de_passe">
      </div>
      <button type="submit">Réinitialiser le mot de passe</button>
    </form>
  </div>
</template>

<script setup>
import axios from '@/axios/instance'
import {ref} from 'vue'

const user = ref({
  email: '',
  mot_de_passe: '',
  re_mot_de_passe: '',
})
const successMessage = ref('');
const errorMessage = ref('');

async function resetPassword(userData) {
  try {
    if (userData.mot_de_passe !== userData.re_mot_de_passe) {
      errorMessage.value = 'Les mots de passe ne correspondent pas.';
      return;
    }
    // Inclure l'email dans l'URL
    const response = await axios.put(`/login/${userData.email}`, userData);
    console.log(userData);
    successMessage.value = response.data.message;
  } catch (error) {
    if (error.response && error.response.status === 400) {
      errorMessage.value = error.response.data.message;
    } else {
      errorMessage.value = 'Erreur lors de la réinitialisation du mot de passe.';
    }
  }
}

</script>
