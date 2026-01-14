package com.example.softlarism;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PANTALLAINICIO extends Fragment {

    private View rootView;
    private final long DELAY_MS = 1000; // 1 segundo

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.pantallainicio, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Espera 1 segundo, luego hace fade out y reemplaza por CrearCuenta
        new Handler().postDelayed(() -> {
            // Carga animación fade out
            Animation fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // Al terminar la animación reemplazamos el fragmento
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            // Puedes usar animaciones custom en la transacción si quieres
                            .replace(R.id.fragment_container, new Pantalla3())
                            .commitAllowingStateLoss();
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
            rootView.startAnimation(fadeOut);
        }, DELAY_MS);
    }
}
