<template>
  <div class="nouvelle-vente">
    <div class="form-container">
      <h2 class="form-title">Nouvelle vente</h2>
      <form class="form" @submit.prevent="submitForm">
        <div class="form-group">
          <FormTextElement label="Nom de l'article" type="text" :object="article" field="nomArticle"/>
        </div>
        <div class="form-group">
          <label for="categorie">Description :</label>
          <textarea id="description" v-model="article['description']" rows="4" required></textarea>
        </div>
        <div class="form-group">
          <label for="categorie">Catégorie:</label>
          <select id="categorie" v-model="article['categorie']" required v-if="categories.length > 0">
            <option value="" disabled>Sélectionnez une catégorie</option>
           <!-- <option v-for="categorie in categories" :key="categorie.noCategorie" :value="categorie.noCategorie">{{ categorie.libelle }}</option> -->
            <option v-for="categorie in categories" :key="categorie.noCategorie" :value="{ 'noCategorie': categorie.noCategorie, 'libelle': categorie.libelle }">
              {{ categorie.libelle }}
            </option>



          </select>
          <span v-else>Loading categories...</span>
        </div>

        <div class="form-group">
          <FormTextElement label="Mise à prix" type="number" :object="article" field="miseAPrix" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Début de l'enchère" type="datetime-local" :object="article" field="dateDebutEncheres" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Fin de l'enchère" type="datetime-local" :object="article" field="dateFinEncheres" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Rue" type="text" :object="article.retrait" field="rue" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Code Postal" type="text" :object="article.retrait" field="codePostal" required/>
        </div>
        <div class="form-group">
          <FormTextElement label="Retrait - Ville" type="text" :object="article.retrait" field="ville" required/>
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

export default {
  components: {
    FormTextElement
  },
  setup() {
    const article = ref({
      nomArticle: '',
      description: '',
      //categorie: null,
      miseAPrix: 0,
      dateDebutEncheres: '',
      dateFinEncheres: '',
      retrait: {
        rue: '',
        codePostal: '',
        ville: ''
      }
    });

    const categories = ref([]);
    const listeErreurs = ref([]);

    async function recupererCategories() {
      try {
        console.log("--LOG-- recupererCategories");
        const reponseHTTP = await axios.get('/categories');

        categories.value = reponseHTTP.data;

        console.log("--LOG-- categories : ", categories);
      } catch (erreur) {
        console.error("Erreur lors de la récupération des catégories:", erreur);
        listeErreurs.value.push("Erreur lors de la récupération des catégories.");
      }
    }

    onMounted(() => {
      recupererCategories();
    });



    async function submitForm() {
      console.log("--LOG-- submitForm");
      console.log("--LOG-- article.value : ");
      console.log(article.value);
      try {
        console.log("--LOG-- >try");
        // Envoi dans le back des datas via axios
        await axios.post(`articles`, article.value)

        // si jamais on n'a pas d'erreur
        // on vide la variable listeErreurs
        listeErreurs.value = []

        // après enregistrement, réinistalisation du forms :
        article.nomArticle = ''
        article.description = ''
        article.categorie = null
        article.miseAPrix = 0
        article.dateDebutEncheres = ''
        article.dateFinEncheres = ''
        article.retrait = {
          rue: '',
          codePostal: '',
          ville: ''
        }


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
      article,
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
