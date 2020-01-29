package com.bahasadaerahapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bahasadaerahapp.R;
import com.bahasadaerahapp.model.Dictionary;
import com.bahasadaerahapp.model.DictionaryLanguage;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DictionaryListAdapter extends RecyclerView.Adapter<DictionaryListAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<Dictionary> listLanguage;

    public DictionaryListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Dictionary> getListDictionary() {
        return listLanguage;
    }

    public void setListMountain(ArrayList<Dictionary> listLanguage) {
        this.listLanguage = listLanguage;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dictionary, parent, false);
        return new ListViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.tvIndo.setText(getListDictionary().get(position).getBhsIndo());
        holder.tvDaerah.setText(getListDictionary().get(position).getBhsDaerah());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, getListDictionary().get(position).getBhsIndo() + " : " +
                        getListDictionary().get(position).getBhsDaerah(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListDictionary().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndo, tvDaerah;
        LinearLayout linearLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIndo = itemView.findViewById(R.id.tv_bhsIndo);
            tvDaerah = itemView.findViewById(R.id.tv_bhsDaerah);
            linearLayout = itemView.findViewById(R.id.ll_listLanguage);
        }
    }
}
