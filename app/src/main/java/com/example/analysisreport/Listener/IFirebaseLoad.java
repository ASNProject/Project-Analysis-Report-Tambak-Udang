package com.example.analysisreport.Listener;

import com.example.analysisreport.Model.Tambak;

import java.util.List;

public interface IFirebaseLoad {
    void onFirebaseLoadSuccess(List<Tambak>tambakList);
    void onFirebaseLoadFailed(String message);
}
