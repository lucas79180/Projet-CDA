<template>
  <div class="article-detail" v-if="article">
    <h1> Détail Vente</h1>
    <h2>{{ article.article.nomArticle }}</h2>
    <p>Description : {{article.article.description}}</p>
    <p>Catégorie : {{article.article.categorie.libelle}}</p>
    <p>Mise à prix : {{article.article.miseAPrix}} point(s)</p>
    <p>Date de fin des enchères : {{ formatDate(article.article.dateFinEncheres) }}</p>
    <p>Retrait : {{article.retrait.rue}}, {{article.retrait.code_postal}}, {{article.retrait.ville}}</p>
    <p>Prix de vente : {{ article.article.prixVente }} pt par {{ listeEncheres.length > 0 ? listeEncheres[listeEncheres.length - 1].utilisateur.pseudo : 'Aucun utilisateur' }}</p>

    <p>Vendeur : {{ article.article.vendeur.pseudo }}</p>
    <p>Proposition :
      <input type="number" v-model.number="bidPrice" :min="article.article.prixVente" step="1">
    </p>
    <!-- Bouton pour valider la proposition -->
    <button class="button" @click="placeBid">Enchérir</button>
    <!-- Autres détails de l'article -->

    <table>

      <tr v-for="element in listeEnchere" :key="element.id">
        <!-- Affichage des données de l'utilisateur -->
        <td v-for="field in fields" :key="field">{{ element[field] }}</td>
      </tr>
    </table>

  </div>
  <div>
    <TableElementEnchere
        :labels="['Date de l\'enchere', 'Montant de l\'enchere', 'Utilisateur' ]"
        :fields="['dateEnchere', 'montantEnchere', 'utilisateur.pseudo']"
        :listeEnchere="listeEncheres" />
  </div>
</template>

<script setup>
import {ref, onMounted, watch} from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from '../axios/instance';
import TableElement from "@/components/TableElement.vue";
import TableElementEnchere from "@/components/TableElementEnchere.vue";

const listeEncheres = ref([])
const article = ref(null);
const route = useRoute();
const router = useRouter();
const bidPrice = ref(0);

async function fetchArticle() {
  try {
    const response = await axios.get(`/article/${route.params.id}`);
    article.value = response.data;
    console.log("--LOG-- article.value");
    console.log(article.value);
    // Définir le prix initial comme valeur minimale pour le champ d'entrée
    bidPrice.value = article.value.article.prixVente;
  } catch (error) {
    console.error('Erreur lors de la récupération de l\'article :', error);
  }
}

onMounted(() => {
  fetchArticle();
  recupererenchere();
});

async function recupererenchere() {
  const reponseHTTP = await axios.get('/encheres');
  // Inverser l'ordre de la liste des enchères
  listeEncheres.value = reponseHTTP.data.reverse();
  console.log("--LOG--", listeEncheres.value);
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


const placeBid = async () => {
  try {
    // Récupérer l'ID de l'article
    const articleId = article.value.article.noArticle;

    console.log('Envoi de la requête pour placer une enchère...');
    console.log('ID de l\'article:', articleId);
    console.log('Prix de l\'enchère proposé:', bidPrice.value);

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
    console.log('Réponse du serveur:', response.data);

    // Mettre à jour le prix de vente de l'article avec la nouvelle valeur retournée par le serveur
    article.value.prixVente = response.data.newPrice;

    console.log(dataEncheres)

    const responsePost = await axios.post(`/encheres`, dataEncheres);
    console.log('Réponse du serveur:', responsePost.data);

  } catch (error) {
    console.error('Erreur lors de la soumission de l\'enchère :', error);
  }
};

</script>

<style scoped>
button {
  margin-top: 20px;
  background-color: #004981;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

</style>
