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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

	private void loginAsGuest() {
		startActivity(new Intent(Login.this, MainActivity.class));
	}

	private void attemptSignup(){
		startActivity(new Intent(Login.this, Signup.class));
	}

	private void attempLogin(final String email, final String pass) {

		String URL = getString(R.string.service_url) + "Login";

		StringRequest loginRequest = new StringRequest(Request.Method.POST, URL,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("LOG",response);
						if(response.toLowerCase().contains("success")){
							SharedPreferences.Editor editor = Globals.share.edit();
							editor.putBoolean("SignedIn", true);
							editor.putString("Email", email);
							editor.putString("Pass", pass);
							editor.apply();

							getUserInfo();

						} else
							Toast.makeText(getApplicationContext(), "Unsuccessful Attempt", Toast.LENGTH_SHORT).show();
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
						Log.d("LOG_error", error.toString());
			}
		}){

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> mParams = new HashMap<String, String>();
				mParams.put("Email", email);
				mParams.put("Password", pass);
				Log.d("LOG_getParams",mParams.toString());
				return mParams;
			}

		};

		Globals.mRequestQueue.add(loginRequest);
	}

	public void getUserInfo(){

		String URL = getString(R.string.service_url) + "UserInfo";
		StringRequest userInfoRequest = new StringRequest(Request.Method.POST, URL,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("LOG_getUserInfo", response);
						if(!response.isEmpty()){ // Server replies with the list of contributions
							try {
								JSONObject jsonObject = new JSONObject(response);
								int ID = jsonObject.getInt("ID");
								String name = jsonObject.getString("Name");
								String surname = jsonObject.getString("Surname");

								SharedPreferences.Editor editor = Globals.share.edit();
								editor.putInt("ID",ID);
								editor.putString("Name",name);
								editor.putString("Surname", surname);
								editor.apply();

								startActivity(new Intent(Login.this, MainActivity.class));
								finish();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						} else //unsuccessful attempt while receiving user info
							Toast.makeText(getApplicationContext().getApplicationContext(), response, Toast.LENGTH_SHORT).show();
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d("LOG", error.toString());
			}
		});

		Globals.mRequestQueue.add(userInfoRequest);
	}
}
