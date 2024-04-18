<template>
  <main>
    <ErrorsDisplay :errors="listeErreurs"/>
    <div class="content">
      <router-view></router-view>
      <div class="filters">
        <p>Filtres :</p>
      </div>
      <div class="search-container">
        <input type="text" v-model="termeRecherche" placeholder="Recherche...">
      </div>
      <div class="filters">
        <p>Catégories :</p>
      </div>
      <div class="select-container">
        <select v-model="categorieSelectionnee">
          <option value="">Choisissez une catégorie</option>
          <option v-for="categorie in categories" :key="categorie.noCategorie" :value="categorie.noCategorie">{{ categorie.libelle }}</option>
        </select>
      </div>
    </div>
    <h2>Liste des Articles</h2>

    <!-- Affichage des cartes d'articles -->
    <div class="article-cards">
      <ArticleCard v-for="article in articlesFiltres" :key="article.nomArticle" :article="article" />
    </div>
  </main>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";
import axios from "../axios/instance";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";
import ArticleCard from "@/components/ArticleCard.vue";

const listeArticles = ref([]);
const listeErreurs = ref([]);
const termeRecherche = ref('');
const categorieSelectionnee = ref('');
const categories = ref([]);

async function recupererArticles() {
  try {
    const reponseArticles = await axios.get('/article');
    listeArticles.value = reponseArticles.data;
    console.log("Liste des articles :", listeArticles.value);

    const reponseCategories = await axios.get('/categories');
    categories.value = reponseCategories.data;
    console.log("Liste des catégories :", categories.value);
  } catch (error) {
    console.error('Erreur lors de la récupération des données :', error);
  }
}

onMounted(() => {
  recupererArticles();
});

const articlesFiltres = computed(() => {
  return listeArticles.value.filter(article => {
    const correspondNom = article.nomArticle.toLowerCase().includes(termeRecherche.value.toLowerCase());
    const correspondCategorie = categorieSelectionnee.value === '' || article.categorie.noCategorie === categorieSelectionnee.value;
    return correspondNom && correspondCategorie;
  });
});

</script>

<style scoped>
/* Styles spécifiques aux cartes d'articles */
.article-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* Espace entre les cartes */
}
.search-container {
  width: 500px; /* Choisissez une largeur fixe appropriée */
}
.select-container {
  width: 400px; /* Choisissez une largeur fixe appropriée */
}
/* Styles génériques pour les composants */
/* Vous pouvez ajouter des styles supplémentaires selon vos besoins */
</style>
