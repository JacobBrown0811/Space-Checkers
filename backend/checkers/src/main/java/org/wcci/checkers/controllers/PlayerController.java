// package org.wcci.checkers.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import org.wcci.checkers.models.PlayerModel;
// import org.wcci.checkers.repositories.PlayerRepository;

// @RestController
// @RequestMapping("/players")
// public class PlayerController {

//     @Autowired
//     private PlayerRepository playerRepository;

//     @PostMapping
//     public PlayerModel createPlayer(@RequestBody PlayerModel player) {
//         return playerRepository.save(player);
//     }

//     @GetMapping("/{id}")
//     public PlayerModel getPlayer(@PathVariable long id) {
//         return playerRepository.findById(id).orElse(null);
//     }


// }