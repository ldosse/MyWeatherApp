package uk.ac.gcu.myweatherapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.ac.gcu.myweatherapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_LOCATION_INDEX = "section_number";
    private static int i;
    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index,int locationIndex) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        i = locationIndex;
//        bundle.putInt(ARG_LOCATION_INDEX, locationIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
//        int locIndex = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
//            locIndex = getArguments().getInt(ARG_LOCATION_INDEX);
        }
        pageViewModel.setLocation(i);
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final TextView descTextView = root.findViewById(R.id.description);
        final TextView linkTextView = root.findViewById(R.id.link);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        pageViewModel.getDesc().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                descTextView.setText(s);
//                linkTextView.setText("lllink");
            }
        });
        return root;
    }
}