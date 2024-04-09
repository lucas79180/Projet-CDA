<template>
  <div class="nouvelle-vente">
  <div class="form-container">
    <h2 class="form-title">Nouvelle vente</h2>
    <form class="form">
      <div class="form-group">
        <label for="article">Article:</label>
        <input type="text" id="article" name="article" required>
      </div>
      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea>
      </div>
      <div class="form-group">
        <label for="category">Catégorie:</label>
        <input type="text" id="category" name="category" required>
      </div>
      <div class="form-group">
        <label for="photo">Photo de l'article:</label>
        <input type="file" id="photo" name="photo" accept="image/*">
        <!-- Ajoutez ici un code pour afficher la photo -->
      </div>
      <div class="form-group">
        <label for="price">Mise à prix:</label>
        <input type="number" id="price" name="price" required>
      </div>
      <div class="form-group">
        <label for="start-date">Début de l'enchère:</label>
        <input type="datetime-local" id="start-date" name="start-date" required>
      </div>
      <div class="form-group">
        <label for="end-date">Fin de l'enchère:</label>
        <input type="datetime-local" id="end-date" name="end-date" required>
      </div>
      <div class="form-group">
        <label for="address">Retrait:</label>
        <div class="address">
          <input type="text" id="street" name="street" placeholder="Rue" required>
          <input type="text" id="postal-code" name="postal-code" placeholder="Code postal" required>
          <input type="text" id="city" name="city" placeholder="Ville" required>
        </div>
      </div>
      <div class="form-actions">
        <button type="submit">Enregistrer</button>
        <button type="button" @click="cancel">Annuler</button>
      </div>
    </form>
  </div>
  </div>
</template>

<script>
export default {
  methods: {
    cancel() {
      // Ajoutez ici le code pour annuler l'actio
    }
  }
}
</script>

<style>
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
