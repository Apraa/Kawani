package com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UserProfile extends Fragment{
    String email;
    String FirstName;
    String LastName;
    String Position;
    String Department;
    String IDNumber;
    TextView FullName;
    TextView ShowDepartment;
    TextView ShowEmail;
    TextView ShowID;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FullName = view.findViewById(R.id.FullName);
        ShowDepartment = view.findViewById(R.id.ShowDepartment);
        ShowEmail = view.findViewById(R.id.ShowEmail);
        ShowID = view.findViewById(R.id.showID);

        if (user != null) {
            // Name, email address, and profile photo Url
            email = user.getEmail();


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");

        final Query userQuery = myRef.orderByChild("Email").equalTo(email);

        //finds the parent based on email (child)
        userQuery.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Information from real time database
                IDNumber = dataSnapshot.getKey();
                FirstName = dataSnapshot.child("FirstName").getValue(String.class);
                LastName = dataSnapshot.child("LastName").getValue(String.class);
                Position = dataSnapshot.child("Position").getValue(String.class);
                Department = dataSnapshot.child("Department").getValue(String.class);
                FullName.setText(FirstName +" " + LastName);
                ShowDepartment.setText(Department);
                ShowEmail.setText("Email:    " +email);
                ShowID.setText("ID:     " + IDNumber);
            }

            @Override
            public void onChildChanged( DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserProfile.this)
                        .navigate(R.id.QrProfile);
            }
        });
        view.findViewById(R.id.genQR2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vc) {
                NavHostFragment.findNavController(UserProfile.this)
                        .navigate(R.id.GenQR);
            }
        });
        view.findViewById(R.id.Location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserProfile.this)
                        .navigate(R.id.GpsQ);
            }
        });

    }
}