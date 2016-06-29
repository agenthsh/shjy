package com.example.hwang.testdb.db;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by hwang on 2016-06-26.
 */
public interface DBIncomeConstants {

    public class IncomeDB
    {
        private static String authority = "com.example.hwang.testdb";
        private static String tableName = "INCOME_DATA";
        private static int group = 0;
        private static int code = 1 + group * 10;
        private static int codeItem = 2 + group * 10;

        public static final String KEY_INDEX = "_id";
        public static final String COLUMN_INDEX = KEY_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT ";

        public static final String KEY_NAME = "name";
        public static final String COLUMN_NAME = KEY_NAME + " TEXT NOT NULL ";

        public static final String KEY_AMMOUNT = "ammount";
        public static final String COLUMN_AMMOUNT = KEY_AMMOUNT + " INTEGER DEFAULT 0 ";

        public static final String KEY_INCOME_DATE = "idate";
        public static final String COLUMN_INCOME_DATE = KEY_INCOME_DATE + " date ";


        public static void setAuthority(String auth) {
            authority = auth;
        }

        public static String getAuthority() {
            return authority;
        }

        public static String getTableName() {
            return tableName;
        }

        public static int getGroup() {
            return group;
        }

        public static int getCode() {
            return code;
        }

        public static int getCodeItem() {
            return codeItem;
        }

        public static Uri getUri() {
            return Uri.parse("content://" + getAuthority() + "/" + getTableName());
        }

        public static String getDir() {
            return "vnd.android.cursor.dir/vnd." + getAuthority() + "." + getTableName();
        }

        public static String getItem() {
            return "vnd.android.cursor.item/vnd." + getAuthority() + "." + getTableName();
        }

        public static String createTable() {
            StringBuffer buffer = new StringBuffer();

            buffer.append(COLUMN_INDEX);
            buffer.append(",");
            buffer.append(COLUMN_NAME);
            buffer.append(",");
            buffer.append(COLUMN_AMMOUNT);
            buffer.append(",");
            buffer.append(COLUMN_INCOME_DATE);

            return ("CREATE TABLE " + getTableName() + " (" + buffer.toString() + ");");
        }

        public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            if (oldVersion == 1 && newVersion == 2)
            {
                db.execSQL("ALTER TABLE " + getTableName() + " ADD Column " + COLUMN_INCOME_DATE + ";");
            }
        }
    }

}
