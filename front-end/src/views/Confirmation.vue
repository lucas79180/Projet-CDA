<script setup>
import axios from '@/axios/instance'
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router'

const user = ref({})

async function remove() {
  try {
    await axios.delete(`/utilisateurs/${user.value.id}`);
    // Redirection vers la liste des utilisateurs après la suppression réussie
    router.push('/utilisateurs');
  } catch (error) {
    console.error("Une erreur s'est produite lors de la suppression de l'utilisateur :", error);
  }
}

function cancel() {
  router.back(); // Revenir à la page précédente
}

onMounted(async () => {
  const idUser = useRoute().params.id;
  const result = await axios.get(`/utilisateurs/${idUser}`);
  user.value = result.data;
});
</script>


