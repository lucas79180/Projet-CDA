// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import NouvelleVente from './views/NouvelleVente.vue';
import UpdateUser from "@/views/UpdateUser.vue";
import InfoUser from "@/views/infoUser.vue";
import User from "./views/User.vue";
import Login from "./views/Login.vue";
import TableUser from "@/views/TableUser.vue";
import Confirmation from "@/views/Confirmation.vue";
import ForgotPassword from "@/views/ForgotPassword.vue"; // Assurez-vous de pointer vers le bon emplacement de votre composant NouvelleVente

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
        path: '/connexion',
        name: 'Login',
        component: Login
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
    },
    {
        path: '/utilisateurs',
        name: 'TableUser',
        component: TableUser
    },
    {
        path: '/utilisateurs/:id/delete',
        name: 'Confirmation',
        props: true,
        component: Confirmation
    },
    {
        path: '/forgot-password',
        name: 'ForgotPassword',
        component: ForgotPassword
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
