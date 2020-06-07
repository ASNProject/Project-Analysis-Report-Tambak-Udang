package com.example.analysisreport.Adapter.DataDetailPakan;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class PostAdapterPakan extends FirebaseRecyclerAdapter<RequestDataPakan, PostAdapterPakan.PostViewHolder> {

    private Context context;
    SharePreference sessions;
    public PostAdapterPakan(@NonNull FirebaseRecyclerOptions<RequestDataPakan> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, final int i, @NonNull final RequestDataPakan requestDataPakan) {
        postViewHolder.stanggalpakan.setText(requestDataPakan.getTanggalpakan());
        postViewHolder.skodepakan.setText(requestDataPakan.getKodepakan());
        postViewHolder.sjam6.setText(requestDataPakan.getJam6());
        postViewHolder.sjam10.setText(requestDataPakan.getJam10());
        postViewHolder.sjam14.setText(requestDataPakan.getJam14());
        postViewHolder.sjam18.setText(requestDataPakan.getJam18());
        postViewHolder.sjam22.setText(requestDataPakan.getJam22());
        postViewHolder.stotalharianpakan.setText(requestDataPakan.getJumlahharian());
        postViewHolder.stotalpakansemua.setText(requestDataPakan.getJumlahtotal());
        postViewHolder.sketeranganpakan.setText(requestDataPakan.getKeteranganpakan());
        postViewHolder.seditpakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.detailpakan))
                        .setGravity(Gravity.CENTER)
                        .setExpanded(true,1500)
                        .create();
                View postViewBolder = (LinearLayout)dialogPlus.getHolderView();
                final EditText stanggalpakan = postViewBolder.findViewById(R.id.utanggalpakan);
                final EditText skodepakan = postViewBolder.findViewById(R.id.ukodepakan);
                final EditText sjam6 = postViewBolder.findViewById(R.id.ujam6);
                final EditText sjam10 = postViewBolder.findViewById(R.id.ujam10);
                final EditText sjam14 = postViewBolder.findViewById(R.id.ujam14);
                final EditText sjam18 = postViewBolder.findViewById(R.id.ujam18);
                final EditText sjam22 = postViewBolder.findViewById(R.id.ujam22);
                final EditText sisicatatan = postViewBolder.findViewById(R.id.uisicatatan);
                final TextView stotalharianpakan = postViewBolder.findViewById(R.id.ujumlahharian);
                final TextView stotalpakansemua = postViewBolder.findViewById(R.id.utotal);
                Button Simpan = postViewBolder.findViewById(R.id.ubutoonsimpanpakan);
                stanggalpakan.setText(requestDataPakan.getTanggalpakan());
                skodepakan.setText(requestDataPakan.getKodepakan());
                sjam6.setText(requestDataPakan.getJam6());
                sjam10.setText(requestDataPakan.getJam10());
                sjam14.setText(requestDataPakan.getJam14());
                sjam18.setText(requestDataPakan.getJam18());
                sjam22.setText(requestDataPakan.getJam22());
                final int jam6 = Integer.parseInt(sjam6.getText().toString());
                final int jam10 = Integer.parseInt(sjam10.getText().toString());
                final int jam14 = Integer.parseInt(sjam14.getText().toString());
                final int jam18 = Integer.parseInt(sjam18.getText().toString());
                final int jam22 = Integer.parseInt(sjam22.getText().toString());

                //stotalharianpakan.setText(requestDataPakan.getJumlahharian());
                stotalpakansemua.setText(requestDataPakan.getJumlahtotal());
                sisicatatan.setText(requestDataPakan.getKeteranganpakan());
                Simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int jumlahharian = jam6+jam10+jam14+jam18+jam22;
                        stotalharianpakan.setText(String.valueOf(jumlahharian));
                        Map<String, Object> map = new HashMap<>();
                        map.put("tanggalpakan", stanggalpakan.getText().toString());
                        map.put("kodepakan", skodepakan.getText().toString());
                        map.put("jam6", sjam6.getText().toString());
                        map.put("jam10", sjam10.getText().toString());
                        map.put("jam14", sjam14.getText().toString());
                        map.put("jam18", sjam18.getText().toString());
                        map.put("jam22", sjam22.getText().toString());
                        map.put("keteranganpakan", sisicatatan.getText().toString());
                        map.put("jumlahharian", stotalharianpakan.getText().toString());
                        map.put("jumlahtotal", stotalpakansemua.getText().toString());
                        sessions = new SharePreference(context.getApplicationContext());
                        String nama = sessions.getDatas();
                        String kolam = sessions.getDetailkolam();
                        FirebaseDatabase.getInstance().getReference().child(nama)
                                .child(kolam).child("Pakan").child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                     dialogPlus.dismiss();
                                    }
                                });
                        FirebaseDatabase.getInstance().getReference()
                                .child(nama).child(kolam).child("Pakanupdate")
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                    }
                });

                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_pakan, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView stanggalpakan;
        TextView seditpakan;
        TextView skodepakan;
        TextView sjam6;
        TextView sjam10;
        TextView sjam14;
        TextView sjam18;
        TextView sjam22;
        TextView stotalharianpakan;
        TextView stotalpakansemua;
        TextView sketeranganpakan;
        Button sbuttongambar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            stanggalpakan = itemView.findViewById(R.id.tanggalanpakan);
            seditpakan = itemView.findViewById(R.id.editpakan);
            skodepakan = itemView.findViewById(R.id.kodepakan);
            sjam6 = itemView.findViewById(R.id.jam6);
            sjam10 = itemView.findViewById(R.id.jam10);
            sjam14 = itemView.findViewById(R.id.jam14);
            sjam18 = itemView.findViewById(R.id.jam18);
            sjam22 = itemView.findViewById(R.id.jam22);
            stotalharianpakan = itemView.findViewById(R.id.totalharianpakan);
            stotalpakansemua = itemView.findViewById(R.id.totalpakansemua);
            sketeranganpakan = itemView.findViewById(R.id.keteranganpakan);
            sbuttongambar = itemView.findViewById(R.id.buttongambar);
        }
    }
}
