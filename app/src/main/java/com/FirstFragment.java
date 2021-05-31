package com;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstFragment extends Fragment {
EditText EmailAddress;
EditText Password;
FirebaseAuth fAuth;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void     onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmailAddress = view.findViewById(R.id.EmailAddress);
        Password = view.findViewById(R.id.Password);
        fAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Cool");

        view.findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(EmailAddress.getText().toString().trim()))
                {
                    EmailAddress.setError("EmailAddress is required.");
                    Toast.makeText(getActivity(), "Please enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Password.getText().toString().trim()))
                {
                    Password.setError("Password is required.");
                    Toast.makeText(getActivity(), "Please enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                String username = EmailAddress.getText().toString().trim();
                String password = Password.getText().toString().trim();
            fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Login successful",Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.SecondFragment);

                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_SHORT).show();
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