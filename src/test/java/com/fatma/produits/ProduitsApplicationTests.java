package com.fatma.produits;

import java.util.Date;
import java.util.List;

import com.fatma.produits.entities.Categorie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fatma.produits.entities.Produit;
import com.fatma.produits.repos.ProduitRepository;
@SpringBootTest
class ProduitsApplicationTests {
    @Autowired
    private ProduitRepository produitRepository;
    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("S22 Ultra",2200.500,new Date());
        produitRepository.save(prod);
    }
    @Test
    public void testCreateProduit1() {
        Produit prod = new Produit("PC HP",3500.000,new Date());
        produitRepository.save(prod);
    }
    @Test
    public void testFindProduit()
    {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }
    @Test
    public void testUpdateProduit()
    {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(1000.0);
        produitRepository.save(p);
    }
    @Test
    public void testDeleteProduit()
    {
        produitRepository.deleteById(1L);;
    }

    @Test
    public void testListerTousProduits()
    {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }
    @Test
    public void testFindByNomProduit()
    {
        List<Produit> prods = produitRepository.findByNomProduit("iphone X");
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }
    @Test
    public void testFindByNomProduitContains ()
    {
        List<Produit> prods=produitRepository.findByNomProduitContains("iphone");
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByNomPrix()
    {
        List<Produit> prods = produitRepository.findByNomPrix("iphone 13", 1000.0);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorie()
    {
        Categorie cat = new Categorie();
        cat.setIdCat(3L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void findByCategorieIdCat()
    {
        List<Produit> prods = produitRepository.findByCategorieIdCat(2L);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

}
