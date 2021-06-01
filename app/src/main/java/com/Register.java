package com;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Register extends Fragment {
    EditText EmailAddress;
    EditText AddPassword;
    EditText FirstName;
    EditText LastName;
    EditText IDNumber;
    RadioGroup Position;
    RadioButton PositionChoice;
    RadioGroup Department;
    RadioButton DepartmentChoice;
    int DepartmentID;
    int PositionID;
    FirebaseAuth fAuth;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_register, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmailAddress = view.findViewById(R.id.AddEmailAddress);
        AddPassword = view.findViewById(R.id.AddPassword);
        FirstName = view.findViewById(R.id.AddFirstName);
        LastName = view.findViewById(R.id.AddLastName);
        IDNumber = view.findViewById(R.id.AddIDNumber);
        Position = view.findViewById(R.id.Position);
        Department = view.findViewById(R.id.Department);

        fAuth = FirebaseAuth.getInstance();




        view.findViewById(R.id.registerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentID = Department.getCheckedRadioButtonId();
                PositionID = Position.getCheckedRadioButtonId();
                PositionChoice = view.findViewById(PositionID);
                DepartmentChoice = view.findViewById(DepartmentID);

                    if(TextUtils.isEmpty(EmailAddress.getText().toString().trim()))
                    {
                        EmailAddress.setError("EmailAddress is required.");
                        Toast.makeText(getActivity(), "Please enter an email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(AddPassword.getText().toString().trim()))
                    {
                            AddPassword.setError("Password is required.");
                            Toast.makeText(getActivity(), "Please enter a Password", Toast.LENGTH_SHORT).show();
                            return;
                    }
                    if(AddPassword.getText().toString().trim().length() < 6)
                    {
                        AddPassword.setError("Password should be more than 6 characters long");
                        Toast.makeText(getActivity(), "Password should be more than 6 characters long", Toast.LENGTH_SHORT).show();
                        return;
                    }
                if(TextUtils.isEmpty(FirstName.getText().toString().trim()))
                {
                    FirstName.setError("First Name is required.");
                    Toast.makeText(getActivity(), "Please enter your First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(LastName.getText().toString().trim()))
                {
                    LastName.setError("Last Name is required.");
                    Toast.makeText(getActivity(), "Please enter your Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(IDNumber.getText().toString().trim()))
                {
                    IDNumber.setError("ID Number is required.");
                    Toast.makeText(getActivity(), "Please enter your ID Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(PositionID == -1)
                {

                    Toast.makeText(getActivity(), "Choose a position", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(DepartmentID == -1)
                {

                    Toast.makeText(getActivity(), "Choose a department", Toast.LENGTH_SHORT).show();
                    return;
                }

                    String email = EmailAddress.getText().toString().trim();
                    String password = AddPassword.getText().toString().trim();
                //email and password is saved into authentication
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Account created successfully",Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Failed to create account",Toast.LENGTH_SHORT).show();
                    }
                    }
                });
                //registers all these infos into real time data base
                String IDNumberW = IDNumber.getText().toString().trim();
                String FirstNameW = FirstName.getText().toString().trim();
                String LastNameW = LastName.getText().toString().trim();
                String PositionChoiceW = PositionChoice.getText().toString().trim();
                String DepartmentChoiceW = DepartmentChoice.getText().toString().trim();
                DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference("users");
                UserRef.child(IDNumberW).child("FirstName").setValue(FirstNameW);
                UserRef.child(IDNumberW).child("LastName").setValue(LastNameW);
                UserRef.child(IDNumberW).child("Email").setValue(email);
                UserRef.child(IDNumberW).child("Position").setValue(PositionChoiceW);
                UserRef.child(IDNumberW).child("Department").setValue(DepartmentChoiceW);


            }
        });
        view.findViewById(R.id.BackToLogin).setOnClickListener(view1 -> NavHostFragment.findNavController(Register.this)
                .navigate(R.id.action_RegisterPage_to_FirstFragment));

    }

}