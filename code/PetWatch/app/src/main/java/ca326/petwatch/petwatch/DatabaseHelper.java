//package ca326.petwatch.petwatch;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class DatabaseHelper extends SQLiteOpenHelper
//{
//    private static final String TAG = "DatabaseHelper";
//
//    private static final String TABLE_NAME = "SpeedLogs";
//    private static final String COL1 = "time";
//    private static final String COL2 = "location";
//    private static final String COL3 = "speed";
//
//
//
//    public DatabaseHelper(Context context)
//    {
//        super(context, TABLE_NAME, null, 1);
//    }
////CREATE TABLE `users` ( `userI.D.` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `fName` TEXT, `sName` TEXT NOT NULL, `pName` TEXT NOT NULL, `eMail` TEXT NOT NULL, `pWord` TEXT NOT NULL, `ardI.D.` INTEGER NOT NULL )
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase)
//    {
//        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + COL2 + " " + text + ", " + COL3 + " " );
//        sqLiteDatabase.execSQL(createTable);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j)
//    {
//        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
//        onCreate(sqLiteDatabase);
//    }
//
//    public boolean addData(String item, String col)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(col, item);
//
//        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        // check if data was entered incorrectly
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }
//}
