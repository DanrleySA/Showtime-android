package danrleysa.com.showtime.controller.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.dao.UsuarioDAO;
import danrleysa.com.showtime.database.DataBase;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.util.Utils;

public class CadastrarUsuario extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase con;
    private Button btnSave;
    private Button btnCancel;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText senhaConfirm;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        btnSave = (Button) findViewById(R.id.CadUserBtnSave);
        btnCancel = (Button) findViewById(R.id.CadUserBtnCancel);
        nome = (EditText) findViewById(R.id.CadUserEdtNome);
        email = (EditText) findViewById(R.id.CadUserEdtEmail);
        senha = (EditText) findViewById(R.id.CadUserEdtSenha);
        senhaConfirm = (EditText) findViewById(R.id.CadUserEdtSenhaConfirm);

        email.setText(getIntent().getStringExtra("email"));

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

    public void save(View view) {
        String nomeTxt = nome.getText().toString();
        String emailTxt = email.getText().toString();
        String senhaTxt = senha.getText().toString();
        String senhaConfirmTxt = senhaConfirm.getText().toString();

        if (nomeTxt.replace(" ", "").isEmpty() || emailTxt.replace(" ", "").isEmpty()
                || senhaTxt.replace(" ", "").isEmpty() || senhaConfirmTxt.replace(" ", "").isEmpty()) {

            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else{
            if (!Utils.isValidEmail(emailTxt)) {
                Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            }else{
                if (!senhaTxt.equals(senhaConfirmTxt)){
                    Toast.makeText(this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        if (usuarioDAO.save(new Usuario(nomeTxt, emailTxt, senhaTxt)) == -1){
                            Toast.makeText(this, "Deu erro", Toast.LENGTH_LONG).show();
                        }else{
                            finish();
                            Intent it = new Intent(this, Home.class);
                            startActivity(it);
                        }
                    }catch (SQLiteException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage() + "@@@@@" + e.getCause(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }



    public void cancel(View view){
        finish();

    }
}
