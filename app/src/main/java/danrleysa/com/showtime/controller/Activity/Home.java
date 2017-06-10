package danrleysa.com.showtime.controller.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.model.Usuario;

public class Home extends AppCompatActivity {

    private Usuario usuario;
    private TextView nome;
    private TextView email;
    private TextView idUsuario;
    private ListView listaUsers;
    private ArrayAdapter<String> adpUsers;
    private SQLiteDatabase con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listaUsers = (ListView) findViewById(R.id.HomeListUsers);


    }
}
