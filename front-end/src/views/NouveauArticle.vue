<template>
  <div class="nouveau-article">
    <h2 ref="formTitle" class="form-title">Article</h2>
    <div class="form-container">
      <form class="form" @submit.prevent="submitForm">
        <div class="form-group">
          <h3>Article</h3>
          <FormTextElement label="Nom de l'article" type="text" :object="articleRetrait.article" field="nomArticle"/>

          <label for="description">Description :</label>
          <textarea id="description" v-model="articleRetrait.article['description']" rows="4" required></textarea>

          <label for="categorie">Catégorie:</label>
          <select id="categorie" v-model="articleRetrait.article['categorie']" required v-if="categories.length > 0">
            <option value="" disabled>Sélectionnez une catégorie</option>
            <!-- <option v-for="categorie in categories" :key="categorie.noCategorie" :value="categorie.noCategorie">{{ categorie.libelle }}</option> -->
            <option v-for="categorie in categories" :key="categorie.noCategorie"
                    :value="{ 'noCategorie': categorie.noCategorie, 'libelle': categorie.libelle }">
              {{ categorie.libelle }}
            </option>

          </select>
          <span v-else>Loading categories...</span>

          <FormTextElement label="Mise à prix (en points)" type="number" :object="articleRetrait.article"
                           field="miseAPrix"
                           required/>

          <FormTextElement label="Début de l'enchère" type="date" :object="articleRetrait.article"
                           field="dateDebutEncheres" required/>

          <FormTextElement label="Fin de l'enchère" type="date" :object="articleRetrait.article"
                           field="dateFinEncheres" required/>

          <label for="image">Image:</label>
          <input type="file" id="image" @change="handleImageUpload" accept="image/*">
        </div>

        <div class="form-group">
          <h3>Retrait</h3>
          <FormTextElement label="Rue" type="text" :object="articleRetrait.retrait" field="rue" required/>

          <FormTextElement label="Code Postal" type="text" :object="articleRetrait.retrait"
                           field="code_postal" required/>

          <FormTextElement label="Ville" type="text" :object="articleRetrait.retrait" field="ville" required/>
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
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import router from "@/router.js";

