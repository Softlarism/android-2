package com.example.softlarism;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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

import com.example.softlarism.model.LoginRequest;
import com.example.softlarism.model.Usuarios;
import com.example.softlarism.network.ApiService;
import com.example.softlarism.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pantalla3 extends Fragment {

    private EditText etTelefono,etContrasena;
    private View btnIniciarSesion;

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
        etTelefono = view.findViewById(R.id.telefonop3);
        etContrasena = view.findViewById(R.id.contrasenap3);
        btnIniciarSesion = view.findViewById(R.id.botonSesion);
        View btnOlvidaste = view.findViewById(R.id.olvidasteCboton);
        View btnCCuenta = view.findViewById(R.id.ccuenta);

        //verificacion de sesion iniciada
        verificarSesion();


        // Animación de botones (rápida)
        Animation btnAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
        btnAnim.setDuration(120);

        // ================ BOTÓN INICIAR SESIÓN =================
        btnIniciarSesion.setOnClickListener(v -> {

            v.startAnimation(btnAnim); // animación

            iniciarSesion();

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

    private void verificarSesion() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("MiApp",MODE_PRIVATE);
        long userId = prefs.getLong("userId", -1);

        if (userId != -1){
            irAPantallaPrincipal();
        }
    }
    private void iniciarSesion(){
        String telefono = etTelefono.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        //Validacion de los campos que esten vacios xdd

        if (telefono.isEmpty()){
            etTelefono.setError("Ingresa tu Telefono");
            etTelefono.requestFocus();
            Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contrasena.isEmpty()){
            etContrasena.setError("Ingresa tu Contraseña");
            etContrasena.requestFocus();
            Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        btnIniciarSesion.setEnabled(false);

        LoginRequest loginRequest = new LoginRequest(telefono,contrasena);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Usuarios> call = apiService.login(loginRequest);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                btnIniciarSesion.setEnabled(true);

                if (response.isSuccessful() && response.body() != null){
                    Usuarios usuarios = response.body();

                    SharedPreferences prefs = requireActivity().getSharedPreferences("MiApp",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("userId", usuarios.getId_usuario());
                    editor.putString("userName",usuarios.getNombre());
                    editor.putString("userPhone",usuarios.getTelefono());
                    editor.apply();

                    Toast.makeText(requireContext(), "Bienvenido",
                            Toast.LENGTH_SHORT).show();
                    
                    irAPantallaPrincipal();
                }else {
                    Toast.makeText(requireContext(), "Telefono o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {

                btnIniciarSesion.setEnabled(true);
                Toast.makeText(requireContext(), "Error de conexion: "+ t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irAPantallaPrincipal(){
        Fragment siguienteFragment = new Alarma();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,siguienteFragment)
                .commit();
    }

}
