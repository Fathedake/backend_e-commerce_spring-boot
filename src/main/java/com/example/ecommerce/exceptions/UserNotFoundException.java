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
 public class UserNotFoundException extends RuntimeException{
   
    public UserNotFoundException(Long id){
      super("Utilisateur non trouvée" + id);
    }
}