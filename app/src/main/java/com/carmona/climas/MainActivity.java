package com.carmona.climas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener{

    boolean bandGPS = false;
    boolean bandRED = false;


    private double latActual, lonActual;
    private double latMarca, lonMarca;
    private LocationManager locationManager;
    //private LatLng ubiActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!runtime_permissions()) {

            getGeoLocation();
            Toast.makeText(this, "Ubicacion: " + latActual + " " + lonActual, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Habilita los Permisos Prro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pri,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmUbicacion:

                break;
            case R.id.itmOtro:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getGeoLocation()
    {
        Location ubicacion;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        bandGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        bandRED = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        try{
            if(bandGPS)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100000,10,this);
                ubicacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                latActual = ubicacion.getLatitude();
                lonActual = ubicacion.getLongitude();
                Toast.makeText(this,"Posicion GPS "+latActual+":"+lonActual,Toast.LENGTH_SHORT).show();
            }
            if(bandRED)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100000,10,this);
                ubicacion = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latActual = ubicacion.getLatitude();
                lonActual = ubicacion.getLongitude();
                Toast.makeText(this,"Posicion Red "+latActual+":"+lonActual,Toast.LENGTH_SHORT).show();
            }

        }catch (SecurityException se){
            Log.e("GetLocation Error Rep:",se.toString());
        }

    }

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permisos Otorgados",Toast.LENGTH_SHORT).show();
            }else {
                runtime_permissions();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
