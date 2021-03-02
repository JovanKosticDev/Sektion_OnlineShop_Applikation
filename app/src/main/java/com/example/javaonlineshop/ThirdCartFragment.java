package com.example.javaonlineshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.javaonlineshop.SecondCartFragment.ORDER_KEY;

public class ThirdCartFragment extends Fragment {

    private Button btnBack, btnCheckout;
    private TextView txtItems, txtAdress, txtPhoneNumber, txtTotalPrice;
    private RadioGroup rgPayment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_third, container, false);

        initViews(view);
        Bundle bundle = getArguments();
        if(null != bundle){
            String jsonOrder = bundle.getString(ORDER_KEY);
            if(null != jsonOrder){
                Gson gson = new Gson();
                Type type = new TypeToken<Order>(){}.getType();
                Order order = gson.fromJson(jsonOrder, type);
                if(null != order){
                    String items = "";
                    for (GroceryItem i : order.getItems()){
                        items += "\n\t" + i.getName();
                    }
                    txtItems.setText(items);
                    txtAdress.setText(order.getAdress());
                    txtPhoneNumber.setText(order.getPhoneNumber());
                    txtTotalPrice.setText(String.valueOf(order.getTotalPrice()));

                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle backBundle = new Bundle();
                            backBundle.putString(ORDER_KEY, jsonOrder);
                            SecondCartFragment secondCartFragment = new SecondCartFragment();
                            secondCartFragment.setArguments(backBundle);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.container, secondCartFragment);
                            transaction.commit();
                        }
                    });

                    btnCheckout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (rgPayment.getCheckedRadioButtonId()){
                                case R.id.rbPayPal:
                                    order.setPaymentMethod("PayPal");
                                    break;
                                case R.id.rbCreditCard:
                                    order.setPaymentMethod("Kreditkarte");
                                    break;
                                default:
                                    order.setPaymentMethod("Unbekannte Zahlmethode");
                                    break;
                            }

                            order.setSuccess(true);

                            // TODO: 02.03.2021 Sende deine Anfrage mit Retrofit
                        }
                    });
                }
            }
        }

        return view;
    }

    private void initViews(View view){
        rgPayment = view.findViewById(R.id.rgPaymentMethod);
        txtAdress = view.findViewById(R.id.txtAdress);
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtItems = view.findViewById(R.id.txtItems);
        txtTotalPrice = view.findViewById(R.id.txtPrice);
        btnBack = view.findViewById(R.id.btnBack);
        btnCheckout = view.findViewById(R.id.btnCheckout);
    }
}
