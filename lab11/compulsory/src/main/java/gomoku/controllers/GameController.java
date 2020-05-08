package gomoku.controllers;

import gomoku.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @GetMapping("/players")
    public List<Player> players() {
        return Player.getPlayers();
    }

    @PostMapping("/players")
    public boolean addPlayer(@RequestBody Player player) {
        return Player.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public boolean changePlayerName(@RequestBody Player player, @PathVariable int id) {
        return Player.changePlayerName(player, id);
    }

    @DeleteMapping("/players/{id}")
    public boolean deletePlayer(@PathVariable int id) {
        return Player.deletePlayer(id);
    }

}
