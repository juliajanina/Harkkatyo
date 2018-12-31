package com.example.juliawalden.harkkatyo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Spinner spinnerRestaurants;
    Spinner spinnerFoods;
    TextView textDate;
    EditText textComment;
    RatingBar ratingBar;
    int choiceRestaurant;
    int choiceFood;
    Context context = null;
    NavigationView navigationView;

    DatePickerDialog.OnDateSetListener dateSetListener;

    private static final String TAG = "MainActivity";

    University university = new University();

    protected ArrayList<Food> foods = new ArrayList<>();

    protected ArrayList<Review> reviews = new ArrayList<>();

    protected ArrayList<Rating> ratings = new ArrayList<>();

    writeFile wf = new writeFile();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        textDate = findViewById(R.id.textViewDate);
        spinnerRestaurants = findViewById(R.id.spinnerRestaurant);
        spinnerFoods = findViewById(R.id.spinnerFood);
        context = Main2Activity.this;
        textComment = findViewById(R.id.editTextComment);
        ratingBar = findViewById(R.id.ratingBar);


        ArrayAdapter<Restaurant> adapter = new ArrayAdapter<>(Main2Activity.this,
                android.R.layout.simple_spinner_item, university.getRestaurants());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRestaurants.setAdapter(adapter);
        spinnerRestaurants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choiceRestaurant = position;
                System.out.println("valittu ravintola: " + university.getRestaurants().get(choiceRestaurant));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(Main2Activity.this, dateSetListener, year, month, day);
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                textDate.setText(date);


                try {

                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                    AssetManager assetManager = getAssets();
                    InputStream is = assetManager.open("menus.xml");

                    Document doc = builder.parse(is);
                    doc.getDocumentElement().normalize();

                    System.out.println("Root: " + doc.getDocumentElement().getNodeName());

                    NodeList nodeList = doc.getDocumentElement().getElementsByTagName("Menu");

                    foods.clear();

                    for(int i = 0; i < nodeList.getLength(); i++) {

                        Node node = nodeList.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;

                            String d = element.getElementsByTagName("Date").item(0).getTextContent();
                            System.out.println("Testi: " + d + " sama kuin: " + date);

                            String r = element.getElementsByTagName("Restaurant").item(0).getTextContent();
                            System.out.println("Testi: " + r + " sama kuin " + university.getRestaurants().get(choiceRestaurant));

                            String res = university.getRestaurants().get(choiceRestaurant).toString();

                            if(d.equals(date) && r.equals(res)) {

                                foods.add(new Food(element.getElementsByTagName("BasicFood").item(0).getTextContent()));
                                foods.add(new Food(element.getElementsByTagName("VegetarianFood").item(0).getTextContent()));
                                foods.add(new Food(element.getElementsByTagName("Soup").item(0).getTextContent()));
                                foods.add(new Food(element.getElementsByTagName("Salad").item(0).getTextContent()));

                            }

                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    //e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    //e.printStackTrace();
                } finally {
                    System.out.println("Done");

                    ArrayAdapter<Food> adapter2 = new ArrayAdapter<>(Main2Activity.this,
                            android.R.layout.simple_spinner_item, foods);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFoods.setAdapter(adapter2);
                    spinnerFoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {

                            choiceFood = position2;
                            System.out.println("Valittu ruoka: " + choiceFood);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }
        };

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            navigationView.getMenu().getItem(0).setChecked(true);

        } else if (id == R.id.nav_menus) {
            Intent i = new Intent(Main2Activity.this, Main6Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ARRAYLIST", (Serializable) ratings);
            i.putExtra("BUNDLE", bundle);
            startActivity(i);

        } else if (id == R.id.nav_reviews) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)reviews);
            intent.putExtra("BUNDLE",args);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Intent in = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(in);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void submitButton(View v) {

        reviews.add(new Review(ratingBar.getRating(), textComment.getText().toString(),
                university.getRestaurants().get(choiceRestaurant).toString(), foods.get(choiceFood).getFoodName()));

        Toast.makeText(Main2Activity.this, "Arvostelu lisätty", Toast.LENGTH_SHORT).show();

        ratings.add(new Rating(foods.get(choiceFood).getFoodName(), ratingBar.getRating()));

        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("ratings.xml", Context.MODE_APPEND));

            System.out.println(context.getFilesDir());

            wf.writeXmlFile(ratings, osw);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
