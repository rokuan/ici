package com.ideal.apps.ici;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private GoogleApiClient googleApiClient;
    private GoogleMap mMap;
    private Location mLastLocation;
    private Marker myMarker;
    private AddressAdapter addressAdapter;
    private ListView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .build();*/

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        resultsView = (ListView) findViewById(R.id.search_results);
        addressAdapter = new AddressAdapter(this);
        resultsView.setAdapter(addressAdapter);
        resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                resultsView.setVisibility(View.INVISIBLE);
                Address selectedAddress = addressAdapter.getItem(i);
                updateLocation(selectedAddress);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.place, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView view = (SearchView) item.getActionView();

        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultsView.setVisibility(View.VISIBLE);
                addressAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMarker = mMap.addMarker(new MarkerOptions().position(sydney)
                .title(getString(R.string.my_location))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11.0f));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                updateLocation(marker.getPosition());
            }
        });
    }

    private void updateLocation(Address address){
        LatLng location = new LatLng(address.getLatitude(), address.getLongitude());
        myMarker.setPosition(location);
        updateLocation(location);
    }

    private void updateLocation(LatLng position){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    private void findNearestPlaces(){

    }

    public class AddressAdapter extends ArrayAdapter<Address> {
        private static final int MAX_ADDRESSES_RESULTS = 5;
        private List<Address> suggestions = new ArrayList<>();
        private Geocoder geocoder;
        private Filter filter = new AddressFilter();

        public AddressAdapter(Context context) {
            super(context, R.layout.suggestion_place);
            geocoder = new Geocoder(getContext());
        }

        @Override
        public int getCount() {
            return suggestions.size();
        }

        @Nullable
        @Override
        public Address getItem(int position) {
            return suggestions.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.suggestion_place, parent, false);
            Address address = getItem(position);

            TextView addressName = (TextView) v.findViewById(R.id.place_name);
            addressName.setText(address.getAddressLine(0));

            if(address.getMaxAddressLineIndex() > 0){
                StringBuilder builder = new StringBuilder();
                TextView addressStreet = (TextView)v.findViewById(R.id.place_address);
                for(int i=1; i<=address.getMaxAddressLineIndex(); i++){
                    builder.append(address.getAddressLine(i));
                    if(i != address.getMaxAddressLineIndex()){
                        builder.append(", ");
                    }
                }
                addressStreet.setText(builder.toString());
                addressStreet.setVisibility(View.VISIBLE);
            }

            return v;
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return filter;
        }

        public class AddressFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence query) {
                FilterResults results = new FilterResults();

                if(query == null || query.length() == 0){
                    return results;
                }

                try {
                    List<Address> addresses = geocoder.getFromLocationName(query.toString(), MAX_ADDRESSES_RESULTS);
                    System.out.println(addresses);
                    results.values = addresses;
                    results.count = addresses.size();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                suggestions.clear();
                if(filterResults.count > 0 && filterResults.values != null){
                    suggestions.addAll((List<Address>)filterResults.values);
                }
                notifyDataSetChanged();
            }
        }
    }
}
