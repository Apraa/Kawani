package com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.login.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class SecondFragment extends Fragment {
    public Authentication authen = new Authentication();
    public String email = authen.getEmail();



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(),email,Toast.LENGTH_SHORT).show();
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
//
//        final Query userQuery = myRef.orderByChild("Email");
//
//
//        userQuery.addChildEventListener(new ChildEventListener() {
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//
//
//                UserParent = dataSnapshot.getKey();
//
//                Toast.makeText(getContext(),UserParent,Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onChildChanged( DataSnapshot snapshot, String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });

//        UserInfoData.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.child(UserInfo[0]).getValue(String.class);
//                Toast.makeText(getContext(),value,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}