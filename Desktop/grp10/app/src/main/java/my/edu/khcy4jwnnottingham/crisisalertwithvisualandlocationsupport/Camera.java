package my.edu.khcy4jwnnottingham.crisisalertwithvisualandlocationsupport;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.File;


public class Camera extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    static final int CAM_REQUEST = 1;
    ImageView imageView;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    String mCurrentPhotoPath;
    private static RatingBar rating_b;
    private static TextView text_v;
    GPS_tracker gps;
    Button btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        ratingBarListener();

        btn = (Button)findViewById(R.id.button12);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                gps=  new GPS_tracker(Camera.this);
                if(gps.canGetLocation())
                {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongtitude();

                    Toast.makeText(getApplicationContext(), "LAT: "+latitude + " " +"LONG: "+longitude,Toast.LENGTH_LONG).show();
                }
                else{
                    gps.showSettingAlert();
                }
            }
        });
    }
//    public void gps(View v){
//        gps = new GPS_tracker(Camera.this);
//
//        if(gps.canGetLocation()){
//            double latitude = gps.getLatitude();
//            double longtitude = gps.getLongtitude();
//
//            Toast.makeText(getApplicationContext(), "Lat: "+ latitude + "Long" + longtitude, Toast.LENGTH_LONG).show();
//        }
//        else{
//            gps.showSettingAlert();
//        }
//    }
    public void cam(View v) {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile();
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera_intent, CAM_REQUEST);
    }

    private File getFile() {
        File folder = new File("sdcard/camera_app");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");
        return image_file;

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
    public void ratingBarListener(){
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        text_v = (TextView)findViewById(R.id.textView);

        rating_b.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener()
        {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        text_v.setText(String.valueOf(rating));
                    }
        });
    }


}
/*class one {

    public void cam(View v) {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile();
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera_intent, CAM_REQUEST);
    }

    private File getFile() {
        File folder = new File("sdcard/camera_app");

            if (!folder.exists()) {
                folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");
        return image_file;

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}*/
