package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.ProductImage;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_MECHANDICE = "arg_mechandice";
    private Product product;

    private TextView product_name;
    private ViewPager slider;
    private SliderViewPagerAdapter sliderAdapter;


    public static ProductDetailFragment newInstance(String merchandiceId) {

        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MECHANDICE, merchandiceId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        product = ProductsRepository.getInstance().getProductById(getArguments().getString(ARG_MECHANDICE));

        ArrayList<String> urls = new ArrayList<>();
        for (Product m : ProductsRepository.getInstance().getAllProducts())
            if (product.getId().equals(m.getId()))
                for (ProductImage image : m.getImages())
                    urls.add(image.getSrc());

        product_name = view.findViewById(R.id.product_detail_activity__product_name);
        product_name.setText(getString(R.string.product_name) + " " + product.getName());
        slider = view.findViewById(R.id.product_detail_activity__view_pager);
        sliderAdapter = new SliderViewPagerAdapter(getFragmentManager(), urls);
        slider.setAdapter(sliderAdapter);

    }

}