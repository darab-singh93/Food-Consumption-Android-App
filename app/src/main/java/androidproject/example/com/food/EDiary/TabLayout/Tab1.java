package androidproject.example.com.food.EDiary.TabLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidproject.example.com.food.Adapter.BreakRecycler;
import androidproject.example.com.food.Adapter.DessertRecycler;
import androidproject.example.com.food.Database.Tables.BreakHelper;
import androidproject.example.com.food.Database.Tables.DessertHelper;
import androidproject.example.com.food.POJO.Break;
import androidproject.example.com.food.POJO.Dessert;
import androidproject.example.com.food.R;

public class Tab1 extends Fragment {
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<Break> listUsers;
    private BreakRecycler breakRecycler;
    private BreakHelper breakHelper;
    private RecyclerView recyclerViewBeneficiary;
    private ArrayList<Break> listBeneficiary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_tab1, container, false);

        recyclerViewBeneficiary = (RecyclerView)view.findViewById(R.id.recyclerViewBeneficiary);
        initObjects();

        return view;
    }
    private void initObjects() {
        listBeneficiary = new ArrayList<>();
        breakRecycler=new BreakRecycler(listBeneficiary,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBeneficiary.setLayoutManager(mLayoutManager);
        recyclerViewBeneficiary.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBeneficiary.setHasFixedSize(true);
        recyclerViewBeneficiary.setAdapter(breakRecycler);
        breakHelper = new BreakHelper(getActivity());

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
                listBeneficiary.clear();
                listBeneficiary.addAll(breakHelper. getAllBeneficiary());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                breakRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
