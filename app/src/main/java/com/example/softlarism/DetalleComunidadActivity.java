package com.example.softlarism;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DetalleComunidadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comunidad);

        // 🔹 Recibir los datos enviados
        String nombre = getIntent().getStringExtra("nombre");
        String localizacion = getIntent().getStringExtra("localizacion");
        String miembros = getIntent().getStringExtra("miembros");

        // 🔹 Mostrar los datos en los TextViews
        //TextView tvNombre = findViewById(R.id.tvNombreComunidad);
        //TextView tvLocalizacion = findViewById(R.id.tvLocalizacion);
        //TextView tvMiembros = findViewById(R.id.tvMiembros);

        //tvNombre.setText(nombre);
        //tvLocalizacion.setText(localizacion);
        //tvMiembros.setText(miembros);
    }
}
