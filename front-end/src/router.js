// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import NouvelleVente from './views/NouvelleVente.vue'; // Assurez-vous de pointer vers le bon emplacement de votre composant NouvelleVente

const routes = [
    {
        path: '/nouvelle-vente',
        name: 'NouvelleVente',
        component: NouvelleVente
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
