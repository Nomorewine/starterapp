package android.choi.alexander.otherapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 111;
    private TextView textView;
    private String LOG_TAG = "log_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        View btnGo = findViewById(R.id.button);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "click");
                try {
                    Log.d(LOG_TAG, "trying to start intent");
                    Intent intent = new Intent("io.alphacon.wallet.WALLET_ACTIVITY");//getPackageManager().getLaunchIntentForPackage("android.choi.alexander.tmp");
                    intent.putExtra("flag", "success");
                    intent.putExtra("address", "address");
                    intent.putExtra("amount", "amount");
                    startActivityForResult(intent, REQUEST_CODE);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                    Log.e("Main", "Second application is not installed!");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String intentData = data.getStringExtra("result");
                String addr = data.getStringExtra("address");
                String amnt = data.getStringExtra("amount");
                textView.setText(intentData + " :: " + addr + " :: " + amnt);
            } else {
                textView.setText("User press back at Second Activity");
            }
        }
    }
}
