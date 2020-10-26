package dev.ssonsall.crashmanagersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.ssonsall.crashmanager.CrashCatchExceptionSend;
import dev.ssonsall.crashmanager.CrashManager;
import dev.ssonsall.crashmanager.CrashSavedLogSend;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNullPoint, btnOutOfIndex, btnCatched, btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*
         * If you want to send the logs stored in SharedPreferences to email, you can do the code below
         * (You can write at any time at the time you want)
         */
        new CrashSavedLogSend(getApplicationContext()).sendMailSavedCrashLog();

        /*Setup Views*/
        setupViews();
    }

    public void setupViews() {
        btnNullPoint = findViewById(R.id.btnNullPoint);
        btnOutOfIndex = findViewById(R.id.btnOutOfIndex);
        btnCatched = findViewById(R.id.btnCatched);
        btnPrev = findViewById(R.id.btnPrev);

        btnNullPoint.setOnClickListener(this);
        btnOutOfIndex.setOnClickListener(this);
        btnCatched.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNullPoint:
                String nullString = null;
                /* The Null Point Exception is deliberately caused */
                if (nullString.equals("")) return;
                break;
            case R.id.btnOutOfIndex:
                /* The Out Of Index Exception is deliberately caused */
                int[] outOfIndex = new int[3];
                outOfIndex[5] = 1004;
                break;
            case R.id.btnCatched:
                /* Cause exception and catch it*/
                try {
                    String nullStringCatch = null;
                    if (nullStringCatch.equals("")) return;
                } catch (Exception e) {
                    /*
                     * If you want to send an exception caught by logic, such as a try-catch syntax, to email,
                     * then do the following code
                     */
                    new CrashCatchExceptionSend(getApplicationContext()).sendMailCatchException(e);
                }
                break;
            case R.id.btnPrev:
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}