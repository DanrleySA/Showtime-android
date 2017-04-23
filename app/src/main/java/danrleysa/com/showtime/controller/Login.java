package danrleysa.com.showtime.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.dao.UsuarioDAO;
import danrleysa.com.showtime.database.DataBase;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.util.Utils;

public class Login extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase con;
    private Button btnLogin;
    private Button btnRegistrar;
    private EditText email;
    private EditText senha;
    private UsuarioDAO usuarioDAO;

    private TextView idt;
    private TextView nomet;
    private TextView emailt;
    private TextView senhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.LoginBtnLogin);
        btnLogin = (Button) findViewById(R.id.LoginBtnLogin);
        btnRegistrar = (Button) findViewById(R.id.LoginBtnRegistrar);
        email = (EditText) findViewById(R.id.LoginEdtEmail);
        senha = (EditText) findViewById(R.id.LoginEdtSenha);

        try {
            dataBase = new DataBase(this);
            con = dataBase.getWritableDatabase();
            usuarioDAO = new UsuarioDAO(con);

        } catch (SQLiteException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            ex.printStackTrace();
        }
    }

    public void teste(View view) {
        Intent t = new Intent(this, Home.class);
        startActivity(t);
    }

    public void logar(View view) {
        String emailTxt = email.getText().toString();
        String senhaTxt = senha.getText().toString();
        if (emailTxt.isEmpty() || senhaTxt.isEmpty()) {
            Toast.makeText(this, "Preenha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isValidEmail(emailTxt)) {
                try {
                    Usuario usuario = usuarioDAO.getUserByEmailAndSenha(emailTxt, senhaTxt);
                    if (usuario != null) {
                        Intent intent = new Intent(this, Home.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLiteException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registrar(View view) {
        finish();
        Intent intent = new Intent(this, CadastrarUsuario.class);
        if (Utils.isValidEmail(email.getText().toString()))
            intent.putExtra("email", email.getText().toString());
        startActivity(intent);
    }

}
