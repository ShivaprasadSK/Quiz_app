package com.example.mad;


import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.content.Intent;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button signUpButton;

    // Store registered users
    Map<String, String> registeredUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.username_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        signUpButton = findViewById(R.id.sign_up_button);

        // Initialize the registered users map
        registeredUsers = new HashMap<>();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isPasswordValid(password)) {
            Toast.makeText(this, "Password must contain at least 1 lowercase letter, 1 uppercase letter, and 1 number and be at least 8 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the user is already registered
        if (registeredUsers.containsKey(username)) {
            Toast.makeText(this, "Username already exists. Please choose a different username.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register the user
        registeredUsers.put(username, password);
        Toast.makeText(this, "Registration successful. You can now log in.", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        usernameEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isPasswordValid(String password) {
        // Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 8 characters long
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        return pattern.matcher(password).matches();
    }


    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 8 characters long
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        return pattern.matcher(password).matches();
    }
}