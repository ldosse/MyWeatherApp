package uk.ac.gcu.myweatherapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

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
//        private RecyclerView recyclerView;
//        private RecyclerView.Adapter mAdapter;
//        private RecyclerView.LayoutManager layoutManager;
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView brief = root.findViewById(R.id.brief);
        final TextView descTextView = root.findViewById(R.id.description);
        final TextView temperatureTextView = root.findViewById(R.id.temperature);
        final RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.attributesRecyclerView);

//        TODO: update this when working on pull up interaction
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
        final RecyclerView.Adapter[] mAdapter = new RecyclerView.Adapter[1];

        pageViewModel.getAttributes().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(@Nullable Map<String, String> map) {
                mAdapter[0] = new AttributesAdapter(map);
                recyclerView.setAdapter(mAdapter[0]);

            }
        });


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                temperatureTextView.setText(s);
            }
        });
        pageViewModel.getDesc().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("On changed "+s);
                descTextView.setText(s);
//                linkTextView.setText("lllink");
            }
        });
        pageViewModel.getBrief().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                brief.setText(s);
//                linkTextView.setText("lllink");
            }
        });
        return root;
    }
}