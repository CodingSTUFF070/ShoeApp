package com.codingstuff.shoeapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.codingstuff.shoeapp.utils.model.ShoeCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCartItem(ShoeCart shoeCart);

    @Query("SELECT * FROM shoe_table")
    LiveData<List<ShoeCart>> getAllCartItems();

    @Delete
    void deleteCartItem(ShoeCart shoeCart);

    @Query("UPDATE shoe_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id , int quantity);

    @Query("UPDATE shoe_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id , double totalItemPrice);

    @Query("DELETE FROM shoe_table")
    void deleteAllItems();

}
