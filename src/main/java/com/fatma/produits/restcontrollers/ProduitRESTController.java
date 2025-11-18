package com.fatma.produits.restcontrollers;


import com.fatma.produits.dto.ProduitDTO;
import com.fatma.produits.entities.Produit;
import com.fatma.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProduitRESTController {

    @Autowired
    private ProduitService produitService;

//    @GetMapping("/{id}")
    @RequestMapping(value="/getbyid/{id}",method = RequestMethod.GET)
    public ProduitDTO getProduitById(@PathVariable("id") Long id) {
        return produitService.getProduit(id);
    }

    // Méthode pour obtenir tous les produits
    @GetMapping(path = "all")
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @RequestMapping(path="/addprod",method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ProduitDTO createProduit(@RequestBody Produit produitDTO) {
//        return produitService.saveProduit(produitDTO);
//    }
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.saveProduit(produitDTO);
    }

    @RequestMapping(path="/updateprod",method = RequestMethod.PUT)
//    public Produit updateProduit(@RequestBody Produit produit) {
//        return produitService.updateProduit(produit);
//    }
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.updateProduit(produitDTO);
    }


    @RequestMapping(value="/delprod/{id}",method = RequestMethod.DELETE)
    public void deleteProduit(@PathVariable("id") Long id)
    {
        produitService.deleteProduitById(id);
    }

    @RequestMapping(value="/prodscat/{idCat}",method = RequestMethod.GET)
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }

    @RequestMapping(value="/prodsByName/{nom}",method = RequestMethod.GET)
    public List<Produit> findByNomProduitContains(@PathVariable("nom") String nom) {
        return produitService.findByNomProduitContains(nom);
    }



}