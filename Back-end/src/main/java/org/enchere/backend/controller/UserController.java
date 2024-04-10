package org.enchere.backend.controller;

import org.enchere.backend.Repository.UserRepository;
import org.enchere.backend.model.User;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {

    @Autowired
    private UserRepository utilisateurRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("listeUtilisateurs")
    public List<User> getListeMembres(){
        return userService.consulterUser();
    }

    @GetMapping
    public String getFormulaireUser(@RequestParam(required=false) Long edit, Model model) {

        // afin d'utiliser le formulaire avec th:object, on crée dans le modèle un attribut "personne"
        model.addAttribute("utilisateur", new User());


        // si jamais on a un paramètre "edit" dans la requête HTTP (avce un id de personne), on va mettre en modèle la personne qu'on souhaite modifier
        if (edit != null){
            model.addAttribute("personneEdit", userService.consulterUserParId(edit));
        }


        /*
         * Comme on va utiliser le template personne.html qui est commun avec la gestiond es mmebres
         * Je vais devoir mettre quelques attributs pour définir le titre / la route du formulaire etc...
         */
        model.addAttribute("titre", "Gestion des utilisateurs");
        model.addAttribute("titreSectionTable", "Liste des utilisateurs");
        model.addAttribute("action", "/utilisateurs");

        return "";
    }



    @PostMapping()
    public User ajouterUtilisateur(@RequestBody User utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @GetMapping("/{idUser}/supprimer")
    public String supprimerUser(@PathVariable Long idUser, Model model) {

        User user = userService.consulterUserParId(idUser);

        model.addAttribute("message", "Êtes vous sur de vouloir supprimer l'utilisateur : " + user);
        model.addAttribute("action", "/utilisateurs/" + idUser + "/supprimer");
        model.addAttribute("back", "/utilisateurs");


        // 2 - on redirige sur la page qui affiche le formulaire + la liste des membres
        return "confirmation";
    }

    /**
     * Est appelé lorsqu'on clique sur un bouton de suppression de la page des membres
     * Je vais recupérer un id en paramètre d'url
     */
    @PostMapping("/{idUser}/supprimer")
    public String supprimerUser(@PathVariable Long idUser) {

        // 2.1 - on supprime le membre
        userService.supprimerUserParId(idUser);

        // 2 - on redirige sur la page qui affiche le formulaire + la liste des membres
        return "redirect:/utilisateurs";
    }

    @PostMapping("/{idUser}/modifier")
    public User modifierUtilisateur(@PathVariable Long idUser, @RequestBody User utilisateurModifie) throws NotFoundException {
        // Récupérer l'utilisateur existant par son ID
        User utilisateurExistant = userService.consulterUserParId(idUser);

        // Mettre à jour les champs de l'utilisateur avec les valeurs de l'utilisateur modifié
        utilisateurExistant.setPseudo(utilisateurModifie.getPseudo());
        utilisateurExistant.setPrenom(utilisateurModifie.getPrenom());
        utilisateurExistant.setNom(utilisateurModifie.getNom());
        utilisateurExistant.setMot_de_passe(utilisateurModifie.getMot_de_passe());
        utilisateurExistant.setEmail(utilisateurModifie.getEmail());
        utilisateurExistant.setCode_postal(utilisateurModifie.getCode_postal());
        utilisateurExistant.setVille(utilisateurModifie.getVille());
        utilisateurExistant.setRue(utilisateurModifie.getRue());

        // Enregistrez les modifications dans la base de données via le service
        return userService.modifierUser(utilisateurExistant);
    }

}