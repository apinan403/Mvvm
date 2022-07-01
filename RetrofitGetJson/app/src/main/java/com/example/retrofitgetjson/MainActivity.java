package com.example.retrofitgetjson;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername, txtPass;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    boolean isAllFieldsChecked = false;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);

        String jsonOB = "{\n" +
                    "  \"module\" : \"Authentication\",\n" +
                    "  \"traget\" : \"Login\",\n" +
                    "  \"data\" : {\n" +
                    "    \"username\" : \"user1\",\n" +
                    "    \"password\" : \"ajspfjasoghoa\"\n" +
                    "  }\n" +
                    "}";


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String module, traget, username, password;
                String inputUname = txtUsername.getText().toString();
                String inputPassword = txtPass.getText().toString();
                Intent dataIntent = new Intent(MainActivity.this, HomePageActivity.class);

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {

                    try {
                        JSONObject bindingJsonData = new JSONObject(jsonOB);
                        module = bindingJsonData.getString("module");
                        traget = bindingJsonData.getString("traget");

                        JSONObject unData = bindingJsonData.getJSONObject("data");
                        unData.put("username", inputUname);
                        unData.put("password", inputPassword);
                        username = unData.getString("username");
                        password = unData.getString("password");

                        dataIntent.putExtra("Module", module);
                        dataIntent.putExtra("Traget", traget);
                        dataIntent.putExtra("Username", username);
                        dataIntent.putExtra("Password", password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
    //                createPost();
                    startActivity(dataIntent);
                }

            }

        });

        Log.d("Log", "Hello");

    }


    private boolean CheckAllFields() {

        String inputPassword = txtPass.getText().toString();
        if (txtUsername.length() == 0) {
//            toast = Toast.makeText ( getApplicationContext(), "Please Enter Username!!", toast.LENGTH_LONG );
//            toast.show();
            Toast.makeText(getApplicationContext(), "Please Enter Username!!", Toast.LENGTH_LONG ).show();
            return false;
        } else if (txtUsername.length() < 5) {
            toast = Toast.makeText ( getApplicationContext(), "Username must be minimum 5 characters!! ", toast.LENGTH_LONG );
            toast.show();
            return false;
        }

        if (txtPass.length() == 0) {
            toast = Toast.makeText ( getApplicationContext(), "Please Enter Password!!", toast.LENGTH_LONG );
            toast.show();
            return false;
        } else if (txtPass.length() < 8) {
            toast = Toast.makeText ( getApplicationContext(), "Password must be minimum 8 characters", toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!isValidPassword(inputPassword)) {
            toast = Toast.makeText ( getApplicationContext(), "Invalidation Password!!", toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        // after all validation return true.
        return true;
    }

    public boolean isValidPassword(String password){
        final String PassPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]) \\w{8,}$";

        Pattern pattern = Pattern.compile(PassPattern);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
//    private void createPost() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiInterface requestApiIntf = retrofit.create(ApiInterface.class);
//
//        Post posts = new Post("Authentication", "Login" );
//
//        Call<Post> call = requestApiIntf.createUser(posts);
//
//        call.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if (response.isSuccessful()) {
//                    return;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });
//
//    }
