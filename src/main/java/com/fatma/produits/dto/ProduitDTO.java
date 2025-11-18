package com.fatma.produits.dto;


import com.fatma.produits.entities.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProduitDTO {
    private Long idProduit;
    private String nomProduit;
    private Double prixProduit;
    private Date dateCreation;
    private Categorie categorie;

    private String nomCat;

}
