package com.carmona.climas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.ViewHolder> {
    private ArrayList<List> mDataset;
    private Context mContext;

    public interface PostRVAdapterListener{
        void OnItemClicked(Climas aPost);
    }

    public PostRVAdapter(Context context, ArrayList<List> climas){
        mDataset = climas;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_climas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //final Clima aClima = mDataset.get(position);

        final List aClimas = mDataset.get(position);

        //final City city  = mDataset2.getName();
        /*holder.txt_fecha.setText(aClima.getDt_txt());
        holder.txt_tempMax.setText(""+aClima.getTemp_max());
        holder.txt_tempMin.setText(""+aClima.getTemp_min());
        holder.txt_descripcion.setText(""+aClima.getDescription());*/
        holder.txt_fecha.setText("Fecha:"+aClimas.getDtTxt());
        holder.txt_tempMax.setText("Temp Max: "+aClimas.getMain().getTempMax());
        holder.txt_tempMin.setText("Temp Min:"+aClimas.getMain().getTempMin());
        holder.txt_descripcion.setText("Tipo: "+aClimas.getWeather().get(0).getDescription());

        /*holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof PostRVAdapterListener) {
                    ((PostRVAdapterListener) mContext).OnItemClicked(aClima);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView txt_fecha;
        TextView txt_tempMin;
        TextView txt_tempMax;
        TextView txt_descripcion;
        TextView txt_city;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            txt_fecha = (TextView) itemView.findViewById(R.id.txt_fecha);
            txt_tempMin = (TextView) itemView.findViewById(R.id.txt_tempMin);
            txt_tempMax = (TextView) itemView.findViewById(R.id.txt_tempMax);
            txt_descripcion = (TextView) itemView.findViewById(R.id.txt_descripcion);
            txt_city = (TextView) itemView.findViewById(R.id.txt_city);
        }
    }
}
