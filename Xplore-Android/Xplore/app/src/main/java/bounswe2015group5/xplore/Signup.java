package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * This class is created by Mert Oguz on 06/11/2015.
 */
public class Signup extends Activity {

    /**
     * UI references
     * @author Mert Oguz
     */
    private EditText edtMail, edtName, edtSurname, edtPass, edtPassRetype;
    private Button signupBtn;
    private TextView guestLogin;
    private TextView loginText;

    public static SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        share = getApplicationContext().getSharedPreferences("appdata", Context.MODE_PRIVATE);

        // If the user signed before, it is directed to main activity.
        if(share.getBoolean("signedIn",false)){
            startActivity(new Intent(Signup.this, MainActivity.class));
            finish();
        }

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
        finish();
    }

    private void LoginAsGuest() {

        startActivity(new Intent(Signup.this, MainActivity.class));

    }

    /**
     * Sign-Up function. Checks if the given inputs are okay.
     * @author Mert Oguz
     */
    private void attemptSignup() {

        String email = edtMail.getText().toString();
        String name = edtName.getText().toString();
        String surname = edtSurname.getText().toString();
        String pass = edtPass.getText().toString();
        String pass_retype = edtPassRetype.getText().toString();
        if(!pass.equals(pass_retype)) {
            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()||name.isEmpty()||surname.isEmpty()||pass.isEmpty()||pass_retype.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all of the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        //Signup successfull
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean("signedIn", true);
        editor.putString("email", email);
        editor.apply();
        Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Signup.this, MainActivity.class));
        finish();
    }

}
