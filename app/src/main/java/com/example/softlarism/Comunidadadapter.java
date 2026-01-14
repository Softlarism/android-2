package com.example.softlarism;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Comunidadadapter extends RecyclerView.Adapter<Comunidadadapter.ViewHolder> {

    private List<ComunidadItem> comunidadList;

    public Comunidadadapter(List<ComunidadItem> comunidadList) {
        this.comunidadList = comunidadList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvLocalizacion, tvMiembros;

        public ViewHolder(View itemView) {
            super(itemView);
            //tvNombre = itemView.findViewById(R.id.tvNombre);
            //tvLocalizacion = itemView.findViewById(R.id.tvLocalizacion);
            //tvMiembros = itemView.findViewById(R.id.tvMiembros);
        }
    }

    @NonNull
    @Override
    public Comunidadadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); //esta linea se modifica para cambiar de .xml
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull Comunidadadapter.ViewHolder holder, int position) {
        ComunidadItem item = comunidadList.get(position);
        holder.tvNombre.setText(item.getNombre());
        holder.tvLocalizacion.setText(item.getLocalizacion());
        holder.tvMiembros.setText(item.getMiembros());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleComunidadActivity.class);
            // Puedes enviar datos si quieres
            intent.putExtra("nombre", item.getNombre());
            intent.putExtra("localizacion", item.getLocalizacion());
            intent.putExtra("miembros", item.getMiembros());
            v.getContext().startActivity(intent);
        })
        ;}

    @Override
    public int getItemCount() {
        return 0;
    }
}
