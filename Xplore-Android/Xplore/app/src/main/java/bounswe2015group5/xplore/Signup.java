package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is created by Mert Oguz on 06/11/2015.
 */
public class Signup extends Activity {

    /**
     * UI references
     * @author Mert Oguz
     */
    private EditText edtMail, edtName, edtSurname, edtPass, edtPassRetype;
    private TextView guestLogin, loginText;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        edtMail = (EditText) findViewById(R.id.signup_email);
        edtName = (EditText) findViewById(R.id.signup_name);
        edtSurname = (EditText) findViewById(R.id.signup_surname);
        edtPass = (EditText) findViewById(R.id.signup_pass);
        edtPassRetype = (EditText) findViewById(R.id.signup_pass_retype);

        signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        guestLogin = (TextView) findViewById(R.id.guestLogin);
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAsGuest();
            }
        });

        loginText = (TextView) findViewById(R.id.signup_login);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginPage();
            }
        });

    }

    private void goToLoginPage() {
        startActivity(new Intent(Signup.this, Login.class));
        finishAffinity();
    }

    private void LoginAsGuest() {
        startActivity(new Intent(Signup.this, MainActivity.class));
        finish();
    }

    /**
     * Sign-Up function. Checks if the given inputs are okay.
     * @author Mert Oguz
     */
    private void attemptSignup() {

        final String email = edtMail.getText().toString();
        final String name = edtName.getText().toString();
        final String surname = edtSurname.getText().toString();
        final String pass = edtPass.getText().toString();
        final String pass_retype = edtPassRetype.getText().toString();
        final String URL = getString(R.string.service_url) + "RegisterUser"; //for POST to server

        if(!pass.equals(pass_retype)) {
            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()||name.isEmpty()||surname.isEmpty()||pass.isEmpty()||pass_retype.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all of the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        //Signup successfull
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(response.toLowerCase().contains("succes")){ // Server replies with "Success"
                            SharedPreferences.Editor editor = Globals.share.edit();
                            editor.putBoolean("signedIn", true);
                            editor.putString("email",email);
                            editor.apply();

                            Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, MainActivity.class));
                            finishAffinity();
                        } else //unsuccessful register attempt
                            Toast.makeText(getApplicationContext(), "Unsuccessful Register Attempt", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG", error.toString());
            }
        }){
            /**
             * Puts arguments for POST that will be sent to the server
             * @auth Mert Oguz
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mParams = new HashMap<String, String>();
                mParams.put("email", email);
                mParams.put("pass", pass);
                mParams.put("name", name);
                mParams.put("surname", surname);

                return mParams;
            }
        };
        Globals.mRequestQueue.add(stringRequest);
    }

}
