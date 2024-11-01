/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.seeder;

import com.example.ecommerce.models.ImageC;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repository.FileRepository;
import com.example.ecommerce.repository.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

   /* public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public void run(String... args) throws Exception {
        // Create some users and save them to the database
        //User user1 = new User("seyd","seyd@gmail.com","123456789");
        // User user2 = new User("seyde","seyde@gmail.com","123456789");
       /* ImageC img1 = new ImageC("Huiles Soin Vergetures Prévention ", "https://shop.cellublue.com/media/cpcs/512x640/dc14357dfde01baa4432809fdbccb177/0/1/01huilevergetures-packshotlot_2.webp", "webp");
        ImageC img2 = new ImageC("Huile Soin Vergetures Prévention ", "https://shop.cellublue.com/media/cpcs/512x640/bbf564569ad5a422473306cbc9ab2a92/0/1/01huilevergetures-packshot.webp", "webp");
        ImageC img3 = new ImageC("Crème Vergetures Réparatrice", "https://shop.cellublue.com/media/cpcs/512x640/7a0f8356b0713dd2ef56dd7dabb9ad37/0/1/01cremevergetures.webp", "webp");
        ImageC img4 = new ImageC("Sérum Vergetures  ", "https://shop.cellublue.com/media/cpcs/512x640/b15cdb9c154e3a18337fdc90a0a49d1a/0/1/01serumvergetures-packshot.webp", "webp");
        ImageC img5 = new ImageC("Gommages Vergetures ", "https://shop.cellublue.com/media/cpcs/512x640/2ea1136ba7bfd2c7be18081717a34a63/0/1/01gommagevergetures-packshot_2_2.webp", "webp");
        ImageC img6 = new ImageC("Huiles massage cellulite ", "https://shop.cellublue.com/media/cpcs/520x650/163edfd822e18c9863e6c16fc94daf22/0/1/01huilecellulite-packshot_1_5.webp", "webp");
        ImageC img7 = new ImageC("Crème vergétures réparatrice ", "https://shop.cellublue.com/media/cpcs/520x650/0d124b541a4e24efe4a77fed48903209/0/1/01brumeactivante.webp", "webp");
        ImageC img8 = new ImageC("Soin activateur palper rouler", "https://shop.cellublue.com/media/cpcs/520x650/9eede1503d15bdd1774a44d7dcabf5d7/0/1/01soinactivateurpalperrouler_packshot.webp", "webp");
        ImageC img9 = new ImageC("Brume activatrice minceur", "https://shop.cellublue.com/media/cpcs/520x650/0d124b541a4e24efe4a77fed48903209/0/1/01brumeactivante.webp", "webp");
        ImageC img10 = new ImageC("Cure Perte de Poids 30J", "https://shop.cellublue.com/media/cpcs/512x640/2e571a3b68364ae416113b63f7e164a7/0/2/02challengepertedepoids_6_2_.webp", "webp");
        
        
         ImageC img11 = new ImageC("Gommages Cellulite ", "https://shop.cellublue.com/media/cpcs/512x640/1333128956a0b967e2e3b02d27daeccf/0/1/01gommagecellulite-packshotlot_3.webp", "webp");
        ImageC img12 = new ImageC("Gommage Cellulite", "https://shop.cellublue.com/media/cpcs/512x640/c08ff922929d84e51d95d1b96eb1c3d6/0/1/01gommagecellulite-packshot.webp", "webp");
        ImageC img13 = new ImageC("Gels Douche Cellulite  ", "https://shop.cellublue.com/media/cpcs/512x640/3330bd6895feb068314a72ffe9923695/0/1/01geldouche-packshotlot_2.webp", "webp");
        ImageC img14 = new ImageC("Draineur Cellulite Rétention d'Eau", "https://shop.cellublue.com/media/cpcs/512x640/8fb71a2efdfea0f8c73c4afd6d5e66fb/0/0/001draineurcellulitejour-packshot.webp", "webp");
        ImageC img15 = new ImageC("Gélules Cellulite", "https://shop.cellublue.com/media/cpcs/512x640/5528961b4d55951296e8b527926f9f0f/g/e/ge_lulescellulite.webp", "webp");
        ImageC img16 = new ImageC("Crème Cellulite Cryo", "https://shop.cellublue.com/media/cpcs/512x640/16fffb0508d333c9d138a07a49914aa1/0/1/01cremecellulitecryo-packshot.webp", "webp");
        ImageC img17 = new ImageC("Soin Activateur Palper-Rouler", "https://shop.cellublue.com/media/cpcs/512x640/9eede1503d15bdd1774a44d7dcabf5d7/0/1/01soinactivateurpalperrouler_packshot.webp", "webp");
        ImageC img18 = new ImageC("Masseur Peau d'Orange", "https://shop.cellublue.com/media/cpcs/512x640/0bd5eb4b4e654a8f666d901056a73e28/m/a/masseurpeauorangemain_1.webp", "webp");   
        
        fileRepository.saveAll(Arrays.asList(img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18));*/
    }
}
