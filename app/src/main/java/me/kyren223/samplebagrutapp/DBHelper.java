package me.kyren223.samplebagrutapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context _context) {
        super(_context, "MyApplication2", null, 1);
        context = _context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, login text, pass text, firstName text, lastName text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }
    private void MessageIt(String title, String msg) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setCancelable(true);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNegativeButton("Close", null);
        dlg.show();
    }
    public boolean InsertOneUser(String login, String pass, String first, String last){
        long res;
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("pass", pass);
            cv.put("firstName", first);
            cv.put("lastName", last);
            res = db.insert("users", null, cv);
        }
        if (res == -1) //error
            Toast.makeText(context, "Error in database Insert!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, login+" Added successfully", Toast.LENGTH_LONG).show();

        return res != -1;
    }

    //for Development
    public void ShowTable(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+table, null);

        if (cursor.getCount() == 0) {
            MessageIt(table, "No data");
            return;
        }
        String buf = "";
        while (cursor.moveToNext()){
            buf += "[0]: "+cursor.getString(0)+"\n";
            buf+= "[1]: "+cursor.getString(1)+"\n";
            buf += "[2]: "+cursor.getString(2)+"\n";
            buf += "[3]: "+cursor.getString(3)+"\n";
            buf += "[4]: "+cursor.getString(4)+"\n\n";
        }
        MessageIt(table, buf);
    }

    public Cursor GetUserData(String login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "WHERE login='"+login+"'";
        Cursor cursor = db.rawQuery("SELECT login, name FROM users " +where, null);

        if (cursor.getCount() == 0)
            return null;

        cursor.moveToNext();
        return cursor;
    }

    public Boolean TryLogin(String login, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "WHERE login='"+login+"' AND pass='"+pass+"'";
        Cursor cursor = db.rawQuery("SELECT login, firstName FROM users " +where, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "user&Password mismatch", Toast.LENGTH_LONG).show();

            return false;
        }

        cursor.moveToNext();
        return true;
    }

}

