package com.example.alaa.memorygame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> imgs;
    public static ArrayList<level> levels;
    int imgs_id[] = {/*R.drawable.a,*/ R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.b,
            R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v,
            R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.aa, R.drawable.bb,
            R.drawable.cc, R.drawable.dd, R.drawable.ee, R.drawable.ff};
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgs = new ArrayList<>();
        for (int i = 0; i < imgs_id.length; i++) {
            imgs.add(imgs_id[i]);
        }
        Collections.shuffle(imgs);
        levels = new ArrayList<>();
        File database = getApplication().getDatabasePath(databaseHelper.DBNAME);
        if (database.exists() == false) {
            databaseHelper.getReadableDatabase();
            if (CopyDatabase(this)) {
                Toast.makeText(this, "تم نسخ بيانات قاعدة البيانات بنجاح", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "فشل نسخ بيانات قاعدة البيانات", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        levels = databaseHelper.get_All_IDS();
//        for (int i = 0; i < levels.size(); i++) {
//            Log.e("levels", levels.get(i).getId()+"");
//        }
        new CountDownTimer(000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Main_screen fragment1 = new Main_screen();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Main_act, fragment1);
                transaction.commit();
            }
        }.start();
    }

    public boolean CopyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(databaseHelper.DBNAME);
            String outFileName = databaseHelper.DBLOCATION + databaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
