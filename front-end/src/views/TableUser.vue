<script setup>

import {onMounted, ref} from "vue";
import axios from "../axios/instance";
import TableElement from "@/components/TableElement.vue";
import ErrorsDisplay from "@/components/ErrorsDisplay.vue";


const user = ref({
  pseudo: '',
  nom: '',
  prenom: '',
  email: '',
  telephone: '',
  rue: '',
  code_postal: '',
  ville: '',
  mot_de_passe: '',
  administrateur: false,
  actif: false
})

const listeUsers = ref([])
const listeErreurs = ref([])
const editingUser = ref(null);

async function edit(user) {
  await axios.put(`/utilisateurs/${user.id}`, user)
  recupereruser()
}

async function remove(user) {
  await axios.delete(`/utilisateurs/${user.id}`, user)
  recupereruser()
}

async function recupereruser() {
  const reponseHTTP = await axios.get('/utilisateurs')
  listeUsers.value = reponseHTTP.data
}

onMounted(() => {
  recupereruser()
});


</script>
<template>
  <main>

    <!--on affiche erreurs éventuelles (ne s'affiche que si la liste d'erreur n'est pas vide) -->
    <ErrorsDisplay :errors="listeErreurs"/>
    <h2>Liste des utilisateurs</h2>

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
    <TableElement
        @edit="(user) => edit(user)"
        @delete="(user) => remove(user)"
        :labels="['Pseudo', 'Prénom', 'Nom', 'Email', 'Telephone', 'Rue', 'Code postal', 'Ville','Administrateur','Actif']"
        :fields="['pseudo', 'prenom', 'nom', 'email', 'telephone', 'rue', 'code_postal', 'ville','administrateur','actif']"
        :listeUser="listeUsers" />

  </main>
</template>

<style scoped>
</style>