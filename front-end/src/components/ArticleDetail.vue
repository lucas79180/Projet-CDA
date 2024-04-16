<template>
  <div class="article-detail" v-if="article">
    <h1> Détail Vente</h1>
    <h2>{{ article.nomArticle }}</h2>
    <p>Description : {{article.description}}</p>
    <p>Catégorie : {{article.categorie.libelle}}</p>
    <p>Mise à prix : {{article.miseAPrix}}</p>
    <p>Date de fin des enchères : {{ formatDate(article.dateFinEncheres) }}</p>
    <p>Retrait : {{article.retrait}}</p>
    <p>Prix de vente : {{ article.prixVente }} pt</p>
    <p>Vendeur : {{ article.vendeur.pseudo }}</p>
    <p>Proposition :
      <input type="number" v-model.number="bidPrice" :min="article.prixVente" step="1">
    </p>
    <!-- Bouton pour valider la proposition -->
    <button @click="placeBid">Enchérir</button>
    <!-- Autres détails de l'article -->
  </div>
  <div v-else>
    Chargement...
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from '../axios/instance';

const article = ref(null);
const route = useRoute();
const router = useRouter();
const bidPrice = ref(0);

async function fetchArticle() {
  try {
    const response = await axios.get(`/article/${route.params.id}`);
    article.value = response.data;
    // Définir le prix initial comme valeur minimale pour le champ d'entrée
    bidPrice.value = article.value.prixVente;
  } catch (error) {
    console.error('Erreur lors de la récupération de l\'article :', error);
  }
}

onMounted(() => {
  fetchArticle();
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const year = date.getFullYear();
  return `${day}/${month}/${year}`;
};

const placeBid = async () => {
  try {
    const response = await axios.post(`/article/${route.params.id}/bid`, {
      bidPrice: bidPrice.value
    });
    // Mettre à jour le prix de vente de l'article avec la nouvelle valeur retournée par le serveur
    article.value.prixVente = response.data.newPrice;
  } catch (error) {
    console.error('Erreur lors de la soumission de l\'enchère :', error);
  }
};
</script>

<style scoped>
/* Styles spécifiques à la page de détail de l'article */
</style>
