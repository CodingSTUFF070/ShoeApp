package com.codingstuff.shoeapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.model.ShoeCart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHodler> {

    private CartClickedListeners cartClickedListeners;
    private List<ShoeCart> shoeCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setShoeCartList(List<ShoeCart> shoeCartList) {
        this.shoeCartList = shoeCartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHodler holder, int position) {

        ShoeCart shoeCart = shoeCartList.get(position);
        holder.shoeImageView.setImageResource(shoeCart.getShoeImage());
        holder.shoeNameTv.setText(shoeCart.getShoeName());
        holder.shoeBrandNameTv.setText(shoeCart.getShoeBrandName());
        holder.shoeQuantity.setText(shoeCart.getQuantity() + "");
        holder.shoePriceTv.setText(shoeCart.getTotalItemPrice() + "");


        holder.deleteShoeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(shoeCart);
            }
        });


        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(shoeCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(shoeCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (shoeCartList == null) {
            return 0;
        } else {
            return shoeCartList.size();
        }
    }

    public class CartViewHodler extends RecyclerView.ViewHolder {

        private TextView shoeNameTv, shoeBrandNameTv, shoePriceTv, shoeQuantity;
        private ImageView deleteShoeBtn;
        private ImageView shoeImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHodler(@NonNull View itemView) {
            super(itemView);

            shoeNameTv = itemView.findViewById(R.id.eachCartItemName);
            shoeBrandNameTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            shoePriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteShoeBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            shoeImageView = itemView.findViewById(R.id.eachCartItemIV);
            shoeQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }

    public interface CartClickedListeners {
        void onDeleteClicked(ShoeCart shoeCart);

        void onPlusClicked(ShoeCart shoeCart);

        void onMinusClicked(ShoeCart shoeCart);
    }
}
