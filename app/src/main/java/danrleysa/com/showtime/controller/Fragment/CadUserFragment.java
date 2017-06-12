package danrleysa.com.showtime.controller.Fragment;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.bo.UsuarioBO;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadUserFragment extends Fragment {

    private SQLiteDatabase con;
    private Context context;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText senhaConfirm;
    private Button btnSave;
    private Button btnCancelar;

    public CadUserFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cad_user, container, false);
        context = getActivity().getApplicationContext();
        btnSave = (Button) view.findViewById(R.id.CadUserBtnSave);
        btnCancelar = (Button) view.findViewById(R.id.CadUserBtnCancel);
        nome = (EditText) view.findViewById(R.id.CadUserEdtNome);
        email = (EditText) view.findViewById(R.id.CadUserEdtEmail);
        senha = (EditText) view.findViewById(R.id.CadUserEdtSenha);
        senhaConfirm = (EditText) view.findViewById(R.id.CadUserEdtSenhaConfirm);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validaCampos = UsuarioBO.getInstance().validaCampos(nome, email, senha, senhaConfirm);
                if (validaCampos.equals("")) {
                    final Usuario usuario = new Usuario(nome.getText().toString(), email.getText().toString(), senha.getText().toString());
                    Call save = new RetrofitInicializador().getUsuarioService().merge(usuario);
                    save.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.isSuccessful()) {
                                Bundle email = new Bundle();
                                email.putString("email", usuario.getEmail());
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Fragment f = new LoginFragment();
                                f.setArguments(email);
                                fragmentTransaction.replace(R.id.frame_container_login, f).commit();
                            } else {
                                try {
                                    Toast.makeText(getActivity().getApplicationContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getActivity().getApplicationContext(), "Erro ao registrar-se" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), validaCampos, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container_login, new LoginFragment())
                        .commit();
            }
        });
        return view;
    }
}
