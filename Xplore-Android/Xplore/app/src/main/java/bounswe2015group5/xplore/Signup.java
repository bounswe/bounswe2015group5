package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * This class is created by Mert Oguz on 06/11/2015.
 */
public class Signup extends Activity {

    /**
     * UI references
     * @author Mert Oguz
     */
    private EditText edtMail, edtUsername, edtPass, edtPassRetype;
    private TextView guestLogin, loginText;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        edtMail     = (EditText) findViewById(R.id.signup_email);
        edtUsername = (EditText) findViewById(R.id.signup_name);
        edtPass     = (EditText) findViewById(R.id.signup_pass);
        edtPassRetype = (EditText) findViewById(R.id.signup_repass);

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

        final String email      = edtMail.getText().toString();
        final String username   = edtUsername.getText().toString();
        final String pass       = edtPass.getText().toString();
        final String pass_retype = edtPassRetype.getText().toString();

        if(!pass.equals(pass_retype)) {
            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()||username.isEmpty()||pass.isEmpty()||pass_retype.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all of the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Response.Listener<JSONObject> responseListener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.length() > 0){ // Server replies with "Success"

                            SharedPreferences.Editor editor = Globals.share.edit();
                            editor.putBoolean("SignedIn", true);
                            editor.putString("email",email);
                            editor.putString("username",username);
                            editor.apply();

                            Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, MainActivity.class));
                            finishAffinity();
                        } else //unsuccessful register attempt
                            Toast.makeText(getApplicationContext(), "Unsuccessful Register Attempt", Toast.LENGTH_SHORT).show();
                    }
                };


        Globals.connectionManager.registerUser(email, pass, username, responseListener);
    }

}
