package com.example.lsouza.fuelcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.editText_inputGas) EditText inputGas;
    @Bind(R.id.editText_inputEth) EditText inputEth;
    @Bind(R.id.textView_lblResult)TextView lblResult;
    @BindString(R.string.toast_error) String toastError;

    int mResult;

    public final static String RESULT = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(savedInstanceState != null){
            mResult = savedInstanceState.getInt(RESULT);
            if (mResult != 0) {
                lblResult.setText(mResult);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(RESULT, mResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void btnCompare_click(View view) {
        String str_gasValue = inputGas.getText().toString();
        String str_ethanolValue = inputEth.getText().toString();

        if (str_gasValue.trim().equals("") || str_ethanolValue.trim().equals("") ) {
            Toast.makeText(this, toastError, Toast.LENGTH_SHORT).show();
        }
        else{
            double gasValue = Double.parseDouble(str_gasValue);
            double ethanolValue = Double.parseDouble(str_ethanolValue);

            if((gasValue * 0.7) < ethanolValue) {
                mResult = R.string.lbl_result_eth;

            }else {
                mResult = R.string.lbl_result_Gas;
            }

            lblResult.setText(mResult);
        }
   }
}
