package com.pla.Manager;
import com.pla.Utils.Result;
import java.util.Map;

public class ResultUtils {
    public static Result ok(String key, Object value) {
        return new Result(key, value);
    }

    public static Result fail() {
        return new Result("");
    }

    public static Result okMessage(String key, Object value, String message) {
        return new Result(key, value, message);
    }

    public static Result ok(Map<String, Object> data){
        return new Result(data);
    }

    public static Result okMsg(Map<String, Object> data, String msg){
        return new Result(data, msg);
    }

}
