package com.bahasadaerahapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bahasadaerahapp.R;
import com.bahasadaerahapp.kamus.KamusActivity;
import com.bahasadaerahapp.model.DictionaryLanguage;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DictionaryLanguageList extends RecyclerView.Adapter<DictionaryLanguageList.ListViewHolder> {
    private Context context;
    private ArrayList<DictionaryLanguage> listLanguage;


    public DictionaryLanguageList(Context context) {
        this.context = context;
    }

    public ArrayList<DictionaryLanguage> getListLanguage() {
        return listLanguage;
    }

    public void setListMountain(ArrayList<DictionaryLanguage> listLanguage) {
        this.listLanguage = listLanguage;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bahasa, parent, false);
        return new ListViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.tvName.setText(getListLanguage().get(position).getName());
        Glide.with(context).load(getListLanguage().get(position).getPhoto()).into(holder.imgList);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivity = new Intent(context, KamusActivity.class);
         //       detailActivity.putExtra(KamusActivity.EXTRA_MOUNTAIN, listLanguage.get(position));
                context.startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListLanguage().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgList;
        LinearLayout linearLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_listname);
            imgList = itemView.findViewById(R.id.iv_listimage);
            linearLayout = itemView.findViewById(R.id.ll_listLanguage);
        }
    }
}
