package ru.tulupov.alex.easyrussian.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.tulupov.alex.easyrussian.R;

public class TrainFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_train;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(LAYOUT, container, false);

        return view;
    }
}
