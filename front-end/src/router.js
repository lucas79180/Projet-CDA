// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import NouveauArticle from './views/NouveauArticle.vue';
import UpdateUser from "@/views/UpdateUser.vue";
import InfoUser from "@/views/infoUser.vue";
import User from "./views/User.vue";
import Login from "./views/Login.vue";
import TableUser from "@/views/TableUser.vue";
import Confirmation from "@/views/Confirmation.vue";
import ForgotPassword from "@/views/ForgotPassword.vue";
import Navbar from "@/views/Navbar.vue";

const routes = [
    {
        path: '/nouveau-article',
        name: 'NouveauArticle',
        component: NouveauArticle
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
        path: '/mon-profil',
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
