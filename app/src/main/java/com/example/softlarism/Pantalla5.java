package com.example.softlarism;

import android.os.Bundle;
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

public class Pantalla5 extends Fragment {

    EditText n1, n2, n3, n4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pantalla5, container, false);

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

        n1 = view.findViewById(R.id.editText1);
        n2 = view.findViewById(R.id.editText2);
        n3 = view.findViewById(R.id.editText3);
        n4 = view.findViewById(R.id.editText4);

        View btnValidar = view.findViewById(R.id.botonvalidar);

        btnValidar.setOnClickListener(v -> {

            // ANIMACIÓN
            ScaleAnimation anim = new ScaleAnimation(
                    1f, 0.9f,   // X: normal → más pequeño
                    1f, 0.9f,   // Y
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );
            anim.setDuration(120);
            anim.setFillAfter(true);

            // Regresa a tamaño original
            ScaleAnimation animBack = new ScaleAnimation(
                    0.9f, 1f,
                    0.9f, 1f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );
            animBack.setDuration(120);
            anim.setFillAfter(true);

            btnValidar.startAnimation(anim);
            btnValidar.postDelayed(() -> btnValidar.startAnimation(animBack), 120);

            // VALIDACIÓN
            if (n1.getText().toString().trim().isEmpty() ||
                    n2.getText().toString().trim().isEmpty() ||
                    n3.getText().toString().trim().isEmpty() ||
                    n4.getText().toString().trim().isEmpty()) {

                Toast.makeText(getActivity(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return; // No avanza
            }

            // NAVEGACIÓN
            Fragment siguiente = new Alarma();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, siguiente)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
