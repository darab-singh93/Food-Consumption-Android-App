package androidproject.example.com.food.Menu;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidproject.example.com.food.R;

public class MenuFragment extends Fragment {
    Button btn_Breakfast,btn_Dinner, btn_Lunch, btn_Snack, btn_Dessert;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btn_Breakfast=(Button)view.findViewById(R.id.btn_Breakfast);
        btn_Dinner=(Button)view.findViewById(R.id.btn_Dinner);
        btn_Lunch=(Button)view.findViewById(R.id.btn_Lunch);
        btn_Snack=(Button)view.findViewById(R.id.btn_Snack);
        btn_Dessert=(Button)view.findViewById(R.id.btn_Dessert);

        btn_Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BreakfastActivity.class);
                startActivity(intent);

            }
        });
        btn_Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LunchActivity.class);
                startActivity(intent);


            }
        });
        btn_Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DinnerActivity.class);
                startActivity(intent);
            }
        });
        btn_Dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DessertActivity.class);
                startActivity(intent);


            }
        });
        btn_Snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SnackActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }
}