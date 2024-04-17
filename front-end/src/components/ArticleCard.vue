<template>
  <div class="article-card" @click="navigateToDetail">
    <h3>{{ article.nomArticle }}</h3>
    <p>Date de fin des enchères : {{ formatDate(article.dateFinEncheres) }}</p>
    <p>Prix de vente : {{ article.prixVente }} pt</p>
    <p>Vendeur : {{ article.vendeur.pseudo }}</p>
    <!-- Vous pouvez ajouter d'autres détails de l'article ici -->
  </div>
</template>

<script setup>
import {defineProps} from 'vue';
import {useRouter} from 'vue-router';

// Définir les propriétés attendues du composant
const props = defineProps({
  article: {
    type: Object,
    required: true
  }
});

const router = useRouter();

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const year = date.getFullYear();
  return `${day}/${month}/${year}`;
};

const navigateToDetail = () => {
  router.push({name: 'articleDetail', params: {id: props.article.noArticle}});
};
</script>

<style scoped>
.article-card {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  width: 300px;
  cursor: pointer; /* Ajoutez ceci pour indiquer que la carte est cliquable */
  /* Ajoutez d'autres styles CSS selon vos besoins */
}
</style>
