package com.example.alaa.memorygame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class chose_level_fragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    static int num = 0;
    Grid_adapter_levels grid_adapter_levels;

    public chose_level_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chose_level_fragment, container, false);
        view.findViewById(R.id.back_button).setVisibility(View.VISIBLE);
        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        setadapter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setadapter();
    }

    public void setadapter() {
        recyclerView = view.findViewById(R.id.chose_level_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        grid_adapter_levels = new Grid_adapter_levels(MainActivity.levels, getActivity());
        recyclerView.setAdapter(grid_adapter_levels);
    }
}
