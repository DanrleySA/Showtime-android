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
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.util.Utils;

public class CadastrarUsuario extends AppCompatActivity {

    private SQLiteDatabase con;
    private Button btnSave;
    private Button btnCancel;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText senhaConfirm;

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

                }
            }
        }
    }



    public void cancel(View view){
        finish();

    }
}
