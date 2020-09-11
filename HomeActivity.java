package com.shopping.onlineshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shopping.onlineshopping.Model.Products;
import com.shopping.onlineshopping.ViewHolder.ProductViewholder;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
        DrawerLayout drawerLayout;
        NavigationView navigationView;
    RecyclerView.LayoutManager layoutManager;
       Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationview);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View headerView=navigationView.getHeaderView(0);
        TextView usernameTextView=headerView.findViewById(R.id.userprofilename);
        ImageView profileImageview=headerView.findViewById(R.id.userprofileImage);
       // usernameTextView.setText(Prevalent.currentOnlineUser.getName());
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseRecyclerOptions<Products>options=new FirebaseRecyclerOptions.Builder<Products>().setQuery(ProductsRef,Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewholder> adapter;
        adapter = new FirebaseRecyclerAdapter<Products, ProductViewholder>(options) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected void onBindViewHolder(@NonNull ProductViewholder holder, int position, @NonNull Products model)
            {
                    holder.txtProductName.setText(model.getPname());
                    holder.txtProductDescription.setText(model.getPid());
                    holder.txtProductPrice.setText("Price = "+model.getPrice()+ "Rs");
                Picasso.get().load(model.getImage()).into(holder.imageView);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @NonNull
            @Override
            public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item_layout,parent,false);
                ProductViewholder holder=new ProductViewholder(view);
                return holder;

            }


        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int v=item.getItemId();
        switch (v)
        {
            case R.id.nav_cart:
                Toast.makeText(this, "One", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_orders:
                Toast.makeText(this, "Two", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_categories:
                Toast.makeText(this, "Two", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(HomeActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else

            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("CONFRIM EXIT");
                builder.setIcon(R.drawable.crosss);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
    }

        }

