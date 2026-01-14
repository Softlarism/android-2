package com.example.softlarism;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Reportes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reportes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ---------- BOTONES Y NAVEGACIÓN ----------

        // A NUEVO REPORTE
        View btnSiguiente = view.findViewById(R.id.btn_add_report);

        btnSiguiente.setOnClickListener(v -> {
            Fragment siguienteFragment = new NuevoReporte(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A ALARMA
        View btnSiguiente2 = view.findViewById(R.id.item_alarma);

        btnSiguiente2.setOnClickListener(v -> {
            Fragment siguienteFragment = new Alarma(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A COMUNIDAD
        View btnSiguiente3 = view.findViewById(R.id.item_comunidad);

        btnSiguiente3.setOnClickListener(v -> {
            Fragment siguienteFragment = new Comunidad(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A PERFIL
        View btnSiguiente4 = view.findViewById(R.id.item_cuenta);

        btnSiguiente4.setOnClickListener(v -> {
            Fragment siguienteFragment = new Perfil(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}