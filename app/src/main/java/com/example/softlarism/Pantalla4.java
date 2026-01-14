package com.example.softlarism;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class Pantalla4 extends Fragment {

    EditText correo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pantalla4, container, false);

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

        correo = view.findViewById(R.id.correop4);
        View btnSiguiente = view.findViewById(R.id.botoncontinuarp4);

        btnSiguiente.setOnClickListener(v -> {

            // ANIMACIÓN
            ScaleAnimation anim = new ScaleAnimation(
                    1f, 0.9f,
                    1f, 0.9f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );
            anim.setDuration(120);
            anim.setFillAfter(true);

            ScaleAnimation animBack = new ScaleAnimation(
                    0.9f, 1f,
                    0.9f, 1f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );
            animBack.setDuration(120);
            animBack.setFillAfter(true);

            btnSiguiente.startAnimation(anim);
            btnSiguiente.postDelayed(() -> btnSiguiente.startAnimation(animBack), 120);

            // VALIDACIONES

            String correoTxt = correo.getText().toString().trim();

            if (correoTxt.isEmpty()) {
                Toast.makeText(getActivity(), "Ingresa tu correo", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(correoTxt).matches()) {
                Toast.makeText(getActivity(), "Correo no válido", Toast.LENGTH_SHORT).show();
                return;
            }

            // CAMBIAR DE PANTALLA
            Fragment siguienteFragment = new Pantalla5();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguienteFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
