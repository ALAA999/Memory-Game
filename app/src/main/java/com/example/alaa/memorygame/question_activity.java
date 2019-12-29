package com.example.alaa.memorygame;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class question_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_activity);
        Intent intent = getIntent();
        int id = intent.getIntExtra("question_id", 0);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        question_fragment fragment1 = new question_fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.question_act, fragment1);
        fragment1.setArguments(bundle);
        transaction.commit();
    }
}
