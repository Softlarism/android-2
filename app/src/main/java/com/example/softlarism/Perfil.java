package com.example.softlarism;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Perfil extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ---------- BOTONES Y NAVEGACIÓN ----------

        // A CONFIGURAR PERFIL
        View btnSiguiente = view.findViewById(R.id.btnconfigurar);

        btnSiguiente.setOnClickListener(v -> {
            Fragment siguienteFragment = new Configuraperfil(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A REPORTES
        View btnSiguiente2 = view.findViewById(R.id.btn_menu_reports);

        btnSiguiente2.setOnClickListener(v -> {
            Fragment siguienteFragment = new Reportes(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A ALARMA
        View btnSiguiente3 = view.findViewById(R.id.btn_menu_alarm);

        btnSiguiente3.setOnClickListener(v -> {
            Fragment siguienteFragment = new Alarma(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A COMUNIDAD
        View btnSiguiente4 = view.findViewById(R.id.btn_menu_community);

        btnSiguiente4.setOnClickListener(v -> {
            Fragment siguienteFragment = new Comunidad(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}