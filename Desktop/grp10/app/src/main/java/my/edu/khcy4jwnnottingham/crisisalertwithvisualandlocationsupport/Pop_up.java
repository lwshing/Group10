package my.edu.khcy4jwnnottingham.crisisalertwithvisualandlocationsupport;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by LOW on 7/3/2016.
 */
public class Pop_up extends Activity {

        @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.pop_window);

                DisplayMetrics dimension = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dimension);

                int width = dimension.widthPixels;
                int height = dimension.heightPixels;

                getWindow().setLayout((int)(width*0.8), (int)(height*0.6));

            }




}
