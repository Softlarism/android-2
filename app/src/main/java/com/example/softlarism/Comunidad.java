package com.example.softlarism;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Comunidad extends Fragment {

    EditText etBuscar;
    ImageButton btnBuscar;
    Spinner spinnerOpciones;
    RecyclerView recyclerComunidades;

    public Comunidad() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comunidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etBuscar = view.findViewById(R.id.etBuscar);
        btnBuscar = view.findViewById(R.id.btnBuscar);
        spinnerOpciones = view.findViewById(R.id.spinnerOpciones);
        recyclerComunidades = view.findViewById(R.id.recyclerComunidades);

        String[] opciones = {"Más opciones", "Agregar comunidad", "Ver solicitudes"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                opciones
        );
        spinnerOpciones.setAdapter(spinnerAdapter);

        recyclerComunidades.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<ComunidadItem> lista = new ArrayList<>();
        lista.add(new ComunidadItem("Vecinos Norte", "CDMX", "12 miembros"));
        lista.add(new ComunidadItem("Barrio Unido", "Guadalajara", "9 miembros"));
        lista.add(new ComunidadItem("Seguridad Sur", "Puebla", "15 miembros"));

        Comunidadadapter adapter = new Comunidadadapter(lista);
        recyclerComunidades.setAdapter(adapter);

        // Botón buscar
        btnBuscar.setOnClickListener(v -> {
            String texto = etBuscar.getText().toString();
            Toast.makeText(requireContext(), "Buscando: " + texto, Toast.LENGTH_SHORT).show();
        });

        // ---------- BOTONES Y NAVEGACIÓN ----------

        // A REPORTES
        View btnSiguiente = view.findViewById(R.id.reportes);

        btnSiguiente.setOnClickListener(v -> {
            Fragment siguienteFragment = new Reportes(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A ALARMA
        View btnSiguiente2 = view.findViewById(R.id.alarma);

        btnSiguiente2.setOnClickListener(v -> {
            Fragment siguienteFragment = new Alarma(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // A CUENTA
        View btnSiguiente3 = view.findViewById(R.id.cuenta);

        btnSiguiente3.setOnClickListener(v -> {
            Fragment siguienteFragment = new Perfil(); // <-- fragmento siguiente

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}
