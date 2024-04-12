<template>
  <main>
    <h1>Login</h1>
    <ul class="error" v-if="errors">
      <li class="error">{{errors}}</li>
    </ul>
    <form>
      <div class="form-element">
        <label>Pseudo</label>
        <input type="text" v-model="user.pseudo">
      </div>
      <div class="form-element">
        <label>Mot de passe</label>
        <input type="password" v-model="user.mot_de_passe">
      </div>
      <div class="form-element">
        <label>
          <input type="checkbox" v-model="user.se_souvenir_de_moi">
          Se souvenir de moi
        </label>
      </div>
      <button type="button" @click="login">Valider</button>
      <button type="button" @click="forgotPassword">Mot de passe oublié ?</button>
    </form>
  </main>
</template>

<script setup>
import axios from '@/axios/instance'
import router from '@/router'
import {ref, onMounted} from 'vue'
import FormTextElement from "@/components/FormTextElement.vue";

const emit = defineEmits(['login'])

const user = ref({
  pseudo: '',
  mot_de_passe: '',
  se_souvenir_de_moi: false,
})

const errors = ref()

async function login() {
  try {
    const result = await axios.post('/login', user.value)
    localStorage.setItem('jwt', result.data);

    if (user.value.se_souvenir_de_moi) {
      localStorage.setItem('userInfo', JSON.stringify(user.value));
    } else {
      localStorage.removeItem('userInfo');
    }

    emit('login')
    await router.push('/')
    window.location.reload();
  } catch (error) {
    errors.value = 'erreur de connexion'
  }
}

function forgotPassword() {
  // Rediriger l'utilisateur vers la page de réinitialisation du mot de passe
  router.push('/forgot-password');
}
</script>
