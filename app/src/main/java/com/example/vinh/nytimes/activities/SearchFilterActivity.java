package com.example.vinh.nytimes.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vinh.nytimes.DatePickerFragment;
import com.example.vinh.nytimes.Filter;
import com.example.vinh.nytimes.R;

import org.parceler.Parcels;

import java.util.Calendar;

public class SearchFilterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar currentDate;

    private TextView tvBeginDate;
    private Spinner spSortOrder;
    private CheckBox cbArts;
    private CheckBox cbFashionStyle;
    private CheckBox cbSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        tvBeginDate = (TextView)findViewById(R.id.text_begin_date);
        spSortOrder = (Spinner)findViewById(R.id.spinner_sort_order);
        cbArts = (CheckBox)findViewById(R.id.checkbox_arts);
        cbFashionStyle = (CheckBox)findViewById(R.id.checkbox_fashion_style);
        cbSports = (CheckBox)findViewById(R.id.checkbox_sports);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Article Filtering"); // set the top title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentDate = Calendar.getInstance();
        String curentday = currentDate.get(currentDate.DAY_OF_MONTH) >= 10 ?
                String.valueOf(currentDate.get(currentDate.DAY_OF_MONTH)) :
                (String) "0" + currentDate.get(currentDate.DAY_OF_MONTH);
        String curentmonth = currentDate.get(currentDate.MONTH) + 1 >= 10 ?
                String.valueOf(currentDate.get(currentDate.MONTH) + 1) :
                (String) "0" + (currentDate.get(currentDate.MONTH) + 1);
        String curentYear = String.valueOf(currentDate.get(currentDate.YEAR));

        tvBeginDate.setText(curentmonth + "/" + curentday +  "/" + curentYear);

        cbArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbArts.isChecked()) {
                    cbFashionStyle.setChecked(false);
                    cbSports.setChecked(false);
                }
            }
        });

        cbFashionStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbFashionStyle.isChecked()) {
                    cbArts.setChecked(false);
                    cbSports.setChecked(false);
                }
            }
        });

        cbSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbSports.isChecked()) {
                    cbFashionStyle.setChecked(false);
                    cbArts.setChecked(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_filter, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_done:

                if (currentDate != null) {

                    int day = currentDate.get(currentDate.DAY_OF_MONTH);
                    int month = currentDate.get(currentDate.MONTH) + 1;
                    int year = currentDate.get(currentDate.YEAR);

                    String sortOrder = spSortOrder.getSelectedItem().toString();

                    int isArts = cbArts.isChecked() ? 1 : 0;
                    int isFashionStyle = cbFashionStyle.isChecked() ? 1 : 0;
                    int isSport = cbSports.isChecked() ? 1 : 0;

                    Filter filter = new Filter(day, month, year, sortOrder, isArts, isFashionStyle, isSport);

                    Intent data = new Intent();
                    data.putExtra("filter", Parcels.wrap(filter));
                    setResult(RESULT_OK, data);
                }

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // attach to an onclick handler to show the date picker
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, year);
        currentDate.set(Calendar.MONTH, monthOfYear);
        currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String curentday = currentDate.get(currentDate.DAY_OF_MONTH) >= 10 ?
                String.valueOf(currentDate.get(currentDate.DAY_OF_MONTH)) :
                (String) "0" + currentDate.get(currentDate.DAY_OF_MONTH);
        String curentmonth = currentDate.get(currentDate.MONTH) + 1 >= 10 ?
                String.valueOf(currentDate.get(currentDate.MONTH) + 1) :
                (String) "0" + (currentDate.get(currentDate.MONTH) + 1);
        String curentYear = String.valueOf(currentDate.get(currentDate.YEAR));

        tvBeginDate.setText(curentmonth + "/" + curentday +  "/" + curentYear);
    }

    public void showCalendar(View view) {
        showDatePickerDialog(view);
    }
}
