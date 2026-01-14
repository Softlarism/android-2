package com.example.softlarism;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.softlarism.model.Usuarios;
import com.example.softlarism.network.ApiService;
import com.example.softlarism.network.RetrofitClient;

//mira aca lo de retromierda
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new PANTALLAINICIO())
                    .commit();
        }
        obtenerUsuarios();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
    public void obtenerUsuarios(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call <List<Usuarios>> call = apiService.obtenerUsuarios();
        call.enqueue(new Callback<List<Usuarios>>(){
            @Override
            public void onResponse(retrofit2.Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Usuarios> usuarios = response.body();

                    Log.d("API","Conexion exitosa! Total usuarios: " + usuarios.size());
                    Toast.makeText(MainActivity.this, "Conectado Usuarios:"+ usuarios.size(),
                            Toast.LENGTH_SHORT).show();

                    for (Usuarios usuario : usuarios){
                        Log.d("API","Usuario" + usuario.getNombre()+ ""
                        + usuario.getApellido());
                    }
                }else {
                    Log.e("API","Error en respuesta: "+response.code());
                    Toast.makeText(MainActivity.this, "Error"+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Log.e("API","Error de conexion: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
