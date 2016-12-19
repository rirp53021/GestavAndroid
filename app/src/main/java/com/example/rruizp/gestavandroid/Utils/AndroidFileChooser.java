package com.example.rruizp.gestavandroid.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class AndroidFileChooser
{
    public static List<File> getFileList(File selectedFile)
    {
        List<File> fileList = null;
        if (selectedFile.isDirectory())
        {
            fileList = new ArrayList<>();
            File[] file = selectedFile.listFiles();
            for (int i = 0; i < selectedFile.list().length; i++)
            {
                fileList.add(file[i]);
            }
        }
        return fileList;
    }


    public static List<File> getFileList(String directoryPath)
    {
        List<File> fileList = null;
        File file = new File(directoryPath);
        if (file.isDirectory())
        {
            fileList = new ArrayList<>();
            File[] files = file.listFiles();
            for (File objFile : files)
            {
                fileList.add(objFile);
            }
        }
        return fileList;
    }

    public static String getFileName(File file)
    {
        return file.getName();
    }

    static String getFilePath(File file)
    {
        return file.getPath();
    }

    static List<File> backFolderList(File file)
    {
        return file.getPath().equalsIgnoreCase("/mnt") ? null : AndroidFileHelper.getFileList(new File(file.getPath()));
    }

    static File getPathBackFolder(File file)
    {
        String currentPath = file.getPath();
        char[] array = currentPath.toCharArray();
        int pos = AndroidStringHelper.getCharacterPosition(currentPath);
        char[] newArray = new char[pos];

        for (int index2 = 0; index2 < pos; index2++)
        {
            newArray[index2] = array[index2];
        }

        currentPath = String.valueOf(newArray);

        return new File(currentPath);
    }
}
