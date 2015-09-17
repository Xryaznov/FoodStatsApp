package main.java.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{
    public static String getCurrentDay()
    {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(new Date().getTime());
    }
}
