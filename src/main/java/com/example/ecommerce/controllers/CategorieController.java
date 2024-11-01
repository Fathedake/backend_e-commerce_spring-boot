/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.controllers;

import com.example.ecommerce.exceptions.CategorieNotFoundException;
import com.example.ecommerce.models.Categorie;
import com.example.ecommerce.models.Produit;
import com.example.ecommerce.repository.CategorieRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/cats")
@CrossOrigin
class CategorieController {
  @Autowired
    private CategorieRepository categorieRepository;

  @GetMapping("/categories")
  List<Categorie> all() {
    return categorieRepository.findAll();
  }
  
  @GetMapping("/categories2")
  public ResponseEntity<List<Categorie>> getAllCategories(@RequestParam(required = false) String title) {
    List<Categorie> categories = new ArrayList<Categorie>();
       categorieRepository.findAll().forEach(categories::add);
    if (categories.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }
    @GetMapping("/categoriesPage")
  Page<Categorie> all( @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "4") int size,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
         @RequestParam(name = "sortOrder", defaultValue = "normal") String sortOrder) {
      
     // Sort objectSort=  sortOrder=="asc" ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
      Sort objectSort;
     
              
       if(sortOrder.equals("ascend")){
        //System.out.println("ascend");    
       objectSort=Sort.by(sortBy).ascending();   
      }else if(sortOrder.equals("descend"))
      {
          //  System.out.println("desc");    
       objectSort=Sort.by(sortBy).descending();
      }else {
           // System.out.println("normal");    
        objectSort=Sort.by("id").ascending(); 
      }
      PageRequest sorted = 
      // PageRequest.of(page, size, Sort.by("id"));
              PageRequest.of(page,size, objectSort);
     // PageRequest firstPageWithTwoElements = PageRequest.of(1,4,objectSort);
    return categorieRepository.findAll(sorted);
  }

  @PostMapping("/categorie")
 Categorie newCategorie(@RequestBody Categorie newCategorie) {
    return categorieRepository.save(newCategorie);
  }

  // Single item
  
  @GetMapping("/categories/{id}")
  Categorie one(@PathVariable Long id) {
    
    return categorieRepository.findById(id)
      .orElseThrow(() -> new CategorieNotFoundException(id));
  }
  //@PutMapping("/categories/{id}")
  @RequestMapping(value = "/categories/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
 Categorie replaceCategorie(@RequestBody Categorie newCategorie, @PathVariable Long id) {
    System.out.println(id);
    return categorieRepository.findById(id)
      .map(categorie -> {
        categorie.setNom(newCategorie.getNom());
        return categorieRepository.save(categorie);
      })
      .orElseGet(() -> {
        newCategorie.setId(id);
        return categorieRepository.save(newCategorie);
      });
  }

  @DeleteMapping("/categories/{id}")
  void deleteCategorie(@PathVariable Long id) {
      System.out.println(id);
    categorieRepository.deleteById(id);
  }
}
