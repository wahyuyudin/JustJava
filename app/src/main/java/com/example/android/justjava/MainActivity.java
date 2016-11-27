package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + is clicked.
     */
    public void increment(View view) {
        if(quantity==100){
            //show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            //Exit this methode early because thete's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the - is clicked.
     */
    public void decrement(View view) {
        if(quantity==1){
            //show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            //Exit this methode early because thete's nothing left to do
            return;
        }
        quantity = quantity - 1;;
        display(quantity);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField =  (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCreeam = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCreeam);
        //calculate the price
        int price = calculatePrice(hasWhippedCreeam,hasChocolate);

        //display order summary
        String priceMessage = createOrderSummary(name, price, hasWhippedCreeam, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * Calculates the price of the order based on the current quantity.
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @return the price
     */
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        //add $1 if the user wants to add whipped cream
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        //add $1 if the user wants to add chocolate
        if(addChocolate){
            basePrice = basePrice +2;
        }
        //calcelate price
        return quantity*basePrice;
    }

    /**
     *
     * Create summary of the order
     * @param name user name
     * @return the priceMessage
     */
    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){

        String priceMessage = "Name : "+ name;
        priceMessage += "\nAdd Whipped Cream? "  + addWhippedCream;
        priceMessage += "\nAdd Chocolate? "  + addChocolate;
        priceMessage += "\nQuantity : "  + quantity;
        priceMessage += "\nTotal  : $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}