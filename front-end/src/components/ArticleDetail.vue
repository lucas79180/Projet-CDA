<template>
  <div class="articleDetail" v-if="article">
    <h2>{{ article.article.nomArticle }}</h2>
    <div class="article-detail-container" v-if="article">
      <div class="article-detail-img" v-if="article && article.article.imageUrl">
        <img v-if="article && article.article.imageUrl" :src="article.article.imageUrl" alt="Image de l'article"
             width="145">
      </div>

      <div class="article-detail-contenu">
        <tr>
          <td>Description :</td>
          <td>{{ article.article.description }}</td>
        </tr>
        <tr>
          <td>Catégorie :</td>
          <td>{{ article.article.categorie.libelle }}</td>
        </tr>
        <br>
        <tr>
          <td>Retrait :</td>
          <td>{{ article.retrait.rue }}</td>
        </tr>
        <tr>
          <td></td>
          <td>{{ article.retrait.code_postal }}, {{ article.retrait.ville }}</td>
        </tr>
        <br>
        <tr>
          <td>Vendeur :</td>
          <td>{{ article.article.vendeur.pseudo }}</td>
        </tr>

        <br>
        <p>Fin de des enchères dans {{ joursRestant(article.article.dateFinEncheres) }} jours
          <br>(le {{ formatDate(article.article.dateFinEncheres) }})</p>
      </div>

      <div class="article-detail-enchere">
        <div class="acheteur">
          <h3>Meilleur offre : {{ article.article.prixVente }} points</h3>
          <p>Par {{ listeEncheres.length > 0 ? listeEncheres[0].utilisateur.pseudo : 'Aucun utilisateur' }}
            <br> Mise à prix : {{ article.article.miseAPrix }} points
          </p>
          <br>
          <p>Votre enchère :
            <input type="number" v-model.number="bidPrice" :min="article.article.prixVente" step="1">
          </p>
          <tr>
            <td>
              <button class="button" @click="placeBid">Enchérir</button>
            </td>
            <td>
              <button class="button" @click="toggleListEncheres">
                {{ showListEncheres ? 'Masquer la liste des offres' : 'Afficher la liste des offres' }} les enchères
              </button>
            </td>
          </tr>
        </div>
      </div>
    </div>

    <div class="article-detail-listeencheres" v-show="showListEncheres">
      <TableElementEnchere
          :labels="['Date de l\'enchere', 'Montant de l\'enchere', 'Utilisateur' ]"
          :fields="['dateEnchere', 'montantEnchere', 'utilisateur.pseudo']"
          :listeEnchere="listeEncheres"/>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import axios from '../axios/instance';
import TableElementEnchere from "@/components/TableElementEnchere.vue";

const listeEncheres = ref([])
const article = ref(null);
const route = useRoute();
const router = useRouter();
const bidPrice = ref(0);
const showListEncheres = ref(false);

const toggleListEncheres = () => {
  showListEncheres.value = !showListEncheres.value;
};

async function fetchArticle() {
  try {
    const response = await axios.get(`/article/${route.params.id}`);
    article.value = response.data;
    console.log("--LOG-- article.value : ", article.value);
    // Définir le prix initial comme valeur minimale pour le champ d'entrée
    bidPrice.value = article.value.article.prixVente;
  } catch (error) {
    console.error('--ERR-- Erreur lors de la récupération de l\'article :', error);
  }
}

onMounted(() => {
  console.log("--LOG-- *** ArticleDetail.vue *** :");
  fetchArticle();
  recupererenchere();
});

async function recupererenchere() {
  const reponseHTTP = await axios.get(`/encheres/${route.params.id}`);
  // Inverser l'ordre de la liste des enchères
  listeEncheres.value = reponseHTTP.data;
  console.log("--LOG-- listeEncheres.value :", listeEncheres.value);
}

watch(article, (newValue) => {
  // Cette fonction sera appelée à chaque fois que la valeur de l'article change
  // Vous pouvez y ajouter tout code nécessaire pour mettre à jour l'affichage
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const year = date.getFullYear();

  return `${day}/${month}/${year}`;
};

const joursRestant = (dateString) => {
  const date = new Date(dateString);
  const today = new Date();

  // Calculating days left
  const diffTime = Math.abs(date - today);
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

  return `${diffDays}`;
};


const placeBid = async () => {
  try {
    // Récupérer l'ID de l'article
    const articleId = article.value.article.noArticle;

    console.log('--LOG-- Envoi de la requête pour placer une enchère...');
    console.log('--LOG-- ID de l\'article:', articleId);
    console.log('--LOG-- Prix de l\'enchère proposé:', bidPrice.value);

    const result = await axios.get('/login');
    const dataEncheres = {
      utilisateur: result.data,
      articleVendu: article.value.article, // Utiliser l'ID de l'article ici
      dateEnchere: new Date(), // Utiliser la date actuelle
      montantEnchere: bidPrice.value
    };
    const response = await axios.put(`/article/${route.params.id}/bid`, {
      bidPrice: bidPrice.value
    });
    window.location.reload();
    console.log('--LOG-- Réponse du serveur:', response.data);

    // Mettre à jour le prix de vente de l'article avec la nouvelle valeur retournée par le serveur
    article.value.prixVente = response.data.newPrice;

    console.log("--LOG-- dataEncheres :", dataEncheres)

    const responsePost = await axios.post(`/encheres`, dataEncheres);
    console.log('--LOG-- Réponse du serveur:', responsePost.data);

  } catch (error) {
    console.error('--ERR-- Erreur lors de la soumission de l\'enchère :', error);
  }
};

</script>

<style scoped>
.articleDetail {
  flex: 1; /* Pour occuper une partie égale de l'espace disponible */
  border: 0px solid black; /* Juste pour visualiser les div */
  padding: 3px; /* Ajoute un peu d'espacement */
  text-align: left;
  margin-block-start: 0px;
}

.article-detail-container {
  display: flex; /* Utilisation de Flexbox */
  align-items: flex-start;
  gap: 10px;
}

.article-detail-img,
.article-detail-contenu,
.article-detail-enchere,
.article-detail-listeencheres {
  border: 0px solid black; /* Juste pour visualiser les div */
  text-align: left;
  margin-block-start: 0px;

  padding: 50px;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.article-detail-img,
.article-detail-contenu,
.article-detail-enchere {
  flex-grow: 1;
}

button, p, h3 {
  margin: 0px;
}

p {
  color: #2c2c2c;
}

button {
  margin-top: 10px;
  background-color: #004981;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

</style>
