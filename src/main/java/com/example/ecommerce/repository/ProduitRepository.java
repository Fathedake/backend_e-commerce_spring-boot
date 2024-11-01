/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.repository;

import com.example.ecommerce.models.Produit;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hp
 */
public interface ProduitRepository extends PagingAndSortingRepository<Produit, Long> {

}