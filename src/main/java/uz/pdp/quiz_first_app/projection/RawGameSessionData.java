package uz.pdp.quiz_first_app.projection;

import java.util.UUID;

public interface RawGameSessionData {

    String getPlayer1Username();
    String getPlayer2Username();
    Integer getPlayer1Score();
    Integer getPlayer2Score();
    UUID getPlayer1Id();
    UUID getPlayer2Id();

}
