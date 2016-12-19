package com.example.rruizp.gestavandroid.Utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AndroidJsonSerializerHelper<T>
{

    private String serialize(List<T> entityList)
    {
        Gson gson = new Gson();
        return gson.toJson(entityList);
    }

    private List<T> deserialize(String jsonEntityList, Type targetType)
    {
        Gson gson = new Gson();
        ArrayList<T> entityList;
        entityList = gson.fromJson(jsonEntityList, targetType);
        return entityList;
    }

    public void save(List<T> entityList, String path, String fileName, String fileExtension)
    {
        String data = serialize(entityList);
        if (data.length() > 0)
            AndroidFileHelper.writeFile(data, path, fileName, fileExtension);
    }

    public List<T> read(Type targetType, String path, String fileName, String fileExtension)
    {
        String fileData = AndroidFileHelper.readFile(path, fileName, fileExtension);
        return deserialize(fileData, targetType);
    }
}
