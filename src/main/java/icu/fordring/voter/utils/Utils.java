package icu.fordring.voter.utils;

import icu.fordring.voter.constant.State;

import java.util.Random;

public class Utils {
    public static String createRandomString(int length){
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<length;i++){
                int number=random.nextInt(62);
                sb.append(str.charAt(number));
            }
            return sb.toString();
    }
    public static State string2State(String state){
        State s = State.UNAUTHORISED;
        if(state.equals("ROOT")){
            s = State.ROOT;
        }else if(state.equals("AUTHORISED")){
            s = State.UNAUTHORISED;
        }else if(state.equals("VOTED")){
            s = State.VOTED;
        }else if(state.equals("UNAUTHORISED")){
            s = State.UNAUTHORISED;
        }
        return s;
    }
}
