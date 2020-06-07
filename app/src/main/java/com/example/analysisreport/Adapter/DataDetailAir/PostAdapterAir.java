package com.example.analysisreport.Adapter.DataDetailAir;

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

import com.example.analysisreport.Model.RequestDataAir;
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

public class PostAdapterAir extends FirebaseRecyclerAdapter<RequestDataAir, PostAdapterAir.PostViewBolder> {
    private Context context;
    SharePreference sessions;
    public PostAdapterAir(@NonNull FirebaseRecyclerOptions<RequestDataAir> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostViewBolder postViewBolder, final int i, @NonNull final RequestDataAir requestDataAir) {

        postViewBolder.stanggal.setText(requestDataAir.getTanggalairs());
        postViewBolder.stinggiair.setText(requestDataAir.getTinggiairs());
        postViewBolder.sdopagi.setText(requestDataAir.getDopagi());
        postViewBolder.sdomalam.setText(requestDataAir.getDomalam());
        postViewBolder.sphpgi.setText(requestDataAir.getPhpagis());
        postViewBolder.sphmalm.setText(requestDataAir.getPhmalams());
        postViewBolder.ssuhu.setText(requestDataAir.getSuhus());
        postViewBolder.sca.setText(requestDataAir.getCas());
        postViewBolder.smg.setText(requestDataAir.getMgs());
        postViewBolder.sno2.setText(requestDataAir.getNo2s());
        postViewBolder.sno3.setText(requestDataAir.getNo3s());
        postViewBolder.snh3.setText(requestDataAir.getNh3s());

        postViewBolder.sedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.editair))
                        .setGravity(Gravity.CENTER)
                        .setExpanded(true, 1500)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View postViewHolder = (LinearLayout)dialog.getHolderView();

                final EditText stanggal = postViewHolder.findViewById(R.id.uptanggalair);
                final EditText stinggiair = postViewHolder.findViewById(R.id.uptinggiair);
                final EditText sdopagi = postViewHolder.findViewById(R.id.updopagi);
                final EditText sdomalam = postViewHolder.findViewById(R.id.updomalam);
                final EditText sphpgi = postViewHolder.findViewById(R.id.upphpagi);
                final EditText sphmalm = postViewHolder.findViewById(R.id.upphmalam);
                final EditText skecerahan = postViewHolder.findViewById(R.id.upkecerahan);
                final EditText ssuhu = postViewHolder.findViewById(R.id.upsuhu);
                final EditText sca =postViewHolder.findViewById(R.id.upca);
                final EditText smg = postViewHolder.findViewById(R.id.upmg);
                final EditText sno2 = postViewHolder.findViewById(R.id.upno2);
                final EditText sno3 =postViewHolder.findViewById(R.id.upno3);
                final EditText snh3 = postViewHolder.findViewById(R.id.upnh3);
                final EditText salkalinitas = postViewHolder.findViewById(R.id.upalkalinitas);
                Button Simpan = postViewHolder.findViewById(R.id.upbutoonsimpanair);

                stanggal.setText(requestDataAir.getTanggalairs());
                stinggiair.setText(requestDataAir.getTinggiairs());
                sdopagi.setText(requestDataAir.getDopagi());
                sdomalam.setText(requestDataAir.getDomalam());
                sphpgi.setText(requestDataAir.getPhpagis());
                sphmalm.setText(requestDataAir.getPhmalams());
                skecerahan.setText(requestDataAir.getKecerahans());
                ssuhu.setText(requestDataAir.getSuhus());
                sca.setText(requestDataAir.getCas());
                smg.setText(requestDataAir.getMgs());
                sno2.setText(requestDataAir.getNo2s());
                sno3.setText(requestDataAir.getNo3s());
                snh3.setText(requestDataAir.getNh3s());
                salkalinitas.setText(requestDataAir.getAlkalinitass());
                Simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map =new HashMap<>();
                        map.put("tanggalairs", stanggal.getText().toString());
                        map.put("tinggiairs", stinggiair.getText().toString());
                        map.put("dopagi", sdopagi.getText().toString());
                        map.put("domalam", sdomalam.getText().toString());
                        map.put("phpagis", sphpgi.getText().toString());
                        map.put("phmalams", sphmalm.getText().toString());
                        map.put("kecerahans", skecerahan.getText().toString());
                        map.put("suhus", ssuhu.getText().toString());
                        map.put("cas", sca.getText().toString());
                        map.put("mgs", smg.getText().toString());
                        map.put("no2s", sno2.getText().toString());
                        map.put("no3s", sno3.getText().toString());
                        map.put("nh3s", snh3.getText().toString());
                        map.put("alkalinitass", salkalinitas.getText().toString());
                        SharePreference sessions;
                        sessions = new SharePreference(context.getApplicationContext() );
                        String nama = sessions.getDatas();
                        String kolam = sessions.getDetailkolam();
                        FirebaseDatabase.getInstance().getReference().child(nama)
                                .child(kolam).child("Air").child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialog.dismiss();
                                    }
                                });
                        FirebaseDatabase.getInstance().getReference().child(nama).child(kolam).child("Airupdate")
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                    }
                });
                dialog.show();
            }
        });

    }

    @NonNull
    @Override
    public PostViewBolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.item_detail_air, parent, false);
       return new PostViewBolder(view);
    }

    class PostViewBolder extends RecyclerView.ViewHolder{

         TextView stanggal;
         TextView sedit;
         TextView stinggiair;
         TextView sdopagi;
         TextView sdomalam;
         TextView sphpgi;
         TextView sphmalm;
         TextView ssuhu;
         TextView sca;
         TextView smg;
         TextView sno2;
         TextView sno3;
         TextView snh3;
         TextView salkalinitas;

        public PostViewBolder(@NonNull View itemView) {
            super(itemView);
            stanggal = itemView.findViewById(R.id.tanggalan);
            sedit = itemView.findViewById(R.id.edit);
            stinggiair = itemView.findViewById(R.id.tinggiair);
            sdopagi = itemView.findViewById(R.id.dopagi);
            sdomalam = itemView.findViewById(R.id.domalam);
            sphpgi = itemView.findViewById(R.id.phpagi);
            sphmalm = itemView.findViewById(R.id.phsore);
            ssuhu = itemView.findViewById(R.id.suuhu);
            sca =itemView.findViewById(R.id.ca);
            smg = itemView.findViewById(R.id.mg);
            sno2 = itemView.findViewById(R.id.no2);
            sno3 =itemView.findViewById(R.id.no3);
            snh3 = itemView.findViewById(R.id.nh3);
            salkalinitas = itemView.findViewById(R.id.alkalinitas);
        }
    }
}
