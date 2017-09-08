package firstapplication.codesroom.com.firstprojectfromcodesroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import firstapplication.codesroom.com.firstprojectfromcodesroom.R;
import firstapplication.codesroom.com.firstprojectfromcodesroom.beans.MyItem;
import firstapplication.codesroom.com.firstprojectfromcodesroom.fregments.ListFragment;

public class HomeActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container, ListFragment.newInstance(1));
            transaction.commit();
        } else {
            finish();
        }
    }

    @Override
    public void onListFragmentInteraction(MyItem item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(MyItem.class.getSimpleName(), item);
        startActivity(intent);
    }

    public void onClick(View view) {

        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);

    }
}
