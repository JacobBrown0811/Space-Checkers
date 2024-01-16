package org.wcci.checkers;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.service.GameService;
import org.wcci.checkers.service.PieceService;


@SpringBootApplication
public class CheckersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckersApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BoardRepository boardRepository, PieceRepository pieceRepository, PieceService pieceService) {
        return args -> {
            GameService game = new GameService(boardRepository, pieceRepository, pieceService);
            game.startGame();
            
        };
    }
   
}
