package androidproject.example.com.food.UserProfile;

import android.content.Context;
import android.net.Uri;
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

import androidproject.example.com.food.Database.DatabaseHelper;
import androidproject.example.com.food.R;

public class UserFragment extends Fragment {
    private AppCompatTextView txtlistUsers;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        txtlistUsers = (AppCompatTextView) view.findViewById(R.id.txtlistUsers);
        recyclerViewUsers = (RecyclerView) view.findViewById(R.id.recyclerViewUsers);

        initObjects();

        return  view;
    }

        private void initObjects() {
            listUsers = new ArrayList<>();
            usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerViewUsers.setLayoutManager(mLayoutManager);
            recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
            recyclerViewUsers.setHasFixedSize(true);
            recyclerViewUsers.setAdapter(usersRecyclerAdapter);
            databaseHelper = new DatabaseHelper(getActivity());

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
                    listUsers.clear();
                    listUsers.addAll(databaseHelper.getAllUser());

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    usersRecyclerAdapter.notifyDataSetChanged();
                }
            }.execute();
        }
}