package com.example.analysisreport.Adapter.DataDetailPakan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Activity.MainActivity;
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
    Toolbar toolbar;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    TextView a1,a2,a3,a4,a5;
    public PostAdapterPakan(@NonNull FirebaseRecyclerOptions<RequestDataPakan> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostViewHolder postViewHolder, final int i, @NonNull final RequestDataPakan requestDataPakan) {
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
                final Button Simpan = postViewBolder.findViewById(R.id.ubutoonsimpanpakan);
                stanggalpakan.setText(requestDataPakan.getTanggalpakan());
                skodepakan.setText(requestDataPakan.getKodepakan());
                sjam6.setText(requestDataPakan.getJam6());
                sjam10.setText(requestDataPakan.getJam10());
                sjam14.setText(requestDataPakan.getJam14());
                sjam18.setText(requestDataPakan.getJam18());
                sjam22.setText(requestDataPakan.getJam22());
                stotalharianpakan.setText(requestDataPakan.getJumlahharian());
                sisicatatan.setText(requestDataPakan.getKeteranganpakan());
                Simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater;
                        sessions = new SharePreference(context.getApplicationContext());
                       // double hitung = jam6+jam10+jam14+jam18+jam22;
                       // stotalharianpakan.setText(String.valueOf(hitung));
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
                        final String nama = sessions.getDatas();
                        final String kolam = sessions.getDetailkolam();
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
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Notice!");
                        alertDialogBuilder.setMessage("Yakin untuk merubah data?")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sjam6.getText().toString();
                                        sjam10.getText().toString();
                                        sjam14.getText().toString();
                                        sjam18.getText().toString();
                                        sjam22.getText().toString();
                                        stotalharianpakan.getText().toString();
                                         final double jam6 = Double.parseDouble(sjam6.getText().toString());
                                        final double jam10 = Double.parseDouble(sjam10.getText().toString());
                                        final double jam14 = Double.parseDouble(sjam14.getText().toString());
                                        final double jam18 = Double.parseDouble(sjam18.getText().toString());
                                        final double jam22 = Double.parseDouble(sjam22.getText().toString());
                                        final double totalharian = Double.parseDouble(stotalharianpakan.getText().toString());
                                        double hitung = jam6+jam10+jam14+jam18+jam22;
                                        String value = String.valueOf(hitung);
                                        String valuesebelumnya = sessions.getPakanfeed();
                                        double svalue = Double.parseDouble(valuesebelumnya);
                                        double hasiltotal = (hitung-totalharian)+svalue;
                                        String valuetotal = String.valueOf(hasiltotal);
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("jumlahharian", value);
                                        map.put("jumlahtotal", valuetotal);
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
                                        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);

                                    }
                                });
                        AlertDialog alertDialog=alertDialogBuilder.create();
                        alertDialog.show();
                        //dialog.cancel();
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
    private void showDialog(){

    }
}
