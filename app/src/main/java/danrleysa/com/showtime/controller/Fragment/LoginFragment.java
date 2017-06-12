package danrleysa.com.showtime.controller.Fragment;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.bo.UsuarioBO;
import danrleysa.com.showtime.controller.Activity.Principal;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private SQLiteDatabase con;
    private EditText email;
    private EditText senha;
    private Context context;
    private Button btnLogin;
    private Button btnRegistrar;


    public LoginFragment() {
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

        Bundle bundle = getArguments();

        email.setText(bundle != null ? getArguments().getString("email") : "");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_login, new CadUserFragment()).commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UsuarioBO.getInstance().validaCampos(email, senha).equals("")) {
                    Call login = new RetrofitInicializador().getUsuarioService().getByPorEmailAndSenha
                            (email.getText().toString(), senha.getText().toString());
                    login.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.body() != null) {
                                Intent intent = new Intent(context, Principal.class);
                                intent.putExtra("usuario", (Usuario) response.body());
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(context, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
            }
        });
        return view;
    }
}
