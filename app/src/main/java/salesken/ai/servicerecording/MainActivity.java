package salesken.ai.servicerecording;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends IstarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAllpermission();

        ButterKnife.bind(this);
    }

    @OnClick(R.id.startservice)
    public void startService(){
        Intent intent = new Intent(MainActivity.this, RecordingService.class);
        intent.setAction(SaleskenIntent.START_FOREGROUND);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }
    @OnClick(R.id.stopservice)
    public void stopService(){
        Intent intent = new Intent(MainActivity.this, RecordingService.class);
        intent.setAction(SaleskenIntent.STOP_FOREGROUND);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }
}
