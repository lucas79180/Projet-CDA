<script setup>
defineProps(['object', 'label', 'field', 'disabled', 'type'])
</script>

<template>
  <div class="form-element">
    <label>{{ label }}</label>
    <input v-model="object[field]" :type="type" :disabled="disabled" required>
  </div>
</template>


