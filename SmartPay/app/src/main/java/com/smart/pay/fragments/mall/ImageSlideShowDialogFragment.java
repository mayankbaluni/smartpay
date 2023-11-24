package com.smart.pay.fragments.mall;

/**
 * Created by Sandeep Londhe on 21-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.smart.pay.R;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.ProductsListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImageSlideShowDialogFragment extends DialogFragment {

    private String TAG = ImageSlideShowDialogFragment.class.getSimpleName();
    private ArrayList<ProductsListModel.ProductImage> images;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblCount, lblTitle, lblDate;
    private int selectedPosition = 0;

    private int dotsCount;
    private ImageView[] dots;

    private LinearLayout pager_indicator;

    public static ImageSlideShowDialogFragment newInstance() {
        ImageSlideShowDialogFragment f = new ImageSlideShowDialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_slider, container, false);
        try {
            viewPager = (ViewPager) v.findViewById(R.id.viewpager);
            lblCount = (TextView) v.findViewById(R.id.lbl_count);
            lblTitle = (TextView) v.findViewById(R.id.title);
            lblDate = (TextView) v.findViewById(R.id.date);

            pager_indicator = (LinearLayout) v.findViewById(R.id.viewPagerCountDots);

            images = (ArrayList<ProductsListModel.ProductImage>) getArguments().getSerializable("images");

            selectedPosition = getArguments().getInt("position");

            Log.e(TAG, "position: " + selectedPosition);
            Log.e(TAG, "images size: " + images.size());

            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

            setCurrentItem(selectedPosition);

            setPageViewIndicator();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    private void setCurrentItem(int position) {
        try {
            viewPager.setCurrentItem(position, false);
            displayMetaInfo(selectedPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);


            Log.d("###onPageSelected, pos ", String.valueOf(position));
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            }

            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

            if (position + 1 == dotsCount) {

            } else {

            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
        try {
            lblCount.setText((position + 1) + " of " + images.size());

            ProductsListModel.ProductImage image = images.get(position);
            lblTitle.setText(image.getImageId());
            lblDate.setText(image.getAddedDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //	adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            try {
                ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

                ProductsListModel.ProductImage image = images.get(position);

//                Glide.with(getActivity()).load(image.getLarge())
//                        .thumbnail(0.5f)
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(imageViewPreview);
//

                Picasso.with(getActivity())
                        .load(Constants.PRODUCT_IMAGE_PATH + image.getProductImage())
                        .placeholder(R.drawable.placeholder)
                        .into(imageViewPreview);


            } catch (Exception e) {
                e.printStackTrace();
            }

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = myViewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    viewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }


}
