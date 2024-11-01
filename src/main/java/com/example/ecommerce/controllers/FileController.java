/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Categorie;
import com.example.ecommerce.models.ImageC;
import com.example.ecommerce.models.Produit;
import com.example.ecommerce.repository.CategorieRepository;
import com.example.ecommerce.repository.FileRepository;
import com.example.ecommerce.security.services.FileService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
 @RequestMapping("/api/files")
@CrossOrigin
public class FileController {
      
       @Autowired
    private FileRepository fileRepository;
   
       
   @GetMapping("/all")
  List<ImageC> all() {
    return fileRepository.findAll();
  }
//@PostMapping(name="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @PostMapping(value= "/images"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public ImageC uploadFile(@RequestParam MultipartFile file) throws IOException {
      //  logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));src/main/resources/
     // System.out.print("file : "+file);
      // logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
    //  fileService.moveFile(file.getOriginalFilename(),"src/main/resources/Images");
     // byte[] bytes = file.getBytes();
      //Path path = Paths.get("Images/" + file.getOriginalFilename());
      //Files.write(path, bytes);
      byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/Images/" + file.getOriginalFilename());
            Files.write(path, bytes);
     ImageC image=new ImageC(file.getName(),file.getContentType(),file.getContentType());
     //image.setNom(file.getName());
     //image.setType(file.getContentType());
     //image.setUrl("Images/"+file.getName()+'.'+file.getContentType());
     
     System.out.println("file");
     image.setUrl("Images/"+file.getName());
     
        return image;
    }
}