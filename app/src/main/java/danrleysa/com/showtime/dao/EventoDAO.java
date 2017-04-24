package danrleysa.com.showtime.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import danrleysa.com.showtime.model.Evento;

/**
 * Created by Danrley on 24/04/2017.
 */

public class EventoDAO {

    private SQLiteDatabase con;

    public EventoDAO(SQLiteDatabase con) {
        this.con = con;
    }

    public ArrayAdapter<Evento> findAll(Context context) {
        ArrayAdapter<Evento> eventos = new ArrayAdapter<Evento>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = con.query("EVENTO", null, null, null, null, null, "data");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                /*Evento evento = new Evento(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getDate(4));
                eventos.add(cursor.get);*/
            } while (cursor.moveToNext());
        }
        return eventos;
    }
}
