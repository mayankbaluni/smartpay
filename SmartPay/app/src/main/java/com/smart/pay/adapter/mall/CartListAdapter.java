package com.smart.pay.adapter.mall;

/**
 * Created by Sandeep Londhe on 25-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.Constants;
import com.smart.pay.db.DatabaseClient;
import com.smart.pay.db.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    private List<Product> productList;
    Activity activity;

    public Product product;

    int strProductId;

    MyViewHolder viewHolder;

    int alreadyAddedProductCount, newProductCount;
    double alreadyAddedProductPrice, newProductPrice;

    double singleAddedProductPrice;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName;
        public TextView txtProductPrice;
        public TextView txtProductQuantity;
        public Button btnMin;
        public Button btnPlus;
        public ImageView productImage;
        public ImageView imgRemoveItem;


        public MyViewHolder(View view) {
            super(view);

            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtProductPrice = (TextView) view.findViewById(R.id.txtProductPrice);
            txtProductQuantity = (TextView) view.findViewById(R.id.txtProductQuantity);
            btnMin = (Button) view.findViewById(R.id.btnMin);
            btnPlus = (Button) view.findViewById(R.id.btnPlus);
            productImage = (ImageView) view.findViewById(R.id.productImage);
            imgRemoveItem = (ImageView) view.findViewById(R.id.imgRemoveItem);


        }
    }


    public CartListAdapter(List<Product> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Product product = productList.get(position);

        strProductId = productList.get(position).getId();


        holder.txtProductQuantity.setText(String.valueOf(product.getStock()));
        holder.txtProductPrice.setText("Price : " + SmartPayApplication.CURRENCY_SYMBOL + product.getPrice());

        viewHolder = holder;


        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new GetAlreadyAddedProductCount(productList.get(position).getId()).execute();

                System.out.println("No of product ids==" + productList.get(position).getId());
                System.out.println("No of product price==" + productList.get(position).getPrice());
                System.out.println("No of product count==" + productList.get(position).getStock());
                System.out.println("Already Added Count==" + alreadyAddedProductCount);
                System.out.println("Already Added Count++==" + (alreadyAddedProductCount + 1));

                int newIntValue = alreadyAddedProductCount + 1;

                new UpdateProductCount(productList.get(position).getId(), newIntValue, holder).execute();


            }
        });


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GetAlreadyAddedProductCount(productList.get(position).getId()).execute();

                System.out.println("No of product ids==" + productList.get(position).getId());
                System.out.println("No of product price==" + productList.get(position).getPrice());
                System.out.println("No of product count==" + productList.get(position).getStock());

                System.out.println("Already Added Count==" + alreadyAddedProductCount);

                System.out.println("Already Added Count--==" + (alreadyAddedProductCount - 1));

                int newIntValue = alreadyAddedProductCount - 1;

                new UpdateProductCount(productList.get(position).getId(), newIntValue, holder).execute();

            }
        });

        holder.txtProductName.setText(product.getName());

        Picasso.with(activity)
                .load(Constants.PRODUCT_IMAGE_PATH + product.getImg())
                .placeholder(R.drawable.placeholder)
                .into(holder.productImage);


        holder.imgRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DeleteCartProduct().execute();

                productList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, productList.size());
                notifyDataSetChanged();
            }
        });

    }


    public void increaseInteger(MyViewHolder myViewHolder) {
        alreadyAddedProductCount = alreadyAddedProductCount + 1;
        display(alreadyAddedProductCount, myViewHolder);

    }


    public void decreaseInteger(MyViewHolder myViewHolder) {
        alreadyAddedProductCount = alreadyAddedProductCount - 1;
        display(alreadyAddedProductCount, myViewHolder);
    }


    private void display(int number, MyViewHolder holder) {
        holder.txtProductQuantity.setText("" + number);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class DeleteCartProduct extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

//            deliveryResponseList.clear();
            Log.e("onPreExecutive", "called");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseClient
                    .getInstance(activity)
                    .getAppDatabase()
                    .daoProduct()
                    .deleteProduct(strProductId);

            return null;
        }

        protected void onPostExecute() {

            Toast.makeText(activity, "Product Removed", Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        }
    }

    class GetAlreadyAddedProductCount extends AsyncTask<Void, Void, Void> {

        int pID;
        int count;

        public GetAlreadyAddedProductCount(int strProductId2) {

            this.pID = strProductId2;

        }

        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product
            //adding to database

            count = DatabaseClient.getInstance(SmartPayApplication.getInstance()).getAppDatabase()
                    .daoProduct()
                    .getStockForProductInCart(pID);


            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

//            viewHolder.txtProductQuantity.setText(String.valueOf(alreadyAddedProductCount));

            System.out.println("PRODUCT COUNT ID==" + strProductId + " COUNT==" + count);

            count = alreadyAddedProductCount;
        }
    }

    class GetAlreadyAddedProductPrice extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            alreadyAddedProductPrice = DatabaseClient.getInstance(SmartPayApplication.getInstance()).getAppDatabase()
                    .daoProduct()
                    .getPriceForProductInCart(strProductId);

            System.out.println("PRODUCT ID PASSING BY==" + strProductId);


            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            singleAddedProductPrice = alreadyAddedProductPrice / alreadyAddedProductCount;


            viewHolder.txtProductPrice.setText("Price : " + SmartPayApplication.CURRENCY_SYMBOL + alreadyAddedProductPrice);

            System.out.println("ADDED PRICE BY PRODUCT ID==" + strProductId + " PRICE==" + alreadyAddedProductPrice + " SINGLE PRICE==" + singleAddedProductPrice);
        }
    }

    class UpdateProductCount extends AsyncTask<Void, Void, Void> {

        int pId, newValue;
        MyViewHolder myViewHolder;

        public UpdateProductCount(int productId, int newValue, MyViewHolder myViewHolder) {
            super();
            // do stuff

            this.pId = productId;
            this.newValue = newValue;
            this.myViewHolder = myViewHolder;

        }

        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            DatabaseClient.getInstance(SmartPayApplication.getInstance()).getAppDatabase()
                    .daoProduct()
                    .updateProduct(newValue, pId);

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            myViewHolder.txtProductQuantity.setText(String.valueOf(newValue));

            Toast.makeText(SmartPayApplication.getInstance(), "Product count==" + newValue, Toast.LENGTH_SHORT).show();

        }

    }

    class UpdateProductPrice extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            DatabaseClient.getInstance(SmartPayApplication.getInstance()).getAppDatabase()
                    .daoProduct()
                    .updateProductPrice(alreadyAddedProductPrice, strProductId);

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            singleAddedProductPrice = alreadyAddedProductPrice / alreadyAddedProductCount;

            Toast.makeText(SmartPayApplication.getInstance(), "Product count==" + alreadyAddedProductCount, Toast.LENGTH_SHORT).show();
            System.out.println("Single Product Price==" + singleAddedProductPrice);
        }

    }


}
