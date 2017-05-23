package danrleysa.com.showtime.controller.Fragment;


import android.content.Context;
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
import danrleysa.com.showtime.dao.UsuarioDAO;
import danrleysa.com.showtime.database.DataBase;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.retrofit.RetrofitInicializador;
import danrleysa.com.showtime.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadUserFragment extends Fragment {

    private DataBase dataBase;
    private SQLiteDatabase con;
    private UsuarioDAO usuarioDAO;
    private Context context;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText senhaConfirm;
    private Button btnSave;
    private Button btnCancelar;

    public CadUserFragment() {
        // Required empty public constructor
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

        try {
            dataBase = new DataBase(context);
            con = dataBase.getWritableDatabase();
            usuarioDAO = new UsuarioDAO(con);
        }catch (SQLiteException e){
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTxt = nome.getText().toString();
                String emailTxt = email.getText().toString();
                String senhaTxt = senha.getText().toString();
                String senhaConfirmTxt = senhaConfirm.getText().toString();

                if (nomeTxt.replace(" ", "").isEmpty() || emailTxt.replace(" ", "").isEmpty()
                        || senhaTxt.replace(" ", "").isEmpty() || senhaConfirmTxt.replace(" ", "").isEmpty()) {

                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    if (!Utils.isValidEmail(emailTxt)) {
                        Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show();
                    }else{
                        if (!senhaTxt.equals(senhaConfirmTxt)){
                            Toast.makeText(context, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                        }else{
                            try{
                                Usuario usuario = new Usuario(nomeTxt, emailTxt, senhaTxt);
                                Call save = new RetrofitInicializador().getUsuarioService().merge(usuario);
                                save.enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        Toast.makeText(getActivity().getApplicationContext(), "salvou " + response.message(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Toast.makeText(getActivity().getApplicationContext(), "deu ruim" + t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

//                                if (usuarioDAO.save(usuario) == -1){
//                                    Toast.makeText(context, "Erri ao registrar-se ", Toast.LENGTH_LONG).show();
//                                }else{
//                                    Intent it = new Intent(context, Principal.class);
//                                    getActivity().finish();
//                                    it.putExtra("usuario", usuario);
//                                    startActivity(it);
//                                }
                            }catch (SQLiteException e) {
                                e.printStackTrace();
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(context, e.getMessage() + "@@@@@" + e.getCause(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
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

    @Override
    public void onResume() {
        super.onResume();
        Call danrley = new RetrofitInicializador().getUsuarioService().getById(2L);
        danrley.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    Usuario usuario = (Usuario) response.body();
                    Toast.makeText(getActivity().getApplicationContext(), usuario.getNome(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Deu errado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }
}
