package danrleysa.com.showtime.controller.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
public class ListEventosFragment extends Fragment {

    ArrayAdapter<Evento> eventoArrayAdapter = null;


    public ListEventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_eventos, container, false);

        final ListView eventos = (ListView) view.findViewById(R.id.ListEventosListView);

        Call login = new RetrofitInicializador().getEventoService().listarEventos();
        login.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Evento> lista = (List<Evento>) response.body();
                eventoArrayAdapter = new ArrayAdapter<Evento>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, (List<Evento>) response.body());

                eventos.setAdapter(eventoArrayAdapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder dlgEvento = new AlertDialog.Builder(getActivity());

                final CharSequence[] itens = {"Ver mais", "Inscrever-se", "Cancelar inscrição"};

                dlgEvento.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String opcao = (String) itens[which];

                        if (opcao.equals("Ver mais")) {
                            Toast.makeText(getActivity(), "Ver mais", Toast.LENGTH_SHORT).show();
                        } else if (opcao.equals("Inscrever-se")) {


                            Usuario usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");
                            Evento evento = (Evento) eventos.getAdapter().getItem(position);

                            Call inscricao = new RetrofitInicializador().getInscricaoService().merge(usuario.getIdUsuario(), evento.getIdEvento());
                            inscricao.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {

                                }
                            });
                        } else if (opcao.equals("Cancelar inscrição")) {
                            dialog.cancel();
                        }
                    }
                });
                dlgEvento.setTitle("Inscreva-se");
                AlertDialog alertDialog = dlgEvento.create();
                alertDialog.show();
            }
        });

        return view;
    }


}
