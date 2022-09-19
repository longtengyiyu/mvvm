package com.tangtang.mvvm.json;

public class JsonUtils {
    public static JsonSerializer get(){
        return get(JsonSerializerHolder.Serializer.GSON);
    }

    public static JsonSerializer get(JsonSerializerHolder.Serializer serializerType){
        JsonSerializer serializer = JsonSerializerHolder.get(serializerType.val);

        if(serializer == null){
            throw new RuntimeException("暂未实现你需要的JSON序列化器");
        }

        return serializer;
    }
}
