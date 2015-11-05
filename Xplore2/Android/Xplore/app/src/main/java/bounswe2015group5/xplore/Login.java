package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * The Class Login is an Activity class that shows the login screen to users.
 * The current implementation simply start the MainActivity.
 */
public class Login extends Activity {

	/* UI references */
	private EditText edtMail, edtPass;
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
	}

	private void attempLogin() {

		String email = edtMail.getText().toString();
		String pass = edtPass.getText().toString();

		if(email.equals("admin") && pass.equals("admin"))
			startActivity(new Intent(Login.this, MainActivity.class));

	}

}
