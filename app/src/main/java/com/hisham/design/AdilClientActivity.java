package com.hisham.design;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faisal.aidlserverapp.IAdd;

/**
 *
 * This project is a demo that how to use ADIL in android. ADIL is basically a way to access services of different project.
 * In this activity we are dealing with client side code means we are going to access service of another app.
 * for service app checkout second part from this link and run in your android phone.
 * https://github.com/faisalkhan1690/AidlServerApp
 *
 */

public class AdilClientActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutOne;
    private TextInputLayout textInputLayoutTwo;
    private EditText etNumberOne;
    private EditText etNumberTwo;
    private TextView tvResult;
    private Button btnCalculate;
    ServiceConnection AddServiceConnection;
    protected IAdd AddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adil_client);

        textInputLayoutOne=(TextInputLayout)findViewById(R.id.textInputLayoutOne);
        textInputLayoutTwo=(TextInputLayout)findViewById(R.id.textInputLayoutTwo);
        etNumberOne=(EditText)findViewById(R.id.et_number_one);
        etNumberTwo=(EditText)findViewById(R.id.et_number_two);
        tvResult=(TextView)findViewById(R.id.tv_result);
        btnCalculate=(Button)findViewById(R.id.btn_calculate);

        textInputLayoutOne.setErrorEnabled(true);
        textInputLayoutTwo.setErrorEnabled(true);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValue()){
                    int num1 = Integer.parseInt(etNumberOne.getText().toString());
                    int num2 = Integer.parseInt(etNumberTwo.getText().toString());
                    try {
                        tvResult.setText("Result:" + AddService.add(num1, num2));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        initConnection();
    }

    /**
     * This method will initialises the connection with service of another app
     */
    void initConnection() {
        AddServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                AddService = null;
                Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                AddService = IAdd.Stub.asInterface(service);
                Toast.makeText(getApplicationContext(), "Addition Service Connected", Toast.LENGTH_SHORT).show();
            }
        };
        if (AddService == null) {
            Intent it = new Intent();
            it.setAction("service.Calculator");

            // binding to remote service
            bindService(it, AddServiceConnection, Service.BIND_AUTO_CREATE);
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        // unbinding to remote service
        unbindService(AddServiceConnection);
    }

    /**
     * This method will check that have you fill all fields or not.
     * @return boolean
     */
    private boolean checkValue() {
        boolean value=true;

        textInputLayoutOne.setError(null);
        textInputLayoutTwo.setError(null);

        if(etNumberOne.getText().toString().equals("")){
            value=false;
            textInputLayoutOne.setError("Please enter a number");
        }
        if(etNumberTwo.getText().toString().equals("")){
            value=false;
            textInputLayoutTwo.setError("Please enter a number");
        }
        return value;
    }


}
