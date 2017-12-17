package com.example.android.justjava;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity{
    double[][] drinks = new double[5][2];
    double[][] food = new double[3][2];
    boolean whippedCreamM = false;
    boolean marshmallowM = false;
    boolean whippedCreamC = false;
    boolean marshmallowC = false;
    double extraM = 0.0;
    double extraC = 0.0;
    String name = "";
    int mochaQ = 0;
    int capQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        for(int i=0; i<drinks.length; i++) drinks[i][1] = 0;
        drinks[0][0] = 3;
        drinks[1][0] = 3.5;
        drinks[2][0] = 2.5;
        drinks[3][0] = 2.3;
        drinks[4][0] = 2;

        for(int m=0; m<food.length; m++) food[m][1] = 0;
        food[0][0] = 1;
        food[1][0] = 2;
        food[2][0] = 1.5;
    }

    public void incrementer(int a, int b){if(drinks[a][b]<9) drinks[a][b]+=1;}
    public void incrementfood(int a, int b){if(food[a][b]<9) food[a][b]+=1;}
    public void decrementer(int a, int b){if(drinks[a][b]!=0) drinks[a][b]-=1;}
    public void decrementfood(int a, int b){if(food[a][b]!=0) food[a][b]-=1;}

    public void mochaIncrement(View view) {
        incrementer(0,1);
        displayMocha(drinks[0][1]);
    }
    public void mochaDecrement(View view) {
        decrementer(0,1);
        displayMocha(drinks[0][1]);
    }

    public void frappeIncrement(View view) {
        incrementer(1,1);
        displayFrappe(drinks[1][1]);
    }
    public void frappeDecrement(View view) {
        decrementer(1,1);
        displayFrappe(drinks[1][1]);
    }

    public void cappucinoIncrement(View view) {
        incrementer(2,1);
        displayCappucino(drinks[2][1]);
    }
    public void cappucinoDecrement(View view) {
        decrementer(2,1);
        displayCappucino(drinks[2][1]);
    }

    public void espressoIncrement(View view) {
        incrementer(3,1);
        displayEspresso(drinks[3][1]);
    }
    public void espressoDecrement(View view) {
        decrementer(3,1);
        displayEspresso(drinks[3][1]);
    }

    public void teaIncrement(View view) {
        incrementer(4,1);
        displayTea(drinks[4][1]);
    }
    public void teaDecrement(View view) {
        decrementer(4,1);
        displayTea(drinks[4][1]);
    }

    public void cookieIncrement(View view){
        incrementfood(0,1);
        displayCookie(food[0][1]);
    }
    public void cookieDecrement(View view){
        decrementfood(0,1);
        displayCookie(food[0][1]);
    }

    public void cakeIncrement(View view){
        incrementfood(1,1);
        displayCake(food[1][1]);
    }
    public void cakeDecrement(View view){
        decrementfood(1,1);
        displayCake(food[1][1]);
    }

    public void doughnutIncrement(View view){
        incrementfood(2,1);
        displayDoughnut(food[2][1]);
    }
    public void doughnutDecrement(View view){
        decrementfood(2,1);
        displayDoughnut(food[2][1]);
    }

    public void addWhippedCreamMocha(View view){
        if(whippedCreamM){whippedCreamM = false; extraM-=0.35;}
        else{whippedCreamM = true; extraM+=0.35;}
    }

    public void addMarshmallowsMocha(View view){
        if(marshmallowM){marshmallowM = false; extraM-=0.2;}
        else{marshmallowM = true; extraM+=0.2;}
    }

    public void addWhippedCreamCap(View view){
        if(whippedCreamC){whippedCreamC = false; extraC-=0.35;}
        else{whippedCreamC = true; extraC+=0.35;}
    }

    public void addMarshmallowsCap(View view){
        if(marshmallowC){marshmallowC = false; extraC-=0.2;}
        else{marshmallowC = true; extraC+=0.2;}
    }

    public double calculateTotal(){
        double total = 0;
        for(int i =0; i<drinks.length; i++) total+=(drinks[i][0]*drinks[i][1]);
        for(int j=0; j<food.length; j++) total+=(food[j][0]*food[j][1]);
        return total;
    }

    public double calculateExtra(){
        double total = calculateTotal();
        if(mochaQ>(drinks[0][1])){total+=(extraM*drinks[0][1]);}
        else total+=(extraM*mochaQ);
        if(capQ>(drinks[2][1])){total+=(extraC*drinks[2][1]);}
        else total+=(extraC*capQ);
        return total;
    }

    public void submitOrder(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText mochaText = (EditText) findViewById(R.id.mochaQuant);
        EditText capText = (EditText) findViewById(R.id.capQuant);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        name = editText.getText().toString();
        String m = mochaText.getText().toString();
        if(!m.equals("")) mochaQ = Integer.parseInt(m);
        String c = capText.getText().toString();
        if(!c.equals("")) capQ = Integer.parseInt(c);
        String priceMessage = "Name: " + name
                + "\nTotal: £" + decimalFormat.format(calculateExtra())
                + "\nThank you!";
        displayMessage(priceMessage);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayMocha(double number){
        TextView mochaTextView = (TextView) findViewById(R.id.mocha_text_view);
        mochaTextView.setText("" + (int)number);
    }

    private void displayFrappe(double number){
        TextView frappeTextView = (TextView) findViewById(R.id.frappe_text_view);
        frappeTextView.setText("" + (int)number);
    }

    private void displayCappucino(double number){
        TextView cappucinoTextView = (TextView) findViewById(R.id.cappucino_text_view);
        cappucinoTextView.setText("" + (int)number);
    }

    private void displayEspresso(double number){
        TextView espressoTextView = (TextView) findViewById(R.id.espresso_text_view);
        espressoTextView.setText("" + (int)number);
    }

    private void displayTea(double number){
        TextView teaTextView = (TextView) findViewById(R.id.tea_text_view);
        teaTextView.setText("" + (int)number);
    }

    private void displayCookie(double number){
        TextView cookieTextView = (TextView) findViewById(R.id.cookie_text_view);
        cookieTextView.setText("" + (int)number);
    }

    private void displayCake(double number){
        TextView cakeTextView = (TextView) findViewById(R.id.cake_text_view);
        cakeTextView.setText("" + (int)number);
    }

    private void displayDoughnut(double number){
        TextView doughnutTextView = (TextView) findViewById(R.id.doughnut_text_view);
        doughnutTextView.setText("" + (int)number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}