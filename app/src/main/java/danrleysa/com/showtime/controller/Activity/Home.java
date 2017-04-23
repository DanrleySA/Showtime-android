package danrleysa.com.showtime.controller.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.dao.UsuarioDAO;
import danrleysa.com.showtime.database.DataBase;
import danrleysa.com.showtime.model.Usuario;

public class Home extends AppCompatActivity {

    private Usuario usuario;
    private TextView nome;
    private TextView email;
    private TextView idUsuario;
    private ListView listaUsers;
    private ArrayAdapter<String> adpUsers;
    private DataBase dataBase;
    private SQLiteDatabase con;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listaUsers = (ListView) findViewById(R.id.HomeListUsers);

        try {
            dataBase = new DataBase(this);
            con = dataBase.getReadableDatabase();
            usuarioDAO = new UsuarioDAO(con);
            adpUsers = usuarioDAO.getUsuarios(this);
            listaUsers.setAdapter(adpUsers);



        } catch (SQLiteException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            ex.printStackTrace();
        }
    }
}
