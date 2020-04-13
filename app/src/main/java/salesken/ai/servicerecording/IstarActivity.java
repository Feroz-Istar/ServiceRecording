package salesken.ai.servicerecording;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class IstarActivity extends AppCompatActivity {
    private static final String TAG ="MyActivity" ;
    String[] PERMISSIONS = {Manifest.permission.INTERNET,Manifest.permission.WAKE_LOCK,Manifest.permission.CAPTURE_AUDIO_OUTPUT,Manifest.permission.MANAGE_OWN_CALLS,Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.READ_CALL_LOG,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.READ_PHONE_STATE, Manifest.permission.DISABLE_KEYGUARD, Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.USE_SIP, Manifest.permission.READ_CONTACTS, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int DRAW_OVER_OTHER_APP_PERMISSION = 123;

    public boolean requestAllpermission() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : PERMISSIONS) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public void askForSystemOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available to open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION);
        }
    }

}
