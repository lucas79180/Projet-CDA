package org.enchere.backend.converter;

import org.enchere.backend.model.Categorie;
import org.enchere.backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component // Ne pas oublier de mettre @Component afin que le converter soit dans le contexte Spring
public class CategorieConverter implements Converter<String, Categorie> {

    @Autowired
    private CategorieService categorieService;

    @Override
    public Categorie convert(String idFormatTexte) {
        System.out.println("--LOG-- exec Categorie convert("+ idFormatTexte +")");
        int idFormatInteger = Integer.parseInt(idFormatTexte);
        System.out.println(categorieService.getCategoryById(idFormatInteger));
        return categorieService.getCategoryById(idFormatInteger);
    }
}
