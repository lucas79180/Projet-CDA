<script setup>

import {onMounted, ref} from "vue";
import axios from "../axios/instance";
import TableElement from "@/components/TableElement.vue";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";
import TableElementEnchere from "@/components/TableElementEnchere.vue";


const article = ref({
  noArticle: '',
  nomArticle: '',
  description: '',
  dateDebutEncheres: '',
  dateFinEncheres: '',
  miseAPrix: '',
  prixVente: '',
  categorie: '',
  retrait: '',
  encheres: '',
  vendeur: ''
})

const listeArticles = ref([])
const listeErreurs = ref([])

async function recupererarticle() {
  try {
    const reponseHTTP = await axios.get('/article');
    listeArticles.value = reponseHTTP.data;
    console.log("Liste des articles :", listeArticles.value);
  } catch (error) {
    console.error('Erreur lors de la récupération des enchères :', error);
  }
}
onMounted(() => {
  recupererarticle()
});


</script>
<template>
  <main>

    <!--on affiche erreurs éventuelles (ne s'affiche que si la liste d'erreur n'est pas vide) -->
    <ErrorsDisplay :errors="listeErreurs"/>
    <h2>Liste des Encheres</h2>

    <!-- TableElement est un composant définie dans components/TableElement.vue :
          il va prendre en paramètres des propriétés (props) :
              - labels (valeur = liste de Strings): correspond aux th de la table
              - fields (valeur = liste de Strings): correspond aux attributs à afficher pour chaque élémentd e ma liste
              - listeModel (valeur = référence vers une liste définie dans le javascript avec : const listemembres = ref([])):
               correspond à la référence vers la variable du modèle pour laquelle on souhaite afficher le tableau

      @edit="(membre) => edit(membre)"
      lorsque TableElement va envoyer un événement emit('edit'), je vais appeler la méthode edit(membre) définie plus haut

      @delete="(membre) => remove(membre)"
      lorsque TableElement va envoyer un événement emit('delete'), je vais appeler la méthode remove(membre) définie plus haut
      -->
    <TableElementEnchere
        :labels="['Numéro de l article', 'nom de l article', 'Description', 'date de debut de l encheres', 'date de fin de l encheres','mise a Prix', 'prix de vente', 'categorie', 'retrait', 'encheres', 'vendeur']"
        :fields="['noArticle', 'nomArticle', 'description', 'dateDebutEncheres', 'dateFinEncheres','miseAPrix', 'prixVente', 'categorie', 'retrait', 'encheres', 'vendeur']"
        :listeArticle="listeArticles" />

  </main>
</template>

<style scoped>
</style>