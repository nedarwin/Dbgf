package com.example.dbg;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatActivity extends Activity {
    int c1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        DBManager dbManager = DBManager.getInstance(this);
        TextView t1 = findViewById(R.id.textView);
        t1.setText(dbManager.gCount()+""+" кол игр");
        TextView g =findViewById(R.id.textView2);
        g.setText(dbManager.maxCount()+""+" макс");
        TextView h =findViewById(R.id.textView3);
        h.setText(dbManager.minCount()+""+" мин");
    }
}
