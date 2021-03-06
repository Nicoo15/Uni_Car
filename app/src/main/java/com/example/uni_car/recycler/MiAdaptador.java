package com.example.uni_car.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.uni_car.R;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<com.example.uni_car.recycler.MiAdaptador.MiContenedorDeVistas> {

    private ArrayList<com.example.uni_car.recycler.Post> lista_posts = new ArrayList<>();
    private static View vistaG;

    public MiAdaptador(ArrayList<com.example.uni_car.recycler.Post> lista_posts) {
        this.lista_posts = lista_posts;
    }

    @NonNull
    @Override
    public MiContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false);
        TextView tv_origen = vista.findViewById(R.id.origen);
        TextView tv_destino = vista.findViewById(R.id.destino);

        MiContenedorDeVistas contenerdor = new MiContenedorDeVistas(vista);
        Log.d("contenedor", "creando contenedor de vistas");
        return contenerdor;
    }



    @Override
    public void onBindViewHolder(@NonNull MiContenedorDeVistas holder, int position) {
        com.example.uni_car.recycler.Post c = lista_posts.get(position);
        holder.tv_origen.setText((c.getOrigen()));
        holder.tv_destino.setText(c.getDestino());
        //holder.tv_title;
        Glide.with(vistaG)
                .load(c.getPhoto())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(100)))
                .into(holder.tv_img);

        Log.d("contenedor", "vinculando position" + position);
    }

    @Override
    public int getItemCount() {
        return lista_posts.size();
    }

    public static class MiContenedorDeVistas extends RecyclerView.ViewHolder {
        public TextView tv_origen, tv_destino;
        public ImageView tv_img;

        public MiContenedorDeVistas(View vista) {
            super(vista);
            vistaG = vista;
            this.tv_origen = vista.findViewById(R.id.origen);
            this.tv_destino = vista.findViewById(R.id.destino);
            this.tv_img = vista.findViewById(R.id.imgPost);

        }

    }


}