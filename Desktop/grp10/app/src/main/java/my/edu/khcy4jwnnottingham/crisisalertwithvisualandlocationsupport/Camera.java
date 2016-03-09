package my.edu.khcy4jwnnottingham.crisisalertwithvisualandlocationsupport;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


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
    Button button_gps;
    Button button_description;
    ImageButton button_camera;
    TextView text;
    TextView text1;


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

        //button_gps = (Button)findViewById(R.id.button12);
        text = (TextView) findViewById((R.id.textView20));
        text1 = (TextView) findViewById(R.id.textView21);
        imageView = (ImageView) findViewById(R.id.imageView);
        gps = new GPS_tracker(Camera.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongtitude();

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        try{
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude,1);

            if (gps.canGetLocation()) {
                Address address = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder("Address: ");

                for(int i=0; i<address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append(" ");
                }
                text.setText(stringBuilder.toString());
                text1.setText("Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            }
            else {
                gps.showSettingAlert();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    button_camera = (ImageButton)findViewById(R.id.imageButton);
    button_camera.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = getFile();
            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(camera_intent, CAM_REQUEST);
        }
    });

    button_description = (Button)findViewById(R.id.button8);
        button_description.setOnClickListener(new View.OnClickListener
                () {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Camera.this, Description.class));
            }
        });

//        button_gps.setOnClickListener(new View.OnClickListener()
//
//        {
//            public void onClick(View v){
//                gps=  new GPS_tracker(Camera.this);
//                if(gps.canGetLocation())
//                {
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongtitude();
//
//                    //Toast.makeText(getApplicationContext(), "LAT: "+latitude + " " +"LONG: "+longitude,Toast.LENGTH_LONG).show();
//                    text.setText("Latitude: " + latitude);
//                    text1.setText("Longitude: " + longitude);
//                }
//                else{
//                    gps.showSettingAlert();
//                }
//            }
//        });
    }

//    public void cam(View v) {
//        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File file = getFile();
//        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//        startActivityForResult(camera_intent, CAM_REQUEST);
//    }

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
        //imageView.setRotation(90);
        imageView.setImageDrawable(Drawable.createFromPath(path));

    }

    public void ratingBarListener(){
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        text_v = (TextView)findViewById(R.id.textView18);

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
