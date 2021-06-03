package com;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.login.R;

public class QrProfile extends Fragment {

    private Button button;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_profile, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.Location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.GpsQ);
            }
        });

        view.findViewById(R.id.Profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vl) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.UserProfile);
            }
        });

        view.findViewById(R.id.ReaderQR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View va) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.action_ScanQR);
            }
        });

        view.findViewById(R.id.QRH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.action_History);
            }
        });

        view.findViewById(R.id.GpsQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vb) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.action_GPS);
            }
        });

        view.findViewById(R.id.GenQR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vc) {
                NavHostFragment.findNavController(QrProfile.this)
                        .navigate(R.id.action_Gen);
            }
        });
    }
}
