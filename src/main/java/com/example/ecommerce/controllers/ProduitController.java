/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.controllers;

import com.example.ecommerce.exceptions.CategorieNotFoundException;
import com.example.ecommerce.exceptions.ProduitNotFoundException;
import com.example.ecommerce.models.Categorie;
import com.example.ecommerce.models.Produit;
import com.example.ecommerce.models.ImageC;
import com.example.ecommerce.repository.CategorieRepository;
import com.example.ecommerce.repository.FileRepository;
import com.example.ecommerce.repository.ProduitRepository;
import com.example.ecommerce.security.services.FileService;
import java.awt.print.Pageable;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/prods")
@CrossOrigin
public class ProduitController {
 
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private FileRepository fileRepository;
    
    @Autowired
    FileService fileService;
    
  @GetMapping("/produits")
  Page<Produit> all( @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "4") int size,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
         @RequestParam(name = "sortOrder", defaultValue = "normal") String sortOrder) {
      
     // Sort objectSort=  sortOrder=="asc" ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
      Sort objectSort;
     
              
       if(sortOrder.equals("ascend")){
        System.out.println("ascend");    
       objectSort=Sort.by(sortBy).ascending();   
      }else if(sortOrder.equals("descend"))
      {
            System.out.println("desc");    
       objectSort=Sort.by(sortBy).descending();
      }else {
            System.out.println("normal");    
        objectSort=Sort.by("id").ascending(); 
      }
      PageRequest sorted = 
      // PageRequest.of(page, size, Sort.by("id"));
              PageRequest.of(page,size, objectSort);
     // PageRequest firstPageWithTwoElements = PageRequest.of(1,4,objectSort);
    return produitRepository.findAll(sorted);
  }
  
   @GetMapping("/produitsByCat")
  Page<Produit> allByCat( @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "4") int size,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
         @RequestParam(name = "sortOrder", defaultValue = "normal") String sortOrder,
        @RequestParam(name = "catId") String catId) {
      
     // Sort objectSort=  sortOrder=="asc" ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
      Sort objectSort;
     
              
       if(sortOrder.equals("ascend")){
        System.out.println("ascend");    
       objectSort=Sort.by(sortBy).ascending();   
      }else if(sortOrder.equals("descend"))
      {
            System.out.println("desc");    
       objectSort=Sort.by(sortBy).descending();
      }else {
            System.out.println("normal");    
        objectSort=Sort.by("id").ascending(); 
      }
      PageRequest sorted = 
      // PageRequest.of(page, size, Sort.by("id"));
              PageRequest.of(page,size, objectSort);
     // PageRequest firstPageWithTwoElements = PageRequest.of(1,4,objectSort);
    return produitRepository.findAll(sorted);
  }
  
  /*@GetMapping("/categories2")
  public ResponseEntity<List<Categorie>> getAllCategories(@RequestParam(required = false) String title) {
    List<Categorie> categories = new ArrayList<Categorie>();
       categorieRepository.findAll().forEach(categories::add);
    if (categories.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }*/

  @PostMapping(value = "/produit")
 //Produit newProduit(@RequestParam("image") ImageC img,@RequestParam("produitInfos") Produit produit,@RequestParam("categorieId") Long categorieId) {
   Produit newProduit(@RequestBody Produit produit) {
         /* Categorie categorie=categorieRepository.findById(produit.getCategorie().getId())
      .orElseThrow(() -> new CategorieNotFoundException(produit.getCategorie().getId()));*/
     
   //  produit.setCategorie(categorie);
  
    produitRepository.save(produit);
    List<ImageC> images=produit.getImages();
     for (ImageC img : images) {
         img.setProduit(produit);
           fileRepository.save(img);
        }
    // fileRepository.save(img);
   
    return produit;
  }

  // Single item
  
  @GetMapping("/produits/{id}")
  Produit one(@PathVariable Long id) {
    
    return produitRepository.findById(id)
      .orElseThrow(() -> new ProduitNotFoundException(id));
  }

  @RequestMapping(value = "/produits/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
 Produit replaceProduit(@RequestBody Produit newProduit, @PathVariable Long id) {
    
    return produitRepository.findById(id)
      .map(produit -> {
        produit.setNom(newProduit.getNom());
         produit.setDescription(newProduit.getDescription());
        //produit.setImage(newProduit.getImage());
        produit.setPrix(newProduit.getPrix());
        produit.setQuantite(newProduit.getQuantite());
        return produitRepository.save(produit);
      })
      .orElseGet(() -> {
        newProduit.setId(id);
        return produitRepository.save(newProduit);
      });
  }

  @DeleteMapping("/produits/{id}")
  void deleteProduit(@PathVariable Long id) {
    produitRepository.deleteById(id);
  }
}
