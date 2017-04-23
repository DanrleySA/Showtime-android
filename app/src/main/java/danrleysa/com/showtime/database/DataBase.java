package danrleysa.com.showtime.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danrley on 22/04/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context, "SHOWTIME_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateTableUsuario());
        db.execSQL(ScriptSQL.getCreateTableEvento());
        db.execSQL(ScriptSQL.getCreateTableInscricao());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
