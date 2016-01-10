package xyz.davidng.dongaexchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {
    private ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        lvMain = (ListView)findViewById(R.id.lvMain);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new FetchData(MainActivity.this, R.layout.custom_layout, lvMain)
                        .execute("http://dongabank.com.vn/exchange/export");
            }
        });

    }
}
