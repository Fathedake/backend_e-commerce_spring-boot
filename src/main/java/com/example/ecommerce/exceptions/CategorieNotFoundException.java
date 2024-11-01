/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.exceptions;

/**
 *
 * @author hp
 */
public class CategorieNotFoundException extends RuntimeException{
   
    public CategorieNotFoundException(Long id){
      super("Catégorie non trouvée" + id);
    }
}
