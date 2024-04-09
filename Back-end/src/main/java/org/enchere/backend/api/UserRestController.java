package org.enchere.backend.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.enchere.backend.api.doc.SwaggerDoc;
import org.enchere.backend.model.User;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API UTILISATEURS", description = SwaggerDoc.DESC_GENERIQUE + "utilisateurs")
@RestController// obligatoire afin que le controller soit dans le contexte Spring
@CrossOrigin
@RequestMapping("/api/utilisateurs") // tous les @Getmapping/@Postmapping de mon controller auront le chemin de base /films
public class UserRestController {
    @Autowired
    UserService userService;

    @PostMapping
    public void postUser(@RequestBody User user) {
        userService.createUser(user);
    }

}
