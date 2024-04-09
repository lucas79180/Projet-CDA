<script setup>

import {ref} from "vue";
import FormTextElement from "@/components/FormTextElement.vue";
import axios from "../axios/instance";


const user = ref({
  pseudo : '',
  nom : '',
  prenom : '',
  email : '',
  telephone : '',
  rue : '',
  codePostal : '',
  ville: '',
  motDePasse: '',
})

const listeUsers = ref([])

const listeErreurs = ref([])



async function creerUser(){
 console.log(user.value);
  try {
    // on veut passer dans le corps de la requête HTTP le JSON correspondant à la valeur de notre modèle : membre
    await axios.post('/utilisateurs', user.value)

    // si jamais on n'a pas d'erreur
    // on vide la variable listeErreurs
    listeErreurs.value = []

    // une fois qu'on a ajouté la membre, on recharge la liste des membres
    //recupererUser()

    // on reinitialise le formulaire
    user.value.pseudo = ''
    user.value.prenom = ''
    user.value.nom = ''
    user.value.code_postal = ''
    user.value.ville = ''
    user.value.mot_de_passe = ''
    user.value.rue = ''
    user.value.email = ''
    user.value.telephone = ''
  }
  catch (error){
    // si jamais j'ai des erreur

    // je mets à jour la variable listeErreurs de notre modèle afin que le template les affiche (si jamais il y en a)
    if (error.response.data.errors)
      listeErreurs.value = error.response.data.errors
  }
}

console.log("user");

</script>

<template>
  <main>

    <!--on affiche erreurs éventuelles (ne s'affiche que si la liste d'erreur n'est pas vide) -->
    <ErrorsDisplay :errors="listeErreurs"/>

    <h1>Ajout membre</h1>

    <!--
        Je vais créer une balise form juste pour des raisons sémantiques (accessibilité, référencement par les moteurs de recherche)
        Il ne va pas être validé car je vais lancer les requeêtes POST avec javascript
     -->
    <form>

      <!-- FormTextElement est un composant définie dans components/FormTextElement.vue :
          il va prendre en paramètres des propriétés (props) :
          - label (valeur = texte): correspond à ce qu'on va afficher dans <label>{{ label }} </label>
          - object (valeur = reference, donc on mets : devant) : correspond à une reférence de l'objet que je veux modifier dans mon composant
          ici la reference a été déclarée en havascript (plus haut) avec const membre = ref({....}
          - field (valeur = texte) : correspond au champs de l'objet que je vais vouloir éditer à travers ce composant
      -->
      <FormTextElement label="Pseudo" :object="user" field="pseudo"/>

      <FormTextElement label="Nom" :object="user" field="nom"/>

      <FormTextElement label="Prenom" :object="user" field="prenom"/>

      <FormTextElement label="Email" :object="user" field="email"/>

      <FormTextElement label="Telephone" :object="user" field="telephone"/>

      <FormTextElement label="Rue" :object="user" field="rue"/>

      <FormTextElement label="Code postal" :object="user" field="code_postal"/>

      <FormTextElement label="Ville" :object="user" field="ville"/>

      <FormTextElement label="Mot de passe" :object="user" field="mot_de_passe"/>

      <!--
          on ne veut pas valider le formulaire quand on clique sur le bouton , donc il faut rajouter type="button"
          par contre, je veux appeler lorsqu'on clique dessus une méthode Javascript : creermembre ( @click="creermembre")
      -->
      <button type="button" @click="creerUser">Créer utilisateur</button>
    </form>

  </main>
</template>

<style scoped>

</style>