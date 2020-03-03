package augustana.androidsignintechtalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Authors: Dylan Hart & Scott Daluga
 * Date: 3 Mar 2020
 * Class: CSC490
 *
 * Description:
 * This class is used to display user data from the authenticated account and allow
 * the ability to sign out.
 */
public class Main2Activity extends AppCompatActivity {

    // XML references
    ImageView img;
    TextView name, email, id;
    Button signOut;

    GoogleSignInClient client; // Client used to interact with Google Sign In API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img = findViewById(R.id.imageView2);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == signOut) {
                        signOut();                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText("Name: " + acct.getDisplayName());
            email.setText("Email: " + acct.getEmail());
            id.setText("Unique email ID: " + personId);
            Glide.with(this).load(String.valueOf(personPhoto)).into(img); // Fancy way of getting image data from Google and displaying
        }

    }

    /**
     * Method used to handle a button click for signing the current user out and return to the parent
     * activity where sign in occurs.
     */
    private void signOut() {
        client.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Main2Activity.this, "Signed out successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
