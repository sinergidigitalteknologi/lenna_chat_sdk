package ai.lenna.lennachatmodul.regist.model;

import androidx.annotation.Keep;

@Keep
public class ErrorDataBean {
    private int code;
    private String message;

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
