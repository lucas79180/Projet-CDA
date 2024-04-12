<template>
  <main>
    <!-- Affichage des erreurs -->
    <ErrorsDisplay :errors="listeErreurs"/>

    <!-- Affichage du composant de modification -->

    <h2>Liste des utilisateurs</h2>

    <!-- Affichage de la table des utilisateurs -->
    <TableElement
        @edit="(user) => edit(user)"
        @delete="(user) => remove(user)"
        :labels="['Pseudo', 'Prénom', 'Nom', 'Email', 'Telephone', 'Rue', 'code_postal', 'Ville']"
        :fields="['pseudo', 'prenom', 'nom', 'email', 'telephone', 'rue', 'code_postal', 'ville']"
        :listeUser="listeUsers"
    />
  </main>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "../axios/instance";
import TableElement from "@/components/TableElement.vue";


const user = ref({
  pseudo: '',
  nom: '',
  prenom: '',
  email: '',
  telephone: '',
  rue: '',
  code_postal: '',
  ville: '',
  motDePasse: '',
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

<style scoped>
/* Ajoutez vos styles CSS ici */
</style>
