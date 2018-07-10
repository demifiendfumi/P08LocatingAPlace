package demoshowsms.android.myapplicationdev.com.p08_locatingaplace;

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    //Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                //current location
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                }else{
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.4433597, 103.7815925), 11));
//                CameraUpdateFactory.zoomIn() – Provides a CameraUpdate instance zoomed in by one level.
//                CameraUpdateFactory.zoomOut() - Provides a CameraUpdate instance zoomed out by one level.
//                CameraUpdateFactory.zoomTo(float) - Generates a CameraUpdate instance that changes the zoom level to the specified value.
//                CameraUpdateFactory.zoomBy(float) – Provides a CameraUpdate instance with a zoom level increased or decreased by the specified amount.
//                CameraUpdateFactory.newLatLng(LatLng) - Creates a CameraUpdate instance that changes the camera's target latitude and longitude.
//                CameraUpdateFactory.newLatLngZoom(LatLng, float) - Generates a CameraUpdate instance that changes the camera's latitude, longitude and zoom.
                LatLng poi_North = new LatLng(1.4433597, 103.7815925);
                //add marker
                final Marker north = map.addMarker(new MarkerOptions()
                        .position(poi_North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                LatLng poi_Central = new LatLng(1.3301464,103.8294176);
                final Marker central = map.addMarker(new MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.3478878,103.886232);
                final Marker east = map.addMarker(new MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(north)){
                            Toast.makeText(getBaseContext(), "HQ - North", Toast.LENGTH_LONG).show();
                        }else if(marker.equals(central)){
                            Toast.makeText(getBaseContext(), "Central", Toast.LENGTH_LONG).show();
                        }else if(marker.equals(east)){
                            Toast.makeText(getBaseContext(), "East", Toast.LENGTH_LONG).show();
                        }
                            return false;
                    }
                });

                UiSettings ui = map.getUiSettings();
                //compass
                ui.setCompassEnabled(true);
                //zoom controls
                ui.setZoomControlsEnabled(true);
            }
        });

//        btnNorth = (Button) findViewById(R.id.btnNorth);
//        btnCentral = (Button) findViewById(R.id.btnCentral);
//        btnEast = (Button) findViewById(R.id.btnEast);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ABC_Pte_Ltd, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (map != null){
                    if(position == 0){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.4433597, 103.7815925),15));
                    } else if(position == 1){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3301464,103.8294176),15));
                    }else if (position == 2){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3478878,103.886232),15));
                    }

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        btnNorth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.4433597, 103.7815925),15));
//                }
//            }
//        });
//
//        btnCentral.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3301464,103.8294176),15));
//                }
//            }
//        });
//
//        btnEast.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3478878,103.886232),15));
//                }
//            }
//        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantedResults){
//        switch (requestCode){
//            case 0: {
//                if (grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                }
//            }
//        }
//    }
}
