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
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class Login is an Activity class that shows the login screen to users.
 * The current implementation simply start the MainActivity.
 */
public class Login extends Activity {

	private EditText edtMail, edtPass;
	private TextView guestLogin, signUp;
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		XploreApp.initInstance(getApplicationContext());

		setContentView(R.layout.login);

		edtMail = (EditText) findViewById(R.id.login_email);
		edtPass = (EditText) findViewById(R.id.login_pass);

		loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String email = edtMail.getText().toString(),
						pass = edtPass.getText().toString();

				attempLogin(email, pass);
			}
		});

		guestLogin = (TextView) findViewById(R.id.guestLogin);
		guestLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loginAsGuest();
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

	private void loginAsGuest() { startActivity(new Intent(Login.this, MainActivity.class)); }
	private void attemptSignup() { startActivity(new Intent(Login.this, Signup.class)); }

	private void attempLogin(final String email, final String pass) {

		Response.Listener<JSONObject> responseListener =
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						if(response.toString().length() > 0){

							try {
								SharedPreferences.Editor editor = Globals.share.edit();
								editor.putBoolean("SignedIn", true);
								editor.putString("username", response.getString("username"));
								editor.putString("password", response.getString("password"));
								editor.apply();

//								getUserInfo();

								startActivity(new Intent(Login.this, MainActivity.class));
								finishAffinity();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						} else
							Toast.makeText(Globals.appContext, "Unsuccessful Attempt", Toast.LENGTH_SHORT).show();
					}
				};

		Response.ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(Globals.appContext, "Something gone wrong. Please, try again later.", Toast.LENGTH_SHORT).show();
			}
		};

		Globals.connectionManager.login(email, pass, responseListener, errorListener);

	}

	// User Info is retrieved while logging in.
//	public void getUserInfo(){
//		//Globals.connectionManager.getUserInfo(Login.this);
//	}
}
