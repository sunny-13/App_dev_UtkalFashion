package com.appdev.utkalfashion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "REGISTER_ACTIVITY";

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleClient;
    private final int RC_SIGN_IN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleClient = GoogleSignIn.getClient(this, gso);
        mGoogleClient.revokeAccess();

        findViewById(R.id.skip_button).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        findViewById(R.id.login_button).setOnClickListener(v -> {
            signInWithGoogle();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void signInWithGoogle(){
        Intent signInIntent = mGoogleClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account =
                    completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {
            Log.e(TAG,""+e.getMessage());
            e.printStackTrace();
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential =
                GoogleAuthProvider.getCredential(idToken,
                        null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,
                        task -> {
                            if(task.isSuccessful()){
                                Log.e(TAG, "SignIn Google Successful");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUi(user);
                            } else{
                                Log.e(TAG, "SignIn Google Failed");
                            }
                        });
    }


    private void updateUi(FirebaseUser user){
        //Log.e(TAG, "Going In App with user : " + user.getDisplayName());
        //Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user != null ? user.getDisplayName() : "null");
        this.startActivity(intent);
        this.finish();
    }
}