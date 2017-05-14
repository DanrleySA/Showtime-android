package danrleysa.com.showtime.controller.Fragment;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.controller.Activity.Principal;
import danrleysa.com.showtime.dao.UsuarioDAO;
import danrleysa.com.showtime.database.DataBase;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private DataBase dataBase;
    private SQLiteDatabase con;
    private EditText email;
    private EditText senha;
    private UsuarioDAO usuarioDAO;
    private Context context;
    private Button btnLogin;
    private Button btnRegistrar;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity().getApplicationContext();
        btnLogin = (Button) view.findViewById(R.id.LoginBtnLogin);
        btnRegistrar = (Button) view.findViewById(R.id.LoginBtnRegistrar);
        email = (EditText) view.findViewById(R.id.LoginEdtEmail);
        senha = (EditText) view.findViewById(R.id.LoginEdtSenha);

        try {
            dataBase = new DataBase(context);
            con = dataBase.getWritableDatabase();
            usuarioDAO = new UsuarioDAO(con);
        }catch (SQLiteException e){
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_login, new CadUserFragment()).commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt = email.getText().toString();
                String senhaTxt = senha.getText().toString();
                if (emailTxt.isEmpty() || senhaTxt.isEmpty()) {
                    Toast.makeText(context, "Preenha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isValidEmail(emailTxt)) {
                        try {
                            Usuario usuario = usuarioDAO.getUserByEmailAndSenha(emailTxt, senhaTxt);
                            if (usuario != null) {
                                Intent intent = new Intent(context, Principal.class);
                                intent.putExtra("usuario", usuario);
                                startActivity(intent);
                            } else {
                                Toast.makeText(context, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (SQLiteException e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}
