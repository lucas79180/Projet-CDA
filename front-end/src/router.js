// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import NouvelleVente from './views/NouvelleVente.vue';
import User from "./views/User.vue"; // Assurez-vous de pointer vers le bon emplacement de votre composant NouvelleVente
import UpdateUser from "@/views/UpdateUser.vue";
import InfoUser from "@/views/infoUser.vue";
const routes = [
    {
        path: '/nouvelle-vente',
        name: 'NouvelleVente',
        component: NouvelleVente
    },
    {
        path: '/inscription',
        name: 'User',
        component: User
    },
    {
        path: '/update',
        name: 'updateUser',
        component: UpdateUser
    },
    {
        path: '/monprofil',
        name: 'infoUser',
        component: InfoUser
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;