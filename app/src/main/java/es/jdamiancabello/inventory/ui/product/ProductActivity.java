package es.jdamiancabello.inventory.ui.product;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.ui.base.BaseActivity;

public class ProductActivity extends BaseActivity {
    private ProductManagerFragment productManagerFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProductManager();
    }

    private void showProductManager() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        productManagerFragment = (ProductManagerFragment) getSupportFragmentManager().findFragmentByTag(ProductManagerFragment.TAG);
        if(productManagerFragment == null)
            productManagerFragment = ProductManagerFragment.newInstance();
        fragmentTransaction.add(R.id.content,productManagerFragment,ProductManagerFragment.TAG).commit();
    }
}
