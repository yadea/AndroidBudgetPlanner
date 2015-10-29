package com.yadea.budgetplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;

import com.yadea.budgetplanner.common.CashbookDataSource;
import com.yadea.budgetplanner.model.Cashbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashbookAddActivity extends AppCompatActivity {
    RadioButton radExpense;
    RadioButton radIncome;
    AutoCompleteTextView txtTitle;
    EditText txtCategory;
    EditText txtDate;
    EditText txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashbook_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radExpense = (RadioButton) findViewById(R.id.radExpense);
        radIncome = (RadioButton) findViewById(R.id.radIncome);
        txtTitle = (AutoCompleteTextView) findViewById(R.id.txtTitle);
        txtCategory = (EditText) findViewById(R.id.txtCategory);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_cashbook_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_save) {
            //// TODO: 27/10/2015 add save feature
            boolean isExpense = radExpense.isChecked();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Cashbook cashEntry = new Cashbook(isExpense, txtTitle.getText().toString(), txtCategory.getText().toString(), dateFormatter.parse(txtDate.getText().toString()), Double.parseDouble(txtAmount.getText().toString()));
                CashbookDataSource dataSource = new CashbookDataSource(this);
                dataSource.newEntry(cashEntry);
            }
            catch (ParseException ex) {
                Log.e("Parse Exception: ", ex.toString());
            }
            catch (Exception ex) {
                Log.e("Exception: ", ex.toString());
            }

                finish();
            return true;
        }
        else if (id == R.id.action_save_add) {
            // // TODO: 27/10/2015 add save feature
            Intent intent = new Intent(this,CashbookAddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
