package danrleysa.com.showtime.controller.Fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import danrleysa.com.showtime.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadEventoFragment extends Fragment {

    public CadEventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cad_evento, container, false);

        Button btnTime = (Button) view.findViewById(R.id.CadEventoBtnTime);
        Button btnDate = (Button) view.findViewById(R.id.CadEventoBtnData);
        Button btnCancel = (Button) view.findViewById(R.id.CadEventoBtnCancel);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameContainerPrincipal, new LoginFragment())
                        .commit();
            }
        });


        return view;
    }
}
