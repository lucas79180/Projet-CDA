package org.enchere.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ArticleRetrait {
    private ArticleVendu article;
    private Retrait retrait;

}
