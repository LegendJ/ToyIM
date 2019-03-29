package com.just.toyim.pojo;

import org.springframework.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;

public class HttpResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS_STATUS = 200;
    private static final Integer ERROR_STATUS = -1;
    private static final String SUCCESS_MSG = "success";

    public HttpResponse() {
        super();
    }

    public HttpResponse(int code) {
        super();
        setStatus(code);
    }

    public HttpResponse(HttpStatus status) {
        super();
        setStatus(status.value());
        setMsg(status.getReasonPhrase());
    }

    public HttpResponse success() {
        put("msg", SUCCESS_MSG);
        put("status", SUCCESS_STATUS);
        return this;
    }

    public HttpResponse success(String msg) {
        put("msg", msg);
        put("status", SUCCESS_STATUS);
        return this;
    }

    public HttpResponse error(String msg) {
        put("msg", msg);
        put("status", ERROR_STATUS);
        return this;
    }

    public HttpResponse setData(String key, Object obj) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> data = (HashMap<String, Object>) get("data");
        if (data == null) {
            data = new HashMap<String, Object>();
            put("data", data);
        }
        data.put(key, obj);
        return this;
    }

    public HttpResponse setStatus(int status) {
        put("status", status);
        return this;
    }

    public int getStatus(){
        return (int)get("status");
    }

    public HttpResponse setMsg(String msg) {
        put("msg", msg);
        return this;
    }

    public HttpResponse setValue(String key, Object val) {
        put(key, val);
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
