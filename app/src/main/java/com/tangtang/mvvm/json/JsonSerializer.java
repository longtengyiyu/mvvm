package com.tangtang.mvvm.json;

import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public abstract class JsonSerializer {
    public abstract String toJson(Object obj);

    public abstract <T> T fromJson(String json, Class<T> clz);

    public abstract <T> T fromJson(String json, Type type);


    public abstract <T> List<T> fromJsonList(String json, Class<T> clz);

    public abstract <T> T fromFileJson(String filePath, Class<T> clz);

    public abstract <T> T fromFileJson(FileReader fileReader, Class<T> clz);

    public abstract <T> T fromFileJson(InputStream inputStream, Class<T> clz);


    public abstract <T> List<T> fromFileJsonList(String filePath, Class<T> clz);

    public abstract <T> List<T> fromFileJsonList(FileReader fileReader, Class<T> clz);

    public abstract <T> List<T> fromFileJsonList(InputStream inputStream, Class<List<T>> clz);


    public abstract <T> T copy(T obj);

    public abstract <T> T copy(Object obj, Type proto);

    public abstract <T> List<T> copyList(List<T> obj);
}
