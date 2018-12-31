package com.example.juliawalden.harkkatyo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main6Activity extends AppCompatActivity {

    TextView textDate;
    TextView yolo1;
    TextView yolo2;
    TextView yolo3;
    TextView yolo4;
    TextView laseri1;
    TextView laseri2;
    TextView laseri3;
    TextView laseri4;
    TextView lut1;
    TextView lut2;
    TextView lut3;
    TextView lut4;
    TextView rate11;
    TextView rate12;
    TextView rate13;
    TextView rate14;
    TextView rate21;
    TextView rate22;
    TextView rate23;
    TextView rate24;
    TextView rate31;
    TextView rate32;
    TextView rate33;
    TextView rate34;
    Context context = null;
    DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String TAG = "MainActivity";

    protected ArrayList<Food> foods1 = new ArrayList<>();
    protected ArrayList<Food> foods2 = new ArrayList<>();
    protected ArrayList<Food> foods3 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
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

        textDate = findViewById(R.id.textViewDate);
        yolo1 = findViewById(R.id.yoloFood1);
        yolo2 = findViewById(R.id.yoloFood2);
        yolo3 = findViewById(R.id.yoloFood3);
        yolo4 = findViewById(R.id.yoloFood4);
        laseri1 = findViewById(R.id.laseriFood1);
        laseri2 = findViewById(R.id.laseriFood2);
        laseri3 = findViewById(R.id.laseriFood3);
        laseri4 = findViewById(R.id.laseriFood4);
        lut1 = findViewById(R.id.lutFood1);
        lut2 = findViewById(R.id.lutFood2);
        lut3 = findViewById(R.id.lutFood3);
        lut4 = findViewById(R.id.lutFood4);
        rate11 = findViewById(R.id.rate11);
        rate12 = findViewById(R.id.rate12);
        rate13 = findViewById(R.id.rate13);
        rate14 = findViewById(R.id.rate14);
        rate21 = findViewById(R.id.rate21);
        rate22 = findViewById(R.id.rate22);
        rate23 = findViewById(R.id.rate23);
        rate24 = findViewById(R.id.rate24);
        rate31 = findViewById(R.id.rate31);
        rate32 = findViewById(R.id.rate32);
        rate33 = findViewById(R.id.rate33);
        rate34 = findViewById(R.id.rate34);
        context = Main6Activity.this;

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("BUNDLE");
        final ArrayList<Rating> ratings = (ArrayList<Rating>) bundle.getSerializable("ARRAYLIST");


        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(Main6Activity.this, dateSetListener, year, month, day);
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

                    for(int i = 0; i < nodeList.getLength(); i++) {

                        Node node = nodeList.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;

                            String d = element.getElementsByTagName("Date").item(0).getTextContent();
                            String r = element.getElementsByTagName("Restaurant").item(0).getTextContent();

                            if (d.equals(date) && r.equals("Ylioppilastalo")) {

                                foods1.add(new Food(element.getElementsByTagName("BasicFood").item(0).getTextContent()));
                                foods1.add(new Food(element.getElementsByTagName("VegetarianFood").item(0).getTextContent()));
                                foods1.add(new Food(element.getElementsByTagName("Soup").item(0).getTextContent()));
                                foods1.add(new Food(element.getElementsByTagName("Salad").item(0).getTextContent()));

                                for(int a = 0; a < ratings.size(); a++) {
                                    if(ratings.get(a).getFood().equals(foods1.get(0).getFoodName())) {

                                        rate11.setText(ratings.get(a).toString());

                                    } else if(ratings.get(a).getFood().equals(foods1.get(1).getFoodName())) {

                                        rate12.setText(ratings.get(a).toString());

                                    } else if(ratings.get(a).getFood().equals(foods1.get(2).getFoodName())) {

                                        rate13.setText(ratings.get(a).toString());

                                    } else if(ratings.get(a).getFood().equals(foods1.get(3).getFoodName())) {

                                        rate14.setText(ratings.get(a).toString());

                                    }
                                }


                            } else if (d.equals(date) && r.equals("Laseri")) {
                                foods2.add(new Food(element.getElementsByTagName("BasicFood").item(0).getTextContent()));
                                foods2.add(new Food(element.getElementsByTagName("VegetarianFood").item(0).getTextContent()));
                                foods2.add(new Food(element.getElementsByTagName("Soup").item(0).getTextContent()));
                                foods2.add(new Food(element.getElementsByTagName("Salad").item(0).getTextContent()));

                                for(int b = 0; b < ratings.size(); b++) {
                                    if(ratings.get(b).getFood().equals(foods2.get(0).getFoodName())) {

                                        rate21.setText(ratings.get(b).toString());

                                    } else if(ratings.get(b).getFood().equals(foods2.get(1).getFoodName())) {

                                        rate22.setText(ratings.get(b).toString());

                                    } else if(ratings.get(b).getFood().equals(foods2.get(2).getFoodName())) {

                                        rate23.setText(ratings.get(b).toString());

                                    } else if(ratings.get(b).getFood().equals(foods2.get(3).getFoodName())) {

                                        rate24.setText(ratings.get(b).toString());

                                    }
                                }

                            } else if (d.equals(date) && r.equals("LUT Buffet")) {
                                foods3.add(new Food(element.getElementsByTagName("BasicFood").item(0).getTextContent()));
                                foods3.add(new Food(element.getElementsByTagName("VegetarianFood").item(0).getTextContent()));
                                foods3.add(new Food(element.getElementsByTagName("Soup").item(0).getTextContent()));
                                foods3.add(new Food(element.getElementsByTagName("Salad").item(0).getTextContent()));

                                for(int c = 0; c < ratings.size(); c++) {
                                    if(ratings.get(c).getFood().equals(foods3.get(0).getFoodName())) {

                                        rate31.setText(ratings.get(c).toString());

                                    } else if(ratings.get(c).getFood().equals(foods3.get(1).getFoodName())) {

                                        rate32.setText(ratings.get(c).toString());

                                    } else if(ratings.get(c).getFood().equals(foods3.get(2).getFoodName())) {

                                        rate33.setText(ratings.get(c).toString());

                                    } else if(ratings.get(c).getFood().equals(foods3.get(3).getFoodName())) {

                                        rate34.setText(ratings.get(c).toString());

                                    }
                                }


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

                    yolo1.setText(foods1.get(0).toString());
                    yolo2.setText(foods1.get(1).toString());
                    yolo3.setText(foods1.get(2).toString());
                    yolo4.setText(foods1.get(3).toString());

                    laseri1.setText(foods2.get(0).toString());
                    laseri2.setText(foods2.get(1).toString());
                    laseri3.setText(foods2.get(2).toString());
                    laseri4.setText(foods2.get(3).toString());

                    lut1.setText(foods3.get(0).toString());
                    lut2.setText(foods3.get(1).toString());
                    lut3.setText(foods3.get(2).toString());
                    lut4.setText(foods3.get(3).toString());

                }
            }
        };

    }

}

