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
import axios from "@/axios/instance.js";
import {onMounted, ref} from "vue";
import Credit from "@/views/Credit.vue";
import Error404 from "@/views/Error404.vue";
import ErrorLayout from "@/views/ErrorLayout.vue";
import ArticleDetail from "@/components/ArticleDetail.vue";
import Authentification from "@/views/Authentification.vue";


function isAuthenticated() {
    return localStorage.getItem('jwt') !== null;
}

function isAdmin() {
    const userAdmin = JSON.parse(localStorage.getItem('userAdmin'));
    return userAdmin.administrateur === true; // Adapt to your user object structure
}

function isActif() {
    const userActif = JSON.parse(localStorage.getItem('userAdmin'));
console.log(userActif)
    return userActif.actif === true; // Adapt to your user object structure
}
const routes = [
    {
        path: '/nouveau-article/:id',
        name: 'NouveauArticle',
        component: NouveauArticle,
        meta: { requiresActif: true ,requiresAuth: true  }
    },
    {
        path: '/inscription',
        name: 'User',
        component: User,
    },
    {
        path: '/connexion',
        name: 'Login',
        component: Login
    },
    {
        path: '/update',
        name: 'updateUser',
        component: UpdateUser,
        meta: { requiresAuth: true , requiresActif: true }
    },
    {
        path: '/mon-profil',
        name: 'infoUser',
        component: InfoUser,
        meta: { requiresAuth: true , requiresActif: true  }
    },
    {
        path: '/utilisateurs',
        name: 'TableUser',
        component: TableUser,
        meta: { requiresAuth: true, requiresAdmin: true , requiresActif: true }
    },
    {
        path: '/utilisateurs/:id/delete',
        name: 'Confirmation',
        props: true,
        component: Confirmation,
        meta: { requiresAuth: true, requiresAdmin: true , requiresActif: true }
    },
    {
        path: '/forgot-password',
        name: 'ForgotPassword',
        component: ForgotPassword,
        meta: { requiresActif: true }
    },
    {
        path: '/ajout-credit',
        name: 'Credit',
        component: Credit,
        meta: { requiresAuth: true, requiresActif: true }
    },
    // Ajoutez une route de redirection pour /utilisateurs
    {
        path: '/utilisateurs',
        redirect: { name: 'Login' } // Redirigez vers la page de connexion
    },
    {
        path: '/article/:id',
        name: 'articleDetail',
        component: ArticleDetail,
        props: true,
        meta: { requiresAuth: true, requiresActif: true }
    },
    {
        path: '/',
        component: ErrorLayout,
        children: [
            {
                path: '/:catchAll(.*)',
                component: Error404
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth && !isAuthenticated()) {
        next({ name: 'Login' });
        localStorage.removeItem('jwt')
    } else if (to.meta.requiresAdmin && !isAdmin()) {
        next({name: 'Login'});
        localStorage.removeItem('jwt')
    }
    else if (to.meta.requiresActif && !isActif()) {
        alert("tu es ban !!")
            next({ name: 'Login' });
            localStorage.removeItem('jwt')
    } else {
        next();
    }
});

export default router;
