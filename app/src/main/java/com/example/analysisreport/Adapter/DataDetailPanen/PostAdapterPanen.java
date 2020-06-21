package com.example.analysisreport.Adapter.DataDetailPanen;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.RequestDataPanen;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PostAdapterPanen extends FirebaseRecyclerAdapter<RequestDataPanen, PostAdapterPanen.PostViewHolder> {

    private Context context;
    SharePreference sessions;
    Toolbar toolbar;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    public PostAdapterPanen(@NonNull FirebaseRecyclerOptions<RequestDataPanen> options, Context context) {
        super(options);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i, @NonNull RequestDataPanen requestDataPanen) {
        postViewHolder.stanggalpanen.setText(requestDataPanen.getTanggalpanen());
        postViewHolder.sdoc.setText(requestDataPanen.getDoc());
        postViewHolder.stonase.setText(requestDataPanen.getTonase());
        postViewHolder.abw.setText(requestDataPanen.getAbw());
        postViewHolder.size.setText(requestDataPanen.getSize());
        postViewHolder.populasi.setText(requestDataPanen.getPopulasipanen());
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_panen, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
            TextView stanggalpanen;
            TextView sdoc;
            TextView stonase;
            TextView abw;
            TextView size;
            TextView populasi;
            TextView edit;
            TextView delete;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            stanggalpanen = itemView.findViewById(R.id.stanggalanpanen);
            sdoc = itemView.findViewById(R.id.nilaidoc);
            stonase = itemView.findViewById(R.id.nilaitonase);
            abw = itemView.findViewById(R.id.nilaiabw);
            size = itemView.findViewById(R.id.nilaisize);
            populasi = itemView.findViewById(R.id.nilaipopulasi);
            edit = itemView.findViewById(R.id.editpanen);
            delete = itemView.findViewById(R.id.deletepanen);
        }
    }
}
