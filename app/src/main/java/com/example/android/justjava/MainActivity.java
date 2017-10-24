package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import java.text.NumberFormat;
import java.text.StringCharacterIterator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int mochaQuantity = 0;
    int cappucinoQuantity = 0;
    boolean whippedCream = false;
    boolean chocolate = false;
    boolean marshmallow = false;
    double extra = 0.0;
    String name= "Winsome";

    public void mochaIncrement(View view) {
        mochaQuantity+=1;
        displayMocha(mochaQuantity);
    }

    public void mochaDecrement(View view) {
        if(mochaQuantity!=0){
            mochaQuantity-=1;
        }
        displayMocha(mochaQuantity);
    }

    public void cappucinoIncrement(View view) {
        cappucinoQuantity+=1;
        displayCappucino(cappucinoQuantity);
    }

    public void cappucinoDecrement(View view) {
        if(cappucinoQuantity!=0){
            cappucinoQuantity-=1;
        }
        displayCappucino(cappucinoQuantity);
    }

    public void addWhippedCream(View view){
        if(whippedCream){whippedCream = false; extra = 0.0;}
        else{whippedCream = true; extra = 0.35;}
    }

    public void addChocolate(View view){
        if(chocolate){chocolate = false; extra = 0.0;}
        else{chocolate = true; extra = 0.25;}
    }

    public void addMarshmallows(View view){
        if(marshmallow){marshmallow = false; extra = 0.0;}
        else{marshmallow = true; extra = 0.20;}
    }

    public void submitOrder(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        name = editText.getText().toString();
        String priceMessage = "Name: " + name
                + "\nAdd whipped cream?(Extra 35p) "+ whippedCream
                + "\nAdd chocolate?(Extra 25p) " + chocolate
                + "\nAdd marshmallow?(Extra 20p) " + marshmallow
                + "\nQuantity of Mocha's: "+ mochaQuantity
                + "\nQuantity of Cappucino's: "+ cappucinoQuantity
                + "\nTotal: £" + (mochaQuantity*3+ cappucinoQuantity*2.50+ extra*(mochaQuantity+cappucinoQuantity))
                + "\nThank you!";
        displayMessage(priceMessage);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayMocha(int number){
        TextView mochaTextView = (TextView) findViewById(R.id.mocha_text_view);
        mochaTextView.setText("" + number);
    }

    private void displayCappucino(int number){
        TextView cappucinoTextView = (TextView) findViewById(R.id.cappucino_text_view);
        cappucinoTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}