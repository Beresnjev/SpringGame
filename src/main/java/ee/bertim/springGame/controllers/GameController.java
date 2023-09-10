package ee.bertim.springGame.controllers;

import ee.bertim.springGame.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {
    private final List<Game> gamesList = new ArrayList<>();

    @PostMapping
    public int createNewGame() {
        Game newGame = new Game();
        gamesList.add(newGame);

        return newGame.getIndex();
    }

    @GetMapping("/{gameId}/guess/{number}")
    public String guessGameNumber(@PathVariable int gameId, @PathVariable int number) {
        Optional<Game> foundGame = gamesList.stream()
                .filter((game) -> game.getIndex() == gameId)
                .findFirst();

        if (foundGame.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game was not found");
        }

        Game game = foundGame.get();
        if (game.isOver()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game is already over");
        }

        boolean numberIsGuessed = game.guess(number);
        if (numberIsGuessed) {
            int attemptsNumber = game.getAttemptsAmount();
            return "Correct, it took you " + attemptsNumber + " times";
        }

        int numberToGuess = game.getNumberToGuess();
        if (numberToGuess < number) {
            return "Nr is smaller";

        } else {
            return "Nr is bigger";
        }
    }
}
