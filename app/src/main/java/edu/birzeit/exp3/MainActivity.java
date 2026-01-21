package edu.birzeit.exp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout secondLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout firstLinearLayout = new LinearLayout(this);
        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);

        Button addButton = new Button(this);
        addButton.setText("Add Customer");
        addButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        secondLinearLayout = new LinearLayout(this);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(secondLinearLayout);

        firstLinearLayout.addView(addButton);
        firstLinearLayout.addView(scrollView);

        setContentView(firstLinearLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "EXP4", null, 1);
        Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
        secondLinearLayout.removeAllViews();
        while (allCustomersCursor.moveToNext()) {
            TextView textView = new TextView(this);
            textView.setText(
                    "Id=" + allCustomersCursor.getString(0) +
                            "\nName=" + allCustomersCursor.getString(1) +
                            "\nPhone=" + allCustomersCursor.getString(2) +
                            "\nGender=" + allCustomersCursor.getString(3) +
                            "\n\n"
            );
            secondLinearLayout.addView(textView);
        }
    }
}