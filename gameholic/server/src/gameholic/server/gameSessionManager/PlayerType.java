package gameholic.server.gameSessionManager;

public enum PlayerType{

    CREATOR(0),
    OPPONENT(1)
    ;

    private final int PLAYER_CODE;

    PlayerType(int playerCode){
        this.PLAYER_CODE = playerCode;
    }

    public int getCode(){
        return PLAYER_CODE;
    }
}
