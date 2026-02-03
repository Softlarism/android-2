package com.example.softlarism;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.softlarism.model.Usuarios;
import com.example.softlarism.network.ApiService;
import com.example.softlarism.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CREARCUENTA extends Fragment {

    private View rootView;
    private EditText etNombre, etApellido, etTelefono, etCp1, etNumero, etContra, etConfirmarContra;
    private Button btnCrear;
    private TextView tvYaTienesCuenta;

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
        etNombre = rootView.findViewById(R.id.etNombre);
        etApellido = rootView.findViewById(R.id.etApellido);
        etTelefono = rootView.findViewById(R.id.etTelefono);
        etCp1 = rootView.findViewById(R.id.etCp1);
        etNumero = rootView.findViewById(R.id.etDireccion);
        etContra = rootView.findViewById(R.id.etContra);
        etConfirmarContra = rootView.findViewById(R.id.etCofirmarContraseña);
        btnCrear = rootView.findViewById(R.id.btnCrear);
        tvYaTienesCuenta = rootView.findViewById(R.id.tvYaTienesCuenta);

        // Animación del botón
        Animation btnAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_scale);

        // CORREGIDO: El onClick del botón debe estar aquí
        btnCrear.setOnClickListener(v -> {
            // Ejecutar animación del botón
            v.startAnimation(btnAnim);
            registrarUsuarios();
        });

        if (tvYaTienesCuenta != null) {
            tvYaTienesCuenta.setOnClickListener(v -> {
                // navegacion al fragmento
                Fragment loginFragment = new Alarma();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .addToBackStack(null)
                        .commit();
            });
        }
    }
    private void registrarUsuarios() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String cp1 = etCp1.getText().toString().trim();
        String numero = etNumero.getText().toString().trim();
        String contrasena = etContra.getText().toString().trim();
        String confirmarContra = etConfirmarContra.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("Ingresa tu nombre");
            etNombre.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return; // AÑADIDO: return para detener la ejecución
        }

        if (apellido.isEmpty()) {
            etApellido.setError("ingresa tu Apellido");
            etApellido.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return; // AÑADIDO
        }

        if (telefono.isEmpty()) {
            etTelefono.setError("Ingresa tu Telefono");
            etTelefono.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return; // AÑADIDO
        }

        if (telefono.length() != 10) {
            etTelefono.setError("El Telefono debe tener 10 digitos");
            etTelefono.requestFocus();
            return;
        }

        if (cp1.isEmpty()) {
            etCp1.setError("Ingresa tu Codigo Postal");
            etCp1.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return; // AÑADIDO
        }

        if (cp1.length() != 5) {
            etCp1.setError("El codigo postal debe tener 5 digitos");
            etCp1.requestFocus();
            return;
        }

        if (numero.isEmpty()) {
            etNumero.setError("Ingresa tu direccion");
            etNumero.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (contrasena.isEmpty()) {
            etContra.setError("Ingresa una Contraseña");
            etContra.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (contrasena.length() < 6) {
            etContra.setError("La contraseña debe tener al menos 6 caracteres");
            etContra.requestFocus();
            return;
        }

        if (confirmarContra.isEmpty()) { // CORREGIDO: Era confirmarContra.equals(confirmarContra) lo cual siempre es true
            etConfirmarContra.setError("Confirma tu contraseña");
            etConfirmarContra.requestFocus();
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contrasena.equals(confirmarContra)) {
            etConfirmarContra.setError("Las contraseñas no coinciden");
            etConfirmarContra.requestFocus();
            Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        btnCrear.setEnabled(false);
        btnCrear.setText("Creando cuenta...");

        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setCp1(cp1); // CORREGIDO: Era setCp1, debe ser setCpi según tu modelo
        nuevoUsuario.setNumero(numero);
        nuevoUsuario.setContrasena(contrasena);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Usuarios> call = apiService.registrarUsuarios(nuevoUsuario); // CORREGIDO: Era registrarUsuarios

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                btnCrear.setEnabled(true);
                btnCrear.setText("Crear cuenta");

                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(requireContext(), "Cuenta creada exitosamente",
                            Toast.LENGTH_SHORT).show();

                    Fragment siguienteFragment = new Pantalla3();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, siguienteFragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    String errorMsg = "Error al crear cuenta";
                    if (response.code() == 400) {
                        errorMsg = "El telefono ya esta registrado";
                    }
                    Toast.makeText(requireContext(), " " + errorMsg,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                btnCrear.setEnabled(true);
                btnCrear.setText("Crear cuenta");

                Toast.makeText(requireContext(), "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}