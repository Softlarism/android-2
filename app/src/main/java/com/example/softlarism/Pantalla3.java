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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class Pantalla3 extends Fragment {

    public Pantalla3() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pantalla3, container, false);

        // Ajuste de pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ================ ANIMACIÓN AL CARGAR EL FRAGMENTO ================
        view.post(() -> {
            Animation fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
            view.startAnimation(fadeIn);
        });

        // ================ REFERENCIAS ================
        EditText nombre = view.findViewById(R.id.nombrep3);
        EditText contrasena = view.findViewById(R.id.contraseñap3);
        View btnSiguiente = view.findViewById(R.id.botonSesion);
        View btnOlvidaste = view.findViewById(R.id.olvidasteCboton);
        View btnCCuenta = view.findViewById(R.id.ccuenta);

        // Animación de botones (rápida)
        Animation btnAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
        btnAnim.setDuration(120);

        // ================ BOTÓN INICIAR SESIÓN =================
        btnSiguiente.setOnClickListener(v -> {

            v.startAnimation(btnAnim); // animación

            // VALIDACIÓN CAMPOS VACÍOS
            if (nombre.getText().toString().trim().isEmpty() ||
                    contrasena.getText().toString().trim().isEmpty()) {

                Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // NAVEGAR A ALARMA
            Fragment siguienteFragment = new Alarma();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // ================ OLVIDASTE TU CONTRASEÑA =================
        btnOlvidaste.setOnClickListener(v -> {

            v.startAnimation(btnAnim); // animación

            Fragment siguienteFragment = new Pantalla4();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // ================ CREAR CUENTA =================
        btnCCuenta.setOnClickListener(v -> {

            v.startAnimation(btnAnim); // animación

            Fragment siguienteFragment = new CREARCUENTA();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}
