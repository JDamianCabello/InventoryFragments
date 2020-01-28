package es.jdamiancabello.inventory.ui.product;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.jdamiancabello.inventory.R;


public class ProductManagerFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "ProductManagerFragment";
    private BottomNavigationView bottomNavigationView;
    private Fragment productInfo, productMap, productDescription;



    public static ProductManagerFragment newInstance() {
        return new ProductManagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().findViewById(R.id.floatingActionButton).setVisibility(View.GONE);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        getChildFragmentManager().beginTransaction().add(R.id.frameLayout,ProductInfoFragment.newInstance()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()){
            case R.id.product_menu_info:
                fragment = getChildFragmentManager().findFragmentByTag(ProductInfoFragment.TAG);
                if(fragment == null)
                    fragment = ProductInfoFragment.newInstance();
                break;

            case R.id.product_menu_description:
                fragment = getChildFragmentManager().findFragmentByTag(ProductDescriptionFragment.TAG);
                if(fragment == null)
                    fragment = ProductDescriptionFragment.newInstance();
                break;

            case R.id.product_menu_map:
                fragment = getChildFragmentManager().findFragmentByTag(ProductMapFragment.TAG);
                if(fragment == null)
                    fragment = ProductMapFragment.newInstance();
                break;
            default:
                fragment = null;
        }

        FragmentTransaction  fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit();

        return true;
    }
}