onMounted(async () => {

  // Obtention des informations de l'utilisateur connecté
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

    // Déclaration des variables
    const isEditing = ref(false); // Permet de connaitre l'état de la page
    const categories = ref([]); // Contiendra la liste des catégorie chargé avec les datas en bdd
    const listeErreurs = ref([]); // Contiendra la liste des erreurs
    const userInfo = ref([]);
    const refArticle = useRoute().params.id; // On récupéré l'id de l'article passé en paramètre
    const formTitle = ref(null); // Contiendra le libellé de H2
    const imageFile = ref(null)
    const articleRetrait = ref({
      article: {
        noArticle: null,
        nomArticle: '',
        description: '',
        //categorie: null,
        miseAPrix: 0,
        prixVente: 0,
        dateDebutEncheres: '',
        dateFinEncheres: '',
        imageUrl: ''
      },
      retrait: {
        rue: '',
        code_postal: '',
        ville: ''
      },
      imageFile: null
    });

    // Permet de charger la page en fonction de ce qui est passé en param
    async function chargementArticle() {
      console.log("refArticle = ", refArticle)
      if (refArticle === "add") {
        console.log("mode ADD")
        isEditing.value = false;
        formTitle.value.innerText = "Création d'un nouvel article";
      } else {
        console.log("mode EDIT")
        isEditing.value = true;
        // En mode EDIT, la variable refArticle comprend
        formTitle.value.innerText = "EDIT";

        try {
          const reponseHTTP = await axios.get('/article/' + refArticle)
          articleRetrait.value = reponseHTTP.data;
          console.log(articleRetrait.value)
          formTitle.value.innerText = "Modification de l'article " + articleRetrait.value.article.nomArticle;

          // Comparaison de la date de début d'enchères avec la date du jour
          const dateDebutEncheres = new Date(articleRetrait.value.article.dateDebutEncheres);
          const dateDuJour = new Date();

          if (dateDebutEncheres <= dateDuJour) {
            // La date de début d'enchères est antérieur ou égale à la date du jour donc modification non autorisé
            console.log("La date de début d'enchères est antérieur ou égale à la date du jour donc modification non autorisé");
            isEditing.value = false;
            await router.push('/article/' + refArticle);

          }
        } catch (erreur) {
          console.error("Erreur lors de la récupération du détail de l'article :", erreur);
          listeErreurs.value.push("Erreur lors de la récupération du détail de l'article.");

          // En cas d'erreur alors article non trouvé donc retour à la page précédente
          await router.push('/non-trouve');
        }
      }
    }

    // Définir la date du jour
    articleRetrait.value.article.dateDebutEncheres = new Date().toISOString().slice(0, 10);

    // Définir la date dans 30 jours
    const thirtyDaysLater = new Date();
    thirtyDaysLater.setDate(thirtyDaysLater.getDate() + 30);
    articleRetrait.value.article.dateFinEncheres = thirtyDaysLater.toISOString().slice(0, 10);


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

    async function getUser() {
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
      chargementArticle();
    });


    // Bouton "enregistrer"
    async function submitForm() {
      console.log("--LOG-- exec submitForm")
      try {
        // Envoi de l'image
        console.log("--LOG-- articleRetrait.value.imageFile : ", articleRetrait.value.imageFile)
        if (!articleRetrait.value.imageFile == null) {
          const formData = new FormData();
          formData.append('image', articleRetrait.value.imageFile); // Ajoutez l'image à FormData
          // Envoi de l'image au serveur
          const responseImg = await axios.post('/upload-image', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          // Récupération de l'URL de l'image depuis la réponse du serveur
          console.log("--LOG-- responseImg : ", responseImg);
          articleRetrait.value.article.imageUrl = responseImg.data;
          console.log("--LOG-- :responseImg.data ", responseImg.data.imageUrl);
          console.log("--LOG -- articleRetrait.value.article.imageUrl : " + articleRetrait.value.article.imageUrl);
        }

        // Envoi dans le back des datas via axios
        articleRetrait.value.article.vendeur = userInfo.value;
        articleRetrait.value.article.prixVente = articleRetrait.value.article.miseAPrix;
        console.log("--LOG-- articleRetrait.value.article.vendeur :");
        console.log(articleRetrait.value.article.vendeur);
        articleRetrait.value.article = await axios.post(`article`, articleRetrait.value)

        // on vide la variable listeErreurs
        listeErreurs.value = []

        // Après avoir sauvegarder en bddd, redirection vers la page de l'article
        console.log("--LOG-- enreg ok")
        console.log("--LOG-- articleRetrait.value : ", articleRetrait.value)
        console.log("--LOG-- articleRetrait.value.article.noArticle :", articleRetrait.value.article.data.article.noArticle)
        await router.push('/article/' + articleRetrait.value.article.data.article.noArticle);

      } catch (erreur) {
        console.log("--LOG-- >catch");
        console.error("Erreur lors de l'envoi du formulaire:", erreur);
        listeErreurs.value.push("Erreur lors de l'envoi du formulaire.");
      }

    }

    // Fonction pour gérer le téléchargement de l'image
    function handleImageUpload(event) {
      console.log("--LOG-- exec handleImageUpload")
      const file = event.target.files[0];
      // Assignez le fichier à votre articleRetrait
      articleRetrait.value.imageFile = file;
      console.log("--LOG-- articleRetrait.value.imageFile : ", articleRetrait.value.imageFile)
      console.log("--LOG-- file = ", file)
    }

    function cancel() {
      window.history.back()
    }

    return {
      articleRetrait,
      categories,
      listeErreurs,
      submitForm,
      cancel,
      handleImageUpload,
      formTitle,
      imageFile
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


select {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
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
  background-color: #0056b3;
}

</style>
