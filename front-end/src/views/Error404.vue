<template>
  <div class="error-404">
    <h1>Erreur 404</h1>
    <p>Désolé, la page que vous recherchez est introuvable.</p>
    <router-link to="/">Retour à la page d'accueil</router-link>
  </div>
</template>

<script>
export default {
  name: 'Error404'
}
</script>

<style scoped>
.error-404 {
  text-align: center;
  margin-top: 50px;
}
</style>
