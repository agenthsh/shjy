package com.example.hwang.testdb.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.UnsupportedEncodingException;

/**
 * Created by hwang on 2016-06-21.
 */
public class StringUtil {

    private static final String CUSTOM_STACKTRACE_PREFIX = "STACKTRACE: ";
    private static final String LINE_SEPARATOR = "line.separator";

    public static String bytesToString (byte[] bytes)
    {
        String strData = "";

        if(bytes != null && bytes.length>0)
        {
            try
            {
                strData = new String(bytes, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                //e
            }
        }
        return strData;
    }

    public static byte[] stringToBytes(String strData)
    {
        byte[] data = null;

        if(isNotEmpty(strData))
        {
            try
            {
                data = strData.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                //e
            }
        }
        return data;
    }

    // "" = false, null = false, "abc" = true
    public static boolean isNotEmpty(String str)
    {
        return !isNotEmpty(str);
    }

    // "" = true, null = true, "abc" = false
    public static boolean isEmpty(String str)
    {
        return (str ==null || str.length() ==0);
    }

    public static String getCustomStackTrace(Throwable aThrowable)
    {
        final StringBuilder result = new StringBuilder(CUSTOM_STACKTRACE_PREFIX);
        result.append(aThrowable.toString());
        final String NEW_LINE = System.getProperty(LINE_SEPARATOR);
        result.append(NEW_LINE);

        for (StackTraceElement element : aThrowable.getStackTrace())
        {
            result.append(element);
            result.append(NEW_LINE);
        }
        return result.toString();
    }

    public static int getInt(String str)
    {
        int val = 0;

        if(isNotEmpty(str))
        {
            try
            {
                val = Integer.parseInt(str);
            }
            catch (Exception e)
            {
                //e
            }
        }
        return val;
    }

    public static long getLong(String str)
    {
        long val = 0;

        if(isNotEmpty(str))
        {
            try
            {
                val = Long.parseLong(str);
            }
            catch (Exception e)
            {
                //
            }
        }
        return val;
    }

    public static PackageInfo getPackageInfo(Context context, String packageName)
    {
        try
        {
            return context.getPackageManager().getPackageInfo(packageName, 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String makeLogMessage(String msg)
    {
        String strLogMessage = null;

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];

        String strClassName = stackTrace.getClassName();
        String strFileName = new String(strClassName.substring(strClassName.length()+1));
        String strMethodName = stackTrace.getMethodName();
        int lineNumber = stackTrace.getLineNumber();

        strLogMessage = strFileName + ", " + strMethodName + "(), " + lineNumber;
        return strLogMessage;
    }

}
