package com.example.ex103;

import static java.lang.Double.parseDouble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AlertDialog.Builder adb;
    ArrayAdapter<Double> adp;
    ListView lv;
    TextView tx, tx2, tx3, tx4;
    LinearLayout mydialog;
    EditText type, Findex, difference;
    String Stype = "", SFindex = "", Sdifference = "";
    Double[] numbers = new Double[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = findViewById(R.id.tv13);
        tx2 = findViewById(R.id.tv14);
        tx3 = findViewById(R.id.tv15);
        tx4 = findViewById(R.id.tv16);
        lv = findViewById(R.id.listview);
        startlistview();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("credits activity");
        menu.add("MainActivity");
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Stype.equals("a")) {
            double Math1 = Double.valueOf(Double.valueOf(SFindex) + (Double.valueOf(numbers[position])));
            double a1 = parseDouble(String.valueOf(position + 1)) / 2;
            tx4.setText(String.valueOf(Math1 * a1));
            position++;
            tx3.setText(" " + position);
        } else if (Stype.equals("g")) {
            double Math2 = parseDouble(SFindex) * (parseDouble(String.valueOf(Math.pow(parseDouble(Sdifference), position + 1))) - 1);
            tx4.setText(String.valueOf(Math2));
            position++;
            tx3.setText(" " + position);
        }
    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                Stype = type.getText().toString();
                SFindex = Findex.getText().toString();
                Sdifference = difference.getText().toString();
                numbers[0] = Double.valueOf(SFindex);
                if (!Stype.equals("a") && !Stype.equals("g")) {
                    Stype = "a";
                }
                tx.setText(SFindex);
                tx2.setText(Sdifference);
                if (Stype.equals("a")) {
                    for (int i = 1; i < numbers.length; i++) {
                        numbers[i] = numbers[i - 1] + Double.valueOf(Sdifference);
                    }
                } else if (Stype.equals("g")) {
                    for (int i = 1; i < numbers.length; i++) {
                        numbers[i] = numbers[i - 1] * Double.valueOf(Sdifference);
                    }
                }
                lv.setAdapter(adp);
            }
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
        }
    };

    public void btn(View view) {
        /**
         * Activates the custom alert dialog box
         */
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_2, null);
        type = (EditText) mydialog.findViewById(R.id.editTextText);
        Findex = (EditText) mydialog.findViewById(R.id.editTextNumber);
        difference = (EditText) mydialog.findViewById(R.id.editTextNumber2);
        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        mydialog.setBackgroundColor(Color.YELLOW);
        adb.setTitle("Enter Data");
        adb.setMessage("enter the type 'a'(for Arithmetic progression) or 'g' (for Geometric series)");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.show();

    }

    public void clear(View view) {
        /**
         * Clears the values in the custom alert dialog box
         */
        type.setText("");
        Findex.setText("");
        difference.setText("");
    }

    public void startlistview() {
        /**
         * Creates the list view and updates the data
         */
        numbers[0] = 0.0;

        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = 0.0;
        }

        adp = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, numbers);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(this);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("credits activity")) {
            Intent si = new Intent(this, credits_activity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}


