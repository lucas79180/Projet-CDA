<script setup>
import axios from '@/axios/instance'
import router from '@/router'
import { ref, onMounted } from 'vue'
import FormTextElement from "@/components/FormTextElement.vue";
import forgotPassword from "@/views/ForgotPassword.vue";

const emit = defineEmits(['login'])

const user = ref({
  pseudo: '',
  mot_de_passe: '',
})

const errors = ref()

async function login() {
  try {
    const result = await axios.post('/login', user.value)
    localStorage.setItem('jwt', result.data);
    const admin = await axios.get('/login')
    localStorage.setItem('userAdmin', JSON.stringify( admin.data));
    if (admin.data.actif === false)
    {
      localStorage.removeItem('userInfo');
      localStorage.removeItem('jwt');
      localStorage.removeItem('userAdmin');
      await router.push('/')
      window.location.reload();
    }else if (user.value.se_souvenir_de_moi) {
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

onMounted(() => {
  const rememberedUser = localStorage.getItem('userInfo');
  if (rememberedUser) {
    user.value = JSON.parse(rememberedUser);
  }
})

window.addEventListener('beforeunload', () => {
  if (!user.value.se_souvenir_de_moi) {
    localStorage.removeItem('userInfo');
  }
});



</script>

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
      <br><br>
      <router-link to="/forgot-password" class="router-link-button">Mot de passe oublié ?</router-link>

    </form>
  </main>
</template>

<style scoped>
/* Style général */
main {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* Pour centrer verticalement */
}

h1 {
  margin-bottom: 20px;
  color: #333333;
  text-align: center;
}

ul.error {
  color: #ff0000;
  list-style-type: none;
  padding: 0;
  margin-bottom: 20px;
}

.form-element {
  margin-bottom: 20px; /* Augmentation de l'espace entre les champs de saisie */
  width: 100%;
}

label {
  font-weight: bold;
  margin-bottom: 16px; /* Augmentation de l'espace entre les étiquettes et les champs de saisie */
  color: #666666;
}

input[type="text"],
input[type="password"],
input[type="email"],
input[type="tel"] {
  width: calc(100% - 24px); /* Ajustement pour le padding */
  padding: 10px; /* Augmentation de la taille du padding */
  border: 1px solid #cccccc;
  border-radius: 6px;
  font-size: 14px;
  margin-bottom: 15px; /* Augmentation de l'espace entre les champs de saisie */
}

button {
  width: calc(100% - 24px); /* Ajustement pour le padding */
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #004981;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:last-child {
  margin-top: 10px; /* Ajout de l'espace entre les boutons */
}

button:hover {
  background-color: #0056b3;
}
.router-link-button {
  display: inline-block;
  width: calc(100% - 24px); /* Ajustement pour le padding */
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #004981;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  text-decoration: none; /* Supprime le soulignement */
  transition: background-color 0.3s;
}

.router-link-button:hover {
  background-color: #00335b; /* Changement de couleur au survol */
}


</style>