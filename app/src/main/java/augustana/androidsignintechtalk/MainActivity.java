package augustana.androidsignintechtalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * Authors: Dylan Hart & Scott Daluga
 * Date: 3 Mar 2020
 * Class: CSC490
 *
 * Description:
 * This class is used for the front-end authentication to Google Servers through the Sign-In
 * API.
 */
public class MainActivity extends AppCompatActivity {

    SignInButton signIn; // Reference to the fancy Google button
    GoogleSignInClient client; // Client used to interact with Google Sign In API
    int RC_SIGN_IN = 0; // Request code used to handle the API call to sign in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == signIn) {
                    signIn();

                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        client = GoogleSignIn.getClient(this, gso);

    }

    /**
     * This method handles the pop-up menu for Google Account Sign-In
     */
    private void signIn() {
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * THis method is used to handle the users input from the signIn() method and will
     * send the data to the handleSignInResult() method.
     * @param requestCode - Request code (0) assigned for starting the new activity.
     * @param resultCode - Not used in this example but read here for more info: https://developer.android.com/training/basics/intents/result
     * @param data - User inputted data on the pop-up intent from the signIn() method
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * This method will try to authenticate to Google Servers through the API and
     * sign in the user if the credentials are correct, otherwise it will have them
     * retry.
     * @param completedTask - Parameter used to check authentication based on user input
     *                      to the pop-up intent.
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this, "Sign in failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
