package id.androiddev.sikontak;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import id.androiddev.sikontak.R;

public class MainActivity extends AppCompatActivity {


    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListKontak mAdapter;
    private int animation_type = ItemAnimation.BOTTOM_UP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utility.setSystemBarColor(this);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<ModelKontak> items = Utility.getPlayerData(this);


        //set data and list adapter
        mAdapter = new AdapterListKontak(this, items,animation_type);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListKontak.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ModelKontak obj, int position) {

                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra("name", obj.getName());
                i.putExtra("email", obj.getEmail());
                i.putExtra("image", obj.getImage());
                startActivity(i);

//                Snackbar.make(parent_view, "Item " + obj.getName() + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Intent i = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
    
}
