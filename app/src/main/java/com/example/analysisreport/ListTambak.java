package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.analysisreport.Adapter.tambakHolder;
import com.example.analysisreport.Model.LISTTambak;
import com.example.analysisreport.Model.RequestTambahTambak;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ListTambak extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter<LISTTambak, tambakHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private TextView lis, isi;
    Toolbar toolbar;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txtNamaTambak, txtLokasi;
    ImageView tbhTambak;
    SharePreference sessions;
    private String KEY_NAME ="TambahTambak";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tambak);

        tbhTambak = findViewById(R.id.tambahtambak);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_tambak);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setStackFromEnd(true);
        mManager.setReverseLayout(true);
        mRecycler.setLayoutManager(mManager);

        hkh();

        tbhTambak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm();
            }
        });


    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(ListTambak.this);
        dialog = new AlertDialog.Builder(this, R.style.TambahTambak);
        inflater = getLayoutInflater();
        dialogView= inflater.inflate(R.layout.tambah_tambak, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Masukkan Data");

        txtNamaTambak = (EditText) dialogView.findViewById(R.id.ntambak);
        txtLokasi = (EditText) dialogView.findViewById(R.id.nlokasi);

        datakosong();

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = txtNamaTambak.getText().toString();
                String lokasi = txtLokasi.getText().toString();

                //Masukkan program databse firebase
                if (nama.equals("")){
                    txtNamaTambak.setError("Masukkan Nama Tambak");
                    txtNamaTambak.requestFocus();
                    return;
                }
                if (lokasi.equals("")){
                    txtLokasi.setError("Masukkan Lokasi");
                    return;
                }
                submitTambak(new RequestTambahTambak(
                        nama.toLowerCase(),
                        lokasi.toLowerCase()));

                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
            dialog.show();
    }




    private void datakosong() {
        txtNamaTambak.setText(null);
        txtLokasi.setText(null);
    }

    private void submitTambak(RequestTambahTambak requestTambahTambak) {
        final String nama = txtNamaTambak.getText().toString();
        String lokasi = txtLokasi.getText().toString();
        String data = lis.getText().toString();
        mDatabase.child("Data User")
                .child(data)
                .child("Daftar Tambak")
                .push()
                .setValue(requestTambahTambak)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        txtNamaTambak.setText("");
                        txtLokasi.setText("");
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        String data = lis.getText().toString();
        Query query = mDatabase.child("Data User").child(data).child("Daftar Tambak");
        return query;
    }

    private void hkh(){
        final FirebaseUser user = mAuth.getCurrentUser();
        lis = findViewById(R.id.list);
        if(user !=null){
            if (user.getDisplayName() !=null){
                lis.setText(user.getDisplayName());
                final String data = lis.getText().toString();

                Query query = getQuery(mDatabase);
                FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<LISTTambak>()
                        .setQuery(query, LISTTambak.class)
                        .build();

                mAdapter = new FirebaseRecyclerAdapter<LISTTambak, tambakHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull tambakHolder tambakHolder, int i, @NonNull LISTTambak listTambak) {
                        tambakHolder.LTAMBAK(listTambak, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isi = findViewById(R.id.isi);
                                lis = findViewById(R.id.list);
                                String nis = lis.getText().toString();
                                String isin = isi.getText().toString();
                                String hah = String.valueOf(nis+isin);
                                sessions = new SharePreference(ListTambak.this.getApplicationContext());
                                sessions.setDatas(hah);
                                //Toast.makeText(this, "datanya adalah"+hah, Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(ListTambak.this, MainActivity.class);
                                startActivity(in);
                                finish();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public tambakHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                        return new tambakHolder(inflater.inflate(R.layout.item_list_tambak,parent, false));
                    }
                };

                mAdapter.notifyDataSetChanged();
                mRecycler.setAdapter(mAdapter);
            }
        }
    }

}
