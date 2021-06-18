package gameholic.server.services;

import jsc.jEventManager.JEventCode;

import java.util.HashMap;

public enum GameControlEvent implements JEventCode {

    CREATE_GSESSION(1),
    JOIN_GSESSION(2),
    END_GSESSION(3),
    START_GAME(4),
    PASS_DATA(5),
    PLAYER_DISCONNECTED(6),
    GAME_OVER(7),
    INVALID_SESSION_ID(8),
    RESTART(9),
    ;

    private final int GAME_EVENT_CODE;
    private final static HashMap<Integer, GameControlEvent> gameEventMap = new HashMap<>();

    GameControlEvent(int gecode){
        this.GAME_EVENT_CODE = gecode;
    }

    static{
        for(GameControlEvent gameControlEvent : GameControlEvent.values()){
            gameEventMap.put(gameControlEvent.getCode(), gameControlEvent);
        }
    }

    @Override
    public int getCode() {
        return GAME_EVENT_CODE;
    }

    public static GameControlEvent get(int i){
        return gameEventMap.get(i);
    }
}
