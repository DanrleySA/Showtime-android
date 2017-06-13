package danrleysa.com.showtime.controller.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import danrleysa.com.showtime.R;
import danrleysa.com.showtime.model.Evento;
import danrleysa.com.showtime.model.Usuario;
import danrleysa.com.showtime.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeusEventosInscritos extends Fragment {

    ArrayAdapter<Evento> eventoArrayAdapter = null;

    public MeusEventosInscritos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meus_eventos_inscritos, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.MeusEventosInscritosListViewEventos);

        Usuario usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");
        Toast.makeText(getActivity(), usuario.toString(), Toast.LENGTH_LONG).show();

        Call listarMeusEventos = new RetrofitInicializador()
                .getEventoService().
                        listarEventosInscritos(usuario.getIdUsuario());

        listarMeusEventos.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(getActivity(), "@@@@@@@@@@@@", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return view;
    }

}
