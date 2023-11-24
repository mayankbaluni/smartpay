package com.smart.pay.adapter.mall;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.Constants;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.db.DatabaseClient;
import com.smart.pay.models.output.GetOnlineCartOutput;
import com.smart.pay.models.output.IsSellerExist;
import com.smart.pay.models.output.UpdateCartOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


public class CartListAdapterNew extends RecyclerView.Adapter<CartListAdapterNew.MyViewHolder> {

    ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList;
    AppCompatActivity appCompatActivity;
    MainAPIInterface mainAPIInterface;
    String xAccessToken = "mykey";

    public CartListAdapterNew(ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList, AppCompatActivity appCompatActivity) {
        this.cartProductArrayList = cartProductArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView product_name;
        public MyTextView product_old_price;
        public MyTextView product_count;
        public ImageView product_image;
        public MyTextView product_discount;
        public MyTextView product_selling_price;
        public ImageView updateCartIncrease;
        public ImageView updateCartDecrease;
        public ProgressBar cartProgressBar;

        public MyViewHolder(View view) {
            super(view);

            product_name = (MyTextView) view.findViewById(R.id.product_name);
            product_old_price = (MyTextView) view.findViewById(R.id.product_old_price);
            product_count = (MyTextView) view.findViewById(R.id.product_count);
            product_image = (ImageView) view.findViewById(R.id.product_image);
            product_discount = (MyTextView) view.findViewById(R.id.product_discount);
            product_selling_price = (MyTextView) view.findViewById(R.id.product_selling_price);
            updateCartIncrease = (ImageView) view.findViewById(R.id.updateCartIncrease);
            updateCartDecrease = (ImageView) view.findViewById(R.id.updateCartDecrease);
            cartProgressBar = (ProgressBar) view.findViewById(R.id.cartProgressBar);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_cart_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final GetOnlineCartOutput.CartProduct cartProduct = cartProductArrayList.get(position);

        mainAPIInterface = ApiUtils.getAPIService();

        double sell_price = Double.parseDouble(cartProduct.getPrice());
        double seller_old_price = Double.parseDouble(cartProduct.getOld_price());

        double orginal_price;
        double discount;

        discount = Double.parseDouble(cartProduct.getDiscount());

        holder.product_name.setText(cartProduct.getProductName());
        holder.product_selling_price.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", sell_price));

        holder.product_discount.setText(cartProduct.getDiscount() + "%");

        holder.product_old_price.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", seller_old_price));

        Picasso.with(appCompatActivity)
                .load(Constants.PRODUCT_IMAGE_PATH + cartProduct.getProductImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.product_image);

        holder.product_count.setText(cartProduct.getQuantity());

        if (Integer.valueOf(cartProduct.getQuantity()) == 1) {

            holder.updateCartDecrease.setImageResource(R.mipmap.ic_cart_delete);
        }

        holder.updateCartIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateIncreaseRequest(cartProduct.getProductId(), cartProduct.getSellerId(), holder.product_count,
                        holder.cartProgressBar, holder.product_selling_price, holder.product_old_price,
                        holder.updateCartDecrease);

            }
        });

        holder.updateCartDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.valueOf(cartProduct.getQuantity()) == 1) {

                    updateDeleteRequest(cartProduct.getProductId(), cartProduct.getSellerId(),
                            holder.cartProgressBar, cartProductArrayList, position);

                } else {

                    updateDecreaseRequest(cartProduct.getProductId(), cartProduct.getSellerId(), holder.product_count,
                            holder.cartProgressBar, holder.product_selling_price, holder.product_old_price,
                            holder.updateCartDecrease, cartProductArrayList, position);

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return cartProductArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void updateIncreaseRequest(String product_id, String seller_id, final MyTextView product_count,
                                       final ProgressBar progressBar,
                                       final MyTextView selling_price, final MyTextView old_price_txt,
                                       final ImageView cartDecrease) {

        progressBar.setVisibility(View.VISIBLE);

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);
        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("seller_id", seller_id);
        MultipartBody.Part product_id_body = MultipartBody.Part.createFormData("product_id", product_id);


        mainAPIInterface.updateCartIncrease(xAccessToken, customer_id_body, seller_id_body, product_id_body).enqueue(new Callback<UpdateCartOutput>() {
            @Override
            public void onResponse(Call<UpdateCartOutput> call, Response<UpdateCartOutput> response) {


                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    product_count.setText(String.valueOf(response.body().getQuantity()));
                    double sell_price = response.body().getPrice();
                    double old_price = response.body().getOld_price();

                    selling_price.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", sell_price));
                    old_price_txt.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", old_price));

                    if (response.body().getQuantity() > 1) {
                        cartDecrease.setImageResource(R.mipmap.ic_cart_minus);
                    }

                } else {
                    Toast.makeText(appCompatActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateCartOutput> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
                progressBar.setVisibility(View.GONE);
            }
        });


    }


    private void updateDecreaseRequest(final String product_id, final String seller_id, final MyTextView product_count,

                                       final ProgressBar progressBar, final MyTextView selling_price, final MyTextView old_price_txt,
                                       final ImageView decreaseImage, final ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList, final int position) {

        progressBar.setVisibility(View.VISIBLE);

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);
        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("seller_id", seller_id);
        MultipartBody.Part product_id_body = MultipartBody.Part.createFormData("product_id", product_id);


        mainAPIInterface.updateCartDecrease(xAccessToken, customer_id_body, seller_id_body, product_id_body).enqueue(new Callback<UpdateCartOutput>() {
            @Override
            public void onResponse(Call<UpdateCartOutput> call, Response<UpdateCartOutput> response) {


                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    product_count.setText(String.valueOf(response.body().getQuantity()));
                    double sell_price = response.body().getPrice();
                    double old_price = response.body().getOld_price();

                    selling_price.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", sell_price));

                    old_price_txt.setText(SmartPayApplication.CURRENCY_SYMBOL + String.format("%.2f", old_price));


                    if (response.body().getQuantity() == 1) {

                        decreaseImage.setImageResource(R.mipmap.ic_cart_delete);
                    }

                    if (response.body().getQuantity() < 1) {
                        updateDeleteRequest(product_id, seller_id, progressBar, cartProductArrayList, position);
                    }

                } else {
                    Toast.makeText(appCompatActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateCartOutput> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    private void updateDeleteRequest(
            final String product_id, String seller_id, final ProgressBar progressBar,
            final ArrayList<GetOnlineCartOutput.CartProduct> productList, final int position) {

        progressBar.setVisibility(View.VISIBLE);

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);
        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("seller_id", seller_id);
        MultipartBody.Part product_id_body = MultipartBody.Part.createFormData("product_id", product_id);


        mainAPIInterface.updateCartDelete(xAccessToken, customer_id_body,
                seller_id_body, product_id_body).enqueue(new Callback<IsSellerExist>() {
            @Override
            public void onResponse(Call<IsSellerExist> call, Response<IsSellerExist> response) {


                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(appCompatActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    new DeleteCartProduct(product_id).execute();

                    productList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, productList.size());
                    notifyDataSetChanged();


                } else {
                    Toast.makeText(appCompatActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IsSellerExist> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    class DeleteCartProduct extends AsyncTask<Void, Void, Void> {

        String strProductId;

        public DeleteCartProduct(String strProductId) {
            this.strProductId = strProductId;
        }

        @Override
        protected void onPreExecute() {

//            deliveryResponseList.clear();
            Log.e("onPreExecutive", "called");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseClient
                    .getInstance(appCompatActivity)
                    .getAppDatabase()
                    .daoProduct()
                    .deleteProduct(Integer.valueOf(strProductId));

            return null;
        }

        protected void onPostExecute() {
            notifyDataSetChanged();
        }
    }


}
