<template>
  <div class="nouveau-article">
    <div class="form-container">
      <h2 class="form-title">Nouveau article</h2>
      <form class="form" @submit.prevent="submitForm">
        <div class="form-group">
          <FormTextElement label="Nom de l'article" type="text" :object="articleRetrait.article" field="nomArticle"/>
        </div>
        <div class="form-group">
          <label for="description">Description :</label>
          <textarea id="description" v-model="articleRetrait.article['description']" rows="4" required></textarea>
        </div>
        <div class="form-group">
          <label for="categorie">Catégorie:</label>
          <select id="categorie" v-model="articleRetrait.article['categorie']" required v-if="categories.length > 0">
            <option value="" disabled>Sélectionnez une catégorie</option>
           <!-- <option v-for="categorie in categories" :key="categorie.noCategorie" :value="categorie.noCategorie">{{ categorie.libelle }}</option> -->
            <option v-for="categorie in categories" :key="categorie.noCategorie" :value="{ 'noCategorie': categorie.noCategorie, 'libelle': categorie.libelle }">
              {{ categorie.libelle }}
            </option>

          </select>
          <span v-else>Loading categories...</span>
        </div>

        <div class="form-group">
          <FormTextElement label="Mise à prix" type="number" :object="articleRetrait.article" field="miseAPrix" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Début de l'enchère" type="datetime-local" :object="articleRetrait.article" field="dateDebutEncheres" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Fin de l'enchère" type="datetime-local" :object="articleRetrait.article" field="dateFinEncheres" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Rue" type="text" :object="articleRetrait.retrait" field="rue" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Code Postal" type="text" :object="articleRetrait.retrait" field="code_postal" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Ville" type="text" :object="articleRetrait.retrait" field="ville" required/>
        </div>
        <!-- Actions du formulaire -->
        <div class="form-actions">
          <button type="submit">Enregistrer</button>
          <button type="button" @click="cancel">Annuler</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import FormTextElement from "@/components/FormTextElement.vue";
import axios from "../axios/instance";
import { onMounted, ref } from "vue";

onMounted(async () => {
  try {
    const result = await axios.get('/login')
    userInfo.value = result.data // Assigner les données récupérées à userInfo
    console.log("--LOG-- result.data = ")
    console.log(userInfo.value)
  } catch (error) {
    console.error('Erreur lors de la récupération des données utilisateur:', error)
  }
})

export default {
  components: {
    FormTextElement
  },
  setup() {
    const articleRetrait = ref({
      article : {
        nomArticle: '',
        description: '',
        //categorie: null,
        miseAPrix: 0,
        prixVente : 0,
        dateDebutEncheres: '',
        dateFinEncheres: ''
      },
      retrait: {
        rue: '',
        code_postal: '',
        ville: ''
      }

    });

    // Définir la date du jour
    const today = new Date().toISOString().slice(0, 16);

    // Définir la date dans 30 jours
    const thirtyDaysLater = new Date();
    thirtyDaysLater.setDate(thirtyDaysLater.getDate() + 30);
    const dateThirtyDaysLater = thirtyDaysLater.toISOString().slice(0, 16);

    // Attribuer les valeurs aux champs date appropriés
    articleRetrait.value.article.dateDebutEncheres = today;
    articleRetrait.value.article.dateFinEncheres = dateThirtyDaysLater;

    // EN MODE TEST [DEBUT]
    //article.value.nomArticle = "test du nom de l'article";
    //article.value.description = "test de la description  de l'article";
    //article.value.miseAPrix = 150;
    //EN MODE TEST [FIN]

    const categories = ref([]);
    const listeErreurs = ref([]);
    const userInfo = ref([]);

    async function recupererCategories() {
      try {
        console.log("--LOG-- recupererCategories");
        const reponseHTTP = await axios.get('/categories');
        categories.value = reponseHTTP.data;
        console.log("--LOG-- reponseHTTP.data : " + reponseHTTP.data);
        console.log("--LOG-- categories : " + categories);
      } catch (erreur) {
        console.error("Erreur lors de la récupération des catégories:", erreur);
        listeErreurs.value.push("Erreur lors de la récupération des catégories.");
      }
    }

    async function getUser(){
      try {
        const result = await axios.get('/login')
        userInfo.value = result.data // Assigner les données récupérées à userInfo
        console.log("--LOG-- result.data = ")
        console.log(userInfo.value)
        articleRetrait.value.retrait.rue = userInfo.value.rue
        articleRetrait.value.retrait.code_postal = userInfo.value.code_postal
        articleRetrait.value.retrait.ville = userInfo.value.ville
      } catch (error) {
        console.error('Erreur lors de la récupération des données utilisateur:', error)
      }
    }

    onMounted(() => {
      recupererCategories();
      getUser();
    });


    // Bouton "enregistrer"
    async function submitForm() {
      console.log("--LOG-- exec submitForm")
      try {
        // Envoi dans le back des datas via axios
        articleRetrait.value.article.vendeur = userInfo.value;
        articleRetrait.value.article.prixVente = articleRetrait.value.article.miseAPrix;
        console.log("--LOG-- userInfo.value :");
        console.log(userInfo.value);
        console.log("--LOG-- articleRetrait.value.article.vendeur :");
        console.log(articleRetrait.value.article.vendeur);
        await axios.post(`articles`, articleRetrait.value)

        // si jamais on n'a pas d'erreur
        // on vide la variable listeErreurs
        listeErreurs.value = []

        // après enregistrement, réinistalisation du forms :
        articleRetrait.value.article.nomArticle = ''
        articleRetrait.value.article.description = ''
        articleRetrait.value.article.categorie = null
        articleRetrait.value.article.miseAPrix = 0
        articleRetrait.value.article.dateDebutEncheres = ''
        articleRetrait.value.article.dateFinEncheres = ''

        articleRetrait.value.retrait.rue = ''
        articleRetrait.value.retrait.code_postal = ''
        articleRetrait.value.retrait.ville = ''


      } catch (erreur) {
        console.log("--LOG-- >catch");
        console.error("Erreur lors de l'envoi du formulaire:", erreur);
        listeErreurs.value.push("Erreur lors de l'envoi du formulaire.");
      }
    }

    function cancel() {
      // Logique d'annulation du formulaire
    }

    return {
      articleRetrait,
      categories,
      listeErreurs,
      submitForm,
      cancel
    };
  }
};
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
