import axios from "axios";

/**
 * Instance axios
 * Je vais specifier sur cette instance :
 * - une url de base à utiliser pour toutes les routes de mon application
 * => axios.post('url') va envoyer une requête POST sur http://localhost:8080/api/url
 *
 * - un intercepteur de requête (interceptors.request)
 * = code qui va être executé en amont de chaque requête
 * => c'est là qu'on va inclure le token JWT recuperé depuis le localstorage
 */
const axiosInstance = axios.create({ baseURL: 'http://localhost:8080/api/' });
// axios utilise l'API de prod
// const axiosInstance = axios.create({ baseURL: 'https://filmotheque.osc-fr1.scalingo.io/api/' });

/**
 * Je précise qu'à chaque requête HTTP je veux ajouter le token JWT qui est présent dans le localstorage
 */
axiosInstance.interceptors.request.use(
    request => {
        // je vais récupérer depuis le localstorage le token JWT
        const jwt = localStorage.getItem("jwt");

        // si jamais j'ai un token jwt en localstorage
        if (jwt != null) {
            // je met à jour ma requête HTTP en ajoutant l'entete 'Authorization' = Bearer XXXXXX_TOKEN_JWT_XXXXXX
            request.headers['Authorization'] = `Bearer ${jwt}`
        }
        return request;
    }
);


// on va pouvoir faire un import axios from '@/axios/instance'
// dans chaque fichier .vue pour recupérer l'instance créée dans ce fichier
// ca va réer une variable axios qui aura pour valeur l'instance créée dans ce fichier
export default axiosInstance