package es.jdamiancabello.inventory.ui.product;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.jdamiancabello.inventory.R;


public class ProductMapFragment extends Fragment {
    public static final String TAG = "ProductMapFragment";



    public static ProductMapFragment newInstance() {
        return new ProductMapFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_map, container, false);
    }

}
