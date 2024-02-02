package org.wcci.checkers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.wcci.checkers.service.AIService;
import org.wcci.checkers.service.GameService;
import org.wcci.checkers.service.PieceService;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;

@SpringBootApplication
public class CheckersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckersApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BoardRepository boardRepository, PieceRepository pieceRepository, PieceService pieceService, AIService aiService) {
        return args -> {
            // Initialize GameService with all required dependencies
            GameService game = new GameService(boardRepository, pieceRepository, pieceService, aiService);
            game.startGame(); // Starts a new game

            // Optionally, you can also trigger AI move here or elsewhere based on your game flow
            // Long boardId = ...; // You would get the board ID from your game context
            // game.makeAIMove(boardId);
        };
    }
}