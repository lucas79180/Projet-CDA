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
  code_postal : '',
  ville: '',
  mot_de_passe: '',
})



const listeErreurs = ref([])


async function creerUser(){
  try {
    // on veut passer dans le corps de la requête HTTP le JSON correspondant à la valeur de notre modèle : membre
    await axios.post('/utilisateurs', user.value)

    // si jamais on n'a pas d'erreur
    // on vide la variable listeErreurs
    listeErreurs.value = []

    // une fois qu'on a ajouté la membre, on recharge la liste des membres

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
    user.value.administrateur = false
  }
  catch (error){
    // si jamais j'ai des erreur

    // je mets à jour la variable listeErreurs de notre modèle afin que le template les affiche (si jamais il y en a)
    if (error.response.data.errors)
      listeErreurs.value = error.response.data.errors
  }
}

</script>
<template>
  <main>

    <!--on affiche erreurs éventuelles (ne s'affiche que si la liste d'erreur n'est pas vide) -->
    <ErrorsDisplay :errors="listeErreurs"/>

    <h1>Inscription</h1>

    <!--
        Je vais créer une balise form juste pour des raisons sémantiques (accessibilité, référencement par les moteurs de recherche)
        Il ne va pas être validé car je vais lancer les requeêtes POST avec javascript
     -->
    <form class="form">

      <!-- FormTextElement est un composant définie dans components/FormTextElement.vue :
          il va prendre en paramètres des propriétés (props) :
          - label (valeur = texte): correspond à ce qu'on va afficher dans <label>{{ label }} </label>
          - object (valeur = reference, donc on mets : devant) : correspond à une reférence de l'objet que je veux modifier dans mon composant
          ici la reference a été déclarée en havascript (plus haut) avec const membre = ref({....}
          - field (valeur = texte) : correspond au champs de l'objet que je vais vouloir éditer à travers ce composant
      -->
      <FormTextElement label="Pseudo" type="text" :object="user" field="pseudo"/>

      <FormTextElement label="Nom" type="text" :object="user" field="nom"/>

      <FormTextElement label="Prénom" type="text" :object="user" field="prenom" />

      <FormTextElement label="Email" type="email" :object="user" field="email"/>

      <FormTextElement label="Mot de passe" type="password" :object="user" field="mot_de_passe"/>

      <FormTextElement label="Téléphone" type="tel" :object="user" field="telephone"/>

      <FormTextElement label="Rue" type="text" :object="user" field="rue"/>

      <FormTextElement label="Code postal" type="text" :object="user" field="code_postal"/>

      <FormTextElement label="Ville" type="text" :object="user" field="ville" />


      <!--
          on ne veut pas valider le formulaire quand on clique sur le bouton , donc il faut rajouter type="button"
          par contre, je veux appeler lorsqu'on clique dessus une méthode Javascript : creermembre ( @click="creermembre")
      -->
      <button type="submit" @click="creerUser">Créer utilisateur</button>
    </form>
  </main>
</template>

<style scoped>
/* Style général */
main {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* Pour centrer verticalement */
}

h1 {
  margin-bottom: 10px;
  color: #333333;
  text-align: center;
}

form {
  width: 100%; /* Pour s'assurer que le formulaire occupe toute la largeur disponible */
}

form {
  font-weight: bold;
  margin-bottom: 10px;
  color: #666666;
}

input[type="text"],
input[type="password"],
input[type="email"],
input[type="tel"] {
  width: calc(100% - 24px); /* Ajustement pour le padding */
  padding: 6px; /* Réduction de la taille du padding */
  border: 1px solid #cccccc;
  border-radius: 6px;
  font-size: 12px; /* Réduction de la taille de la police */
}

button {
  width: calc(100% - 24px); /* Ajustement pour le padding */
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #004981;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 10px; /* Ajout d'espace entre le dernier champ et le bouton */
}

button:hover {
  background-color: #0056b3;
}

</style>