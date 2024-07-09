package uz.pdp.quiz_first_app.projection;

import java.util.UUID;

public interface PlayerScoreProjection {

    UUID getPlayerId();

    String getPlayerName();

    Long getScore();

    Integer getRanking();

}
