package com.example.softlarism;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CREARCUENTA extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.crearcuenta, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Animación fade-in al cargar el fragmento
        rootView.post(() -> {
            Animation fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
            rootView.startAnimation(fadeIn);
        });

        // --- Referencias a los campos del formulario ---
        EditText nombre = rootView.findViewById(R.id.etNombre);
        EditText correo = rootView.findViewById(R.id.etDireccion);
        EditText telefono = rootView.findViewById(R.id.etTelefono);
        EditText contrasena = rootView.findViewById(R.id.etContra);

        View btnCrearCuenta = rootView.findViewById(R.id.btnCrear);

        // Animación del botón
        Animation btnAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_scale);

        btnCrearCuenta.setOnClickListener(v -> {

            // Ejecutar animación del botón
            v.startAnimation(btnAnim);

            // --- VALIDACIÓN DE CAMPOS VACÍOS ---
            if (nombre.getText().toString().trim().isEmpty() ||
                    correo.getText().toString().trim().isEmpty() ||
                    telefono.getText().toString().trim().isEmpty() ||
                    contrasena.getText().toString().trim().isEmpty()) {

                Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return; // Detiene el proceso
            }

            // Si todo está llenado correctamente → ir al siguiente fragmento
            Fragment siguienteFragment = new Pantalla3();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}
