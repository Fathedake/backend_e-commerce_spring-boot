/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.repository;

import com.example.ecommerce.models.ImageC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<ImageC, Long> {

}