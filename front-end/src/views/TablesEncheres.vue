<template>
  <main>
    <ErrorsDisplay :errors="listeErreurs"/>
    <h2>Liste des Articles</h2>

    <!-- Affichage des cartes d'articles -->
    <div class="article-cards">
      <ArticleCard v-for="article in listeArticles" :key="article.nomArticle" :article="article" />
    </div>
  </main>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "../axios/instance";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";
import ArticleCard from "@/components/ArticleCard.vue";

const listeArticles = ref([]);
const listeErreurs = ref([]);

async function recupererArticles() {
  try {
    const reponseHTTP = await axios.get('/article');
    listeArticles.value = reponseHTTP.data;
    console.log("Liste des articles :", listeArticles.value);
  } catch (error) {
    console.error('Erreur lors de la récupération des articles :', error);
  }
}

onMounted(() => {
  recupererArticles();
});
</script>

<style scoped>
/* Styles spécifiques aux cartes d'articles */
.article-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* Espace entre les cartes */
}

/* Styles génériques pour les composants */
/* Vous pouvez ajouter des styles supplémentaires selon vos besoins */
</style>
