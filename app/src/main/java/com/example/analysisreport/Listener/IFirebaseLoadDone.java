package com.example.analysisreport.Listener;

import com.example.analysisreport.Model.Report;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Report>reportList);
    void onFirebaseLoadFailed(String message);
}
