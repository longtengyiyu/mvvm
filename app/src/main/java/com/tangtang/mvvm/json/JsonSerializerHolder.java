package com.tangtang.mvvm.json;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonSerializerHolder {
    private static final JsonSerializerHolder holder = new JsonSerializerHolder();
    public Map<Integer, JsonSerializer> jsonSerializerMap = new ConcurrentHashMap<>();

    private JsonSerializerHolder() {
        jsonSerializerMap.put(Serializer.GSON.val, new GsonSerializer());
        //在此添加新的序列化器
    }

    public static JsonSerializer get(int type){
        return holder.jsonSerializerMap.get(type);
    }

    public enum Serializer {
        /**
         * gson
         */
        GSON(1),
        /**
         * 高性能json序列化
         */
        FastJson(2)
        ;

        Serializer(int val) {
            this.val = val;
        }

        public Integer val;
    }
}
