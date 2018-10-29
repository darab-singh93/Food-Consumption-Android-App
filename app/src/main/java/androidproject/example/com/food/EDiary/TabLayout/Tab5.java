package androidproject.example.com.food.EDiary.TabLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidproject.example.com.food.Adapter.DessertRecycler;
import androidproject.example.com.food.Adapter.SnackRecycler;
import androidproject.example.com.food.Database.Tables.DessertHelper;
import androidproject.example.com.food.Database.Tables.SnackHelper;
import androidproject.example.com.food.POJO.Dessert;
import androidproject.example.com.food.POJO.Snack;
import androidproject.example.com.food.R;

public class Tab5 extends Fragment {

    private RecyclerView recyclerView;
    private DessertRecycler dessertRecycler;
    private DessertHelper dessertHelper;
    private ArrayList<Dessert> listDessert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab5, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewDessert);
        initObjects();

        return view;
    }
    private void initObjects() {
        listDessert = new ArrayList<>();
        dessertRecycler=new DessertRecycler(listDessert,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dessertRecycler);
        dessertHelper = new DessertHelper(getActivity());

        getDataFromSQLite();

    }


    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listDessert.clear();
                listDessert.addAll(dessertHelper. getAllDessert());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dessertRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
