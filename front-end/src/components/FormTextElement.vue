<script setup>
// pour pouvoir utiliser des propriétés sur nos composant, je suis obligé de les définir avec la méthode defineProps
// une fois que c'est défini, je peux les utiliser dans mon template comme des varaibles de mon modèle
defineProps(['object', 'label', 'field', 'disabled'])
</script>



<template>
     <div class="form-element">
            <label>{{label}} </label>
            <!-- v-model="object[field]" : ce champs de saisie modifie l'attribut correspondant à la prop "field", de la référence au modèle correspondant à la porporiété "object" -->
            <input  v-model="object[field]" type="text" :disabled="disabled">
        </div>
</template>