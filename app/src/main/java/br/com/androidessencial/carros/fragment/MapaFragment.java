package br.com.androidessencial.carros.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class MapaFragment extends BaseFragment implements OnMapReadyCallback {
    private GoogleMap map;
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.carro = (Carro) Parcels.unwrap(getArguments().getParcelable(CARRO));

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        if (carro != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            LatLng location = new LatLng(Double.parseDouble(carro.latitude),Double.parseDouble(carro.longitude));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 13);

            map.moveCamera(cameraUpdate);
            map.addMarker(new MarkerOptions().title(carro.nome).snippet(carro.desc).position(location));
            map.setMyLocationEnabled(false);
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
}
