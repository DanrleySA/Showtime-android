package danrleysa.com.showtime.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import danrleysa.com.showtime.model.Usuario;

/**
 * Created by Danrley on 22/04/2017.
 */

public class UsuarioDAO {

    private SQLiteDatabase con;

    public UsuarioDAO(SQLiteDatabase con) {
        this.con = con;
    }

    public Long save(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("NOME", usuario.getNome());
        values.put("EMAIL", usuario.getEmail());
        values.put("SENHA", usuario.getSenha());
        return con.insertOrThrow("USUARIO", null, values);
    }

    public Usuario getUserByEmailAndSenha(String email, String senha) {
        Cursor cursor = con.rawQuery(" SELECT * FROM USUARIO WHERE EMAIL =? AND SENHA =? ", new String[]{String.valueOf(email), String.valueOf(senha)});
        if (cursor.getCount() != 1){
            return null;
        }else{
            cursor.moveToFirst();
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            return usuario;
        }
    }

    public ArrayAdapter<String> getUsuarios(Context context) {
        ArrayAdapter<String> adpUsuarios = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = con.query("USUARIO", null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String nome = cursor.getString(1);
                adpUsuarios.add(nome);
            } while (cursor.moveToNext());
        }
        return adpUsuarios;
    }
}
