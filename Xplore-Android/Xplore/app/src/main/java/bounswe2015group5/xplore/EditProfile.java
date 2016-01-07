package bounswe2015group5.xplore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    EditText name,password,confirmPass;
    String confirmed,newPass,newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = (EditText) findViewById(R.id.changeName);
        password = (EditText) findViewById(R.id.changePassword);
        confirmPass = (EditText) findViewById(R.id.confirmPassword);
        confirmed = confirmPass.getText().toString();
        Button button = (Button) findViewById(R.id.saveProfileButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(confirmPass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Your profile has been updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match. Please type again", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmPass.setText("");
                }

            }
        });

    }
}
