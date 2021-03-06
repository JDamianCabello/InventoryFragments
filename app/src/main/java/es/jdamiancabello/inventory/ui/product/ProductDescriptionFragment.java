package es.jdamiancabello.inventory.ui.product;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.jdamiancabello.inventory.R;


public class ProductDescriptionFragment extends Fragment {
    public static final String TAG = "ProductDescriptionFragment";


    public static ProductDescriptionFragment newInstance() {
        return new ProductDescriptionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }


}
