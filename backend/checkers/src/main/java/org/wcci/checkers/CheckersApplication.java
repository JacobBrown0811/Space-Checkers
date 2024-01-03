package org.wcci.checkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wcci.checkers.models.BoardModel;

@SpringBootApplication
public class CheckersApplication {

	public static void main(String[] args) {

		BoardModel board = new BoardModel();
		board.drawBoard();

		SpringApplication.run(CheckersApplication.class, args);



	}

}
