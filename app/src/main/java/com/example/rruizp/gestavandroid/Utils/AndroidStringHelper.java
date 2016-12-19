package com.example.rruizp.gestavandroid.Utils;

/**
 * Author by Rruizp on 16/12/2016.
 */

public class AndroidStringHelper
{
    static int getCharacterPosition(String text)
    {
        char[] array = text.toCharArray();

        for (int index = 0; index < array.length; index++)
        {
            if (array[index] == '/')
            {
                return index;
            }
        }

        return -1;
    }
}
