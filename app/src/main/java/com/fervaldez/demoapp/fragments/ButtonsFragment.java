package com.fervaldez.demoapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fervaldez.demoapp.R;


public class ButtonsFragment extends Fragment {

    public ButtonsFragment() {
        // Required empty public constructor
    }

    public static ButtonsFragment newInstance() {
        ButtonsFragment fragment = new ButtonsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showAlert() {
        if (getContext() != null) {
            AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("An Alert!").setMessage("Some Message").create();

            dialog.show();
        }
    }

    public void showToast() {
        if (getContext() != null) {
            Toast.makeText(getContext(), "here is a toast", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_buttons, container, false);

        Button alertButton = v.findViewById(R.id.buttonAlert);
        Button toastButton = v.findViewById(R.id.buttonToast);

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        return v;
    }

}
