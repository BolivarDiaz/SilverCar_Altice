package com.example.bolivarrafael.silvercar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bolivarrafael.silvercar.Models.PublishCar;
import com.example.bolivarrafael.silvercar.R;
import com.example.bolivarrafael.silvercar.VercarrosActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterCars extends RecyclerView.Adapter<AdapterCars.CarsViewHolder> {



ArrayList<PublishCar> listacar;
Context mycontext;


    public AdapterCars(ArrayList<PublishCar> listacar,Context mycontext) {

        this.listacar=listacar;
        this.mycontext=mycontext;
    }


    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vercarros,parent,false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarsViewHolder holder, int position) {


        holder.txtmarca.setText(holder.txtmarca.getText()+" "+listacar.get(position).getMarca());
        holder.txtmodelo.setText(holder.txtmodelo.getText()+" "+listacar.get(position).getModelo());
        holder.txtprecio.setText(holder.txtprecio.getText()+" "+listacar.get(position).getPrecio()+" $");
        holder.txtpropietario.setText(holder.txtpropietario.getText()+" "+listacar.get(position).getPropietario());
        holder.txttelefono.setText(holder.txttelefono.getText()+" "+listacar.get(position).getNumerotelefono());


        Glide.with(mycontext).load(listacar.get(position).getImage()).fitCenter().centerCrop().into(holder.foto);


         holder.foto.setImageResource(R.mipmap.ic_launcher_round);

    }



    @Override
    public int getItemCount() {
        return listacar.size();
    }






    public class CarsViewHolder extends RecyclerView.ViewHolder {
        TextView txtmarca,txtmodelo,txtprecio,txtpropietario,txttelefono;
        ImageView foto;

        public CarsViewHolder(View itemView) {
            super(itemView);
            txtmarca= (TextView) itemView.findViewById(R.id.textViewmarca);
            txtmodelo= (TextView) itemView.findViewById(R.id.textViewmodelo);
            txtprecio=(TextView) itemView.findViewById(R.id.textViewprecio);
            txtpropietario= (TextView) itemView.findViewById(R.id.textViewpropietario);
            txttelefono= (TextView) itemView.findViewById(R.id.textViewphone);
            foto= (ImageView) itemView.findViewById(R.id.imageViewcar);
        }
    }


}





