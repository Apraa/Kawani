package com;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class FirstFragment extends Fragment {

    public EditText EmailAddress;
    public EditText Password;
    public FirebaseAuth fAuth;
    public Authentication authen = new Authentication();







    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmailAddress = view.findViewById(R.id.EmailAddress);
        Password = view.findViewById(R.id.Password);
        fAuth = FirebaseAuth.getInstance();



        // Read from the database
        view.findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //used to sign in
                if (TextUtils.isEmpty(EmailAddress.getText().toString().trim())) {
                    EmailAddress.setError("EmailAddress is required.");
                    Toast.makeText(getActivity(), "Please enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password.getText().toString().trim())) {
                    Password.setError("Password is required.");
                    Toast.makeText(getActivity(), "Please enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                authen.setEmail(EmailAddress.getText().toString().trim());
                authen.setPassword(Password.getText().toString().trim());

                fAuth.signInWithEmailAndPassword(authen.Email, authen.Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {




                            Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                            
                            NavHostFragment.findNavController(FirstFragment.this)
                                    .navigate(R.id.SecondFragment);

                        } else {
                            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        view.findViewById(R.id.RegisterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.RegisterPage);
            }
        });
    }

}