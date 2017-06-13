package danrleysa.com.showtime.controller.Fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

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
public class CadEventoFragment extends Fragment {

    private int ano, mes, dia, hora, minuto;

    public CadEventoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cad_evento, container, false);


        Button btnTime = (Button) view.findViewById(R.id.CadEventoBtnTime);
        Button btnDate = (Button) view.findViewById(R.id.CadEventoBtnData);
        Button btnCancel = (Button) view.findViewById(R.id.CadEventoBtnCancel);
        Button btnSave = (Button) view.findViewById(R.id.CadEventoBtnSave);
        final EditText local = (EditText) view.findViewById(R.id.CadEventoEdtLocal);
        final EditText descricao = (EditText) view.findViewById(R.id.CadEventoEdtDescricao);

        final Date dataHora = new Date();

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar horaCalendar = Calendar.getInstance();
                hora = horaCalendar.get(Calendar.HOUR_OF_DAY);
                minuto = horaCalendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dataHora.setHours(hourOfDay);
                        dataHora.setMinutes(minute);
                    }
                }, hora, minuto, true);
                timePickerDialog.show();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar dataCalendar = Calendar.getInstance();
                ano = dataCalendar.get(Calendar.YEAR);
                mes = dataCalendar.get(Calendar.MONTH);
                dia = dataCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dataHora.setYear(year);
                        dataHora.setMonth(month);
                        dataHora.setDate(dayOfMonth);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameContainerPrincipal, new ListEventosFragment())
                        .commit();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento evento = new Evento();
                evento.setOrganizador((Usuario) getActivity().getIntent().getSerializableExtra("usuario"));
                evento.setLocal(local.getText().toString());
                evento.setDescricao(descricao.getText().toString());
                evento.setLotacao(50);
                evento.setDataHora(dataHora);

                if (evento.getDataHora() == null) {
                    Toast.makeText(getActivity(), "Selecione a data e o horário!", Toast.LENGTH_SHORT).show();
                } else {
                    if (evento.getDataHora().before(Calendar.getInstance().getTime())) {
                        Toast.makeText(getActivity(), "A data não pode ser antes da data atual", Toast.LENGTH_SHORT).show();
                    } else {
                        Call save = new RetrofitInicializador().getEventoService().merge(evento);
                        save.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    getActivity()
                                            .getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frameContainerPrincipal, new ListEventosFragment())
                                            .commit();
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });
        return view;
    }

}