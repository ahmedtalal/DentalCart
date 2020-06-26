package com.example.dentalcart.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentalcart.Adapters.CartAdapter;
import com.example.dentalcart.Pojo.CartModel;
import com.example.dentalcart.Pojo.ItemModel;
import com.example.dentalcart.R;
import com.example.dentalcart.Repositories.GeneralOperations;
import com.example.dentalcart.ViewModels.CartItemsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    @BindView(R.id.cartToolbar_id)
    Toolbar cartToolbarId;
    @BindView(R.id.cartRecycler_id)
    RecyclerView cartRecyclerId;
    @BindView(R.id.totalPrice_id)
    TextView totalPriceId;
    @BindView(R.id.catrButton_id)
    Button catrButtonId;

    private CartAdapter cartAdapter ;
    private int totalPrice = 0 ;
    private List<CartModel> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setSupportActionBar(cartToolbarId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cartToolbarId.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this , MainActivity.class) ;
                startActivity(intent);
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this , LinearLayoutManager.VERTICAL , false) ;
        linearLayoutManager.setStackFromEnd(false);
        cartRecyclerId.setLayoutManager(linearLayoutManager);
        CartItemsViewModel cartItemsViewModel = new ViewModelProvider(this).get(CartItemsViewModel.class);
        cartItemsViewModel.init();
        cartItemsViewModel.getCarts().observe(this, new Observer<List<CartModel>>() {
            @Override
            public void onChanged(List<CartModel> cartModels) {
                if (cartModels.size() >0){
                    cartAdapter = new CartAdapter(cartModels , CartActivity.this) ;
                    cartRecyclerId.setAdapter(cartAdapter);
                    cartAdapter.notifyDataSetChanged();
                }
            }
        });

        // this method is used to calculate the total price
        CalcTotalPrice();


        catrButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralOperations.deleteAllProductFromCart(CartActivity.this);
            }
        });
    }

    // this method is used to calculate the total price
    private void CalcTotalPrice() {
        list = new ArrayList<>() ;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser() ;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference reference = firebaseDatabase.getReference().child("Cart").child(user.getUid()) ;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(CartModel.class));
                    Log.i("ddd",  list.size()+ "");
                }
                for (CartModel cartModel : list){
                    int quantity = cartModel.getQuantity() ;
                    int price = Integer.parseInt(cartModel.getPrice()) ;
                    totalPrice += (quantity * price) ;
                }
                totalPriceId.setText("the total price : " + totalPrice);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private void getPrice() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser() ;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
//        DatabaseReference reference = firebaseDatabase.getReference().child("CartTotalPrice").child(user.getUid()) ;
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ItemModel itemModel = dataSnapshot.getValue(ItemModel.class);
//                totalPriceId.setText("Total Price : " + itemModel.getPrice());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CartActivity.this , MainActivity.class) ;
        startActivity(intent);
        finish();
    }
}
