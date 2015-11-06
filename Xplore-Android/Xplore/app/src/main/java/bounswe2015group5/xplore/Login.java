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

/**
 * The Class Login is an Activity class that shows the login screen to users.
 * The current implementation simply start the MainActivity.
 */
public class Login extends Activity {

	/* UI references */
	private EditText edtMail, edtPass;
	private Button loginBtn;
	private TextView guestLogin;
	private TextView signUp;

	public static SharedPreferences share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		share = getApplicationContext().getSharedPreferences("appdata", Context.MODE_PRIVATE);

		// If the user signed before, it is directed to main activity.
		if(share.getBoolean("signedIn",false)){
			startActivity(new Intent(Login.this, MainActivity.class));
			finish();
		}

		setContentView(R.layout.login);

		edtMail = (EditText) findViewById(R.id.email);
		edtPass = (EditText) findViewById(R.id.pass);

		loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attempLogin();
			}
		});

		guestLogin = (TextView) findViewById(R.id.guestLogin);
		guestLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				LoginAsGuest();
			}
		});

		signUp = (TextView) findViewById(R.id.signUp);
		signUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptSignup();
			}
		});


	}

	private void LoginAsGuest() {

		startActivity(new Intent(Login.this, MainActivity.class));

	}

	private void attemptSignup(){
		startActivity(new Intent(Login.this, Signup.class));
		finish();
	}

	private void attempLogin() {

		String email = edtMail.getText().toString();
		String pass = edtPass.getText().toString();

		if(email.equals("admin") && pass.equals("admin")) {

			SharedPreferences.Editor editor = share.edit();
			editor.putBoolean("signedIn", true);
			editor.putString("email",email);
			editor.apply();

			startActivity(new Intent(Login.this, MainActivity.class));
			finish();
		} else Toast.makeText(getApplicationContext(), "Unsuccessful Attempt", Toast.LENGTH_SHORT).show();

	}

}
