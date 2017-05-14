package danrleysa.com.showtime.controller.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import danrleysa.com.showtime.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEventosFragment extends Fragment {


    public ListEventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_eventos, container, false);
        return view;
    }

}
