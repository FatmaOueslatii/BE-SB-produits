package com.fatma.produits.service;

import com.fatma.produits.dto.ProduitDTO;
import com.fatma.produits.entities.Categorie;
import com.fatma.produits.entities.Produit;
import com.fatma.produits.repos.ImageRepository;
import com.fatma.produits.repos.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ImageRepository imageRepository ;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProduitDTO saveProduit(ProduitDTO p) {
//        return convertEntityToDto(produitRepository.save(p)) ;
        return convertEntityToDto( produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public ProduitDTO updateProduit(ProduitDTO p) {

//        Long oldProdImageId =
//        this.getProduit(p.getIdProduit()).getImage().getIdImage();
//        Long newProdImageId = p.getImage().getIdImage();
//        Produit prodUpdated = produitRepository.save(p);
//        if (oldProdImageId != newProdImageId) //si l'image a été modifiée
//        imageRepository.deleteById(oldProdImageId);
//        return prodUpdated;

        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public void deleteProduit(Produit p) {
         produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
//        Produit p = getProduit(id);
//        //suuprimer l'image avant de supprimer le produit
//        try {
//            Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDTO getProduit(Long id) {

        return convertEntityToDto(produitRepository.findById(id).get());
    }

    @Override
    public List<ProduitDTO> getAllProduits() {

        return produitRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

//        List<Produit> prods = produitRepository.findAll();
//        List<ProduitDTO> listprodDTO = new ArrayList<>(prods.size());
//        for (Produit p : prods)
//            listprodDTO.add(convertEntityToDto(p));
//        return listprodDTO;
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

//    @Override
//    public ProduitDTO convertEntityToDto(Produit produit) {
////        ProduitDTO produitDTO = new ProduitDTO();
////        produitDTO.setIdProduit(produit.getIdProduit());
////        produitDTO.setNomProduit(produit.getNomProduit());
////        produitDTO.setPrixProduit(produit.getPrixProduit());
////        produitDTO.setDateCreation(produit.getDateCreation());
////        produitDTO.setCategorie(produit.getCategorie());
////        return produitDTO;
//        return ProduitDTO.builder()
//            .idProduit(produit.getIdProduit())
//            .nomProduit(produit.getNomProduit())
//            .prixProduit(produit.getPrixProduit())
//            .dateCreation(produit.getDateCreation())
//            .categorie(produit.getCategorie())
//            .build();
//    }

    @Override
    public ProduitDTO convertEntityToDto(Produit produit) {
        //la ligne suivante est pour faire la recherche des attribut de DTO vers l'entité plus profondément : chercher même dans les entités des relation
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
        return produitDTO;
    }


    @Override
    public Produit convertDtoToEntity(ProduitDTO produitDTO) {

        Produit produit = new Produit();
        produit =modelMapper.map(produitDTO, Produit.class);
        return produit;


//       Produit produit = new Produit();
//       produit.setIdProduit(produitDTO.getIdProduit());
//       produit.setNomProduit(produitDTO.getNomProduit());
//       produit.setPrixProduit(produitDTO.getPrixProduit());
//       produit.setDateCreation(produitDTO.getDateCreation());
//       produit.setCategorie(produitDTO.getCategorie());
//        return produit;
    }
}
