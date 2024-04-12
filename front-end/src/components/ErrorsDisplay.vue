<script setup>
// pour pouvoir utiliser des propriétés sur nos composant, je suis obligé de les définir avec la méthode defineProps
// une fois que c'est défini, je peux les utiliser dans mon template comme des varaibles de mon modèle
defineProps(['errors'])
</script>



<template>
    <!-- paragraphe qui affiche les erreurs éventuelles (ne s'affiche que si la liste d'erreur n'est pas vide) -->
    <p v-if="errors?.length > 0" class="errors">
      <ul>
        <li v-for="error in errors">
          {{ error.field }} {{  error.defaultMessage }}
        </li>
      </ul>
    </p>
</template>