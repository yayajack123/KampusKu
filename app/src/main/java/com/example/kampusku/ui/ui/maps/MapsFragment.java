package com.example.kampusku.ui.ui.maps;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kampusku.MapsActivity;
import com.example.kampusku.MapsDetailActivity;
import com.example.kampusku.R;


public class MapsFragment extends Fragment {

    private MapsViewModel mViewModel;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps_fragment, container, false);
        button = (Button) view.findViewById(R.id.start_maps);
        Intent intent = new Intent(getContext(), MapsActivity.class);
        startActivity(intent);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        // TODO: Use the ViewModel
    }

}
