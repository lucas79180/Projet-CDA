<template>
  <div class="nouvelle-vente">
    <div class="form-container">
      <h2 class="form-title">Nouvelle vente</h2>
      <form class="form" @submit.prevent="submitForm">
        <div class="form-group">
          <label for="nom-article">Article:</label>
          <input type="text" id="nom-article" v-model="article.nomArticle" required>
        </div>
        <div class="form-group">
          <label for="description">Description:</label>
          <textarea id="description" v-model="article.description" rows="4" required></textarea>
        </div>
        <div class="form-group">
          <label for="categorie">Catégorie:</label>
          <select id="categorie" v-model="article.categorie" required>
            <option value="" disabled>Sélectionnez une catégorie</option>
            <option v-for="categorie in categories" :key="categorie.noCategorie" :value="categorie.noCategorie">{{ categorie.libelle }}</option>
          </select>
        </div>
        <div class="form-group">
          <label for="photo">Photo de l'article:</label>
          <input type="file" id="photo" @change="handleFileUpload" accept="image/*">
          <!-- Ajoutez ici un code pour afficher la photo -->
        </div>
        <div class="form-group">
          <label for="prix">Mise à prix:</label>
          <input type="number" id="prix" v-model="article.miseAPrix" required>
        </div>
        <div class="form-group">
          <label for="date-debut">Début de l'enchère:</label>
          <input type="datetime-local" id="date-debut" v-model="article.dateDebutEncheres" required>
        </div>
        <div class="form-group">
          <label for="date-fin">Fin de l'enchère:</label>
          <input type="datetime-local" id="date-fin" v-model="article.dateFinEncheres" required>
        </div>
        <div class="form-group">
          <label for="retrait">Retrait:</label>
          <div class="address">
            <input type="text" id="street" v-model="article.retrait.rue" placeholder="Rue" required>
            <input type="text" id="postal-code" v-model="article.retrait.codePostal" placeholder="Code postal" required>
            <input type="text" id="city" v-model="article.retrait.ville" placeholder="Ville" required>
          </div>
        </div>
        <div class="form-actions">
          <button type="submit">Enregistrer</button>
          <button type="button" @click="cancel">Annuler</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import {onMounted, ref} from "vue"; // Importez axios si vous utilisez pour faire des requêtes HTTP

// listeGenres : va être utilisé pour remplir notre table
const categories = ref([])

// errors : va être utilisé pour lister nos erreurs
const errors =  ref([])

async function init() {

  // initialise la liste des catégories
  let reponseHTTP = await axios.get('/categories')
  categories.value = reponseHTTP.data
}

onMounted(() => {
  init()
})

export default {
  data() {
    return {
      article: {
        nomArticle: '',
        description: '',
        categorie: null,
        miseAPrix: 0,
        dateDebutEncheres: '',
        dateFinEncheres: '',
        retrait: {
          rue: '',
          codePostal: '',
          ville: ''
        },
      },
      categories: [] // Tableau pour stocker les catégories
    };
  },
  methods: {
    submitForm() {
      // Envoyer l'article à votre API backend (utilisez axios ou un autre client HTTP)
      axios.post('/api/articles', this.article)
          .then(response => {
            // Traitement de la réponse ou redirection vers une autre page
            console.log(response.data);
            // Redirection vers une autre page par exemple
            this.$router.push('/articles/' + response.data.id);
          })
          .catch(error => {
            // Gestion des erreurs
            console.error('Erreur lors de la création de l\'article : ', error);
          });
    },
    cancel() {
      // Redirection vers une autre page ou traitement pour annuler l'action
      console.log('Annulation de la création de l\'article');
    },
  }
}
</script>



<style scoped>
/* Vos styles CSS existants */

.form-container {
  text-align: center;
  margin-top: 50px;
}

.form-title {
  color: #003A5D; /* Bleu foncé */
}

.form {
  display: inline-block;
  text-align: left;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="number"],
textarea,
select {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

input[type="file"] {
  /* Stylez selon vos préférences */
}

.form-actions {
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #003A5D; /* Bleu foncé */
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #D0121B; /* Rouge */
}
</style>
