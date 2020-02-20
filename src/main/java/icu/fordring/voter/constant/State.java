package icu.fordring.voter.constant;



public enum State {
    UNAUTHORISED("未授权"), AUTHORISED("已授权"),VOTED("已投票"),ROOT("管理员")
    ;
    private String describe;
    private State(String describe){ this.describe=describe; }
    @Override
    public String toString() {return describe;}
}
