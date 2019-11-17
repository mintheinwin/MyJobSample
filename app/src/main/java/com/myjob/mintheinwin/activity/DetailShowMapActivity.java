package com.myjob.mintheinwin.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.myjob.mintheinwin.R;
import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.mvp.data.ObjectWrapperForBinder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailShowMapActivity extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private SupportMapFragment supportMapFragment;
    private static final String DATA = "user_data";
    private JobDataResponse chooseJobData;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_job_no)
    TextView tv_job_no;

    public static Intent newIntent(Context context, JobDataResponse jobDataResponse) {
        Intent intent = new Intent(context, DetailShowMapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBinder(DATA, new ObjectWrapperForBinder(jobDataResponse));
        //  bundle.putSerializable(DATA, jobDataResponse);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_map);
        ButterKnife.bind(this);

        fetchLocation();

        /*Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        chooseJobData = (JobDataResponse) bundle.getSerializable(DATA);*/
        //chooseJobData=(JobDataResponse)getIntent().getSerializableExtra(DATA);

        chooseJobData = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder(DATA)).getData();

        tv_name.setText(chooseJobData.getCompanyName());
        tv_job_no.setText(String.valueOf(chooseJobData.getJobId()));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void fetchLocation() {
        fusedLocationProviderClient
                = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(DetailShowMapActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        onMakerLocationView(googleMap, latLng, "Current Location");

        LatLng userLng = new LatLng(chooseJobData.getGeolocation().getLatitude(), chooseJobData.getGeolocation().getLongitude());

        onMakerLocationView(googleMap, userLng, "Job Location");

    }

    private void onMakerLocationView(GoogleMap googleMap, LatLng latLng, String title) {
        Marker markerOptions = googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
        markerOptions.showInfoWindow();
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

       /* CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(17)
                .bearing(90)
                .tilt(40)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }
}
