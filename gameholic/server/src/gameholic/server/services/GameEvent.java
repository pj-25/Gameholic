package gameholic.server.services;

import jsc.jMessageHandler.JMessageCode;

import java.util.HashMap;

public enum GameEvent implements JMessageCode {

    CREATE_GSESSION(1),
    JOIN_GSESSION(2),
    END_GSESSION(3),
    START_GAME(4),
    PASS_DATA(5),
    PLAYER_DISCONNECTED(6),
    GAME_OVER(7),
    INVALID_SESSION_ID(8)
    ;

    private final int GAME_EVENT_CODE;
    private final static HashMap<Integer, GameEvent> gameEventMap = new HashMap<>();

    GameEvent(int gecode){
        this.GAME_EVENT_CODE = gecode;
    }

    static{
        for(GameEvent gameEvent:GameEvent.values()){
            gameEventMap.put(gameEvent.getCode(), gameEvent);
        }
    }

    @Override
    public int getCode() {
        return GAME_EVENT_CODE;
    }

    public static GameEvent get(int i){
        return gameEventMap.get(i);
    }
}
