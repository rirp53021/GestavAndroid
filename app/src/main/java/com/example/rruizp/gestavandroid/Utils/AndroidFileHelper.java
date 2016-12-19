package com.example.rruizp.gestavandroid.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AndroidFileHelper
{
    static void writeFile(String setString, String path,
                          String fileName, String fileExtension)
    {
        try
        {
            if (isSdMounted())
            {
                File directory = new File(getSdCard() + path);
                if (!directory.exists())
                {
                    if (createDirectory(path))
                    {
                        Log.d(null, "El archivo creado correctamente");
                    } else
                    {
                        Log.d(null, "El archivo no creado correctamente");
                    }
                }
                OutputStreamWriter write = null;
                FileOutputStream fileOutputStream = null;
                try
                {
                    File file = new File(directory, fileName + fileExtension);
                    fileOutputStream = new FileOutputStream(file);
                    write = new OutputStreamWriter(fileOutputStream);
                    write.write(setString);
                } finally
                {
                    fileOutputStream.close();
                    write.flush();
                    write.close();
                }


            }
        } catch (Exception e)
        {
            Log.d(e.toString(), e.getMessage());
        }

    }

    static boolean deleteFile(String path, String filename,
                                     String fileExtension)
    {
        File sdCard;
        File directory;
        File file = null;
        if (isSdMounted())
        {
            try
            {
                sdCard = Environment.getExternalStorageDirectory();
                directory = new File(sdCard.getAbsolutePath() + path);
                file = new File(directory, filename + fileExtension);
            } catch (Exception e)
            {
                Log.d(e.toString(), e.getMessage());
            }
        }
        return file.delete();
    }

    public static void deleteFileOnExit(File file)
    {
        file.deleteOnExit();
    }

    static boolean createDirectory(String path)
    {
        File directory = new File(getSdCard() + path);
        return directory.mkdirs();
    }

    static String readFile(String path, String fileName,
                                  String fileExtension)
    {
        String fileContent = null;
        if (isSdMounted())
            try
            {
                if (existsFile(path, fileName, fileExtension))
                {
                    File file = new File(getSdCard() + path, fileName + fileExtension);
                    BufferedReader bufferedReader = null;
                    FileInputStream fileInputStream = null;
                    try
                    {
                        fileInputStream = new FileInputStream(file);
                        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                        String line;
                        while ((line = bufferedReader.readLine()) != null)
                        {
                            fileContent += line;
                        }

                    } finally
                    {
                        fileInputStream.close();
                        bufferedReader.close();
                    }
                } else
                {
                    fileContent = null;
                    Log.d(null, "File not found");
                }

            } catch (IOException e)
            {
                Log.d(e.toString(), e.getMessage());
            }
        return fileContent;
    }

    public static boolean existsDirectory(String path)
    {
        File fullPath = new File(getSdCard() + path);
        return fullPath.exists();
    }

    public static boolean existsFile(String path, String fileName,
                                     String fileExtension)
    {
        File fullPath = new File(getSdCard() + path, fileName + fileExtension);
        return fullPath != null && fullPath.exists();
    }

    public static boolean isSdMounted()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String readFileRaw(InputStream fraw)
    {
        String text;
        try
        {
            BufferedReader brin = new BufferedReader(
                    new InputStreamReader(fraw));
            text = brin.readLine();
            fraw.close();
        } catch (Exception ex)
        {
            text = null;
            Log.d(ex.toString(), ex.getMessage());
        }
        return text;
    }

    public static String getSdCard()
    {
        File sdCard = Environment.getExternalStorageDirectory();
        return sdCard.getAbsolutePath();
    }

    public static void deleteDirectory(String path)
    {
        File sdCard;
        File directory;
        if (isSdMounted())
        {
            try
            {
                sdCard = Environment.getExternalStorageDirectory();
                directory = new File(sdCard.getAbsolutePath() + path);
                if (directory.isDirectory())
                    for (File child : directory.listFiles())
                    {
                        if (child.listFiles().length == 0) child.delete();
                        deleteDirectory(child.getAbsolutePath());
                    }
                directory.delete();
            } catch (Exception e)
            {
                Log.d(e.toString(), e.getMessage());
            }
        }
    }

    public static List<File> getFileList(File selectedFile)
    {
        ArrayList<File> fileList = null;
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

    public static boolean deleteFilesOfDirectory(String path)
    {
        File directory = new File(getSdCard() + path);
        List<File> fileList = getFileList(directory);
        for (int index = 0; index < fileList.size(); index++)
        {
            return fileList.get(index).delete();
        }

        return false;
    }

}
