package com.example.dentalcart.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dentalcart.Pojo.CartModel;
import com.example.dentalcart.Pojo.ItemModel;
import com.example.dentalcart.Repositories.GetCartItemRepository;

import java.util.List;

public class CartItemsViewModel extends ViewModel {
    MutableLiveData<List<CartModel>> mutableLiveData ;
    GetCartItemRepository repository ;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        repository = GetCartItemRepository.getInstance() ;
        mutableLiveData = repository.getItemsFromCart();
    }

    public LiveData<List<CartModel>> getCarts(){
        return mutableLiveData ;
    }
}
