package com.suyal.socialx;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

    EditText userName, userEmail, phoneNumber, userPassword;
    Button signUpButton;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userName = view.findViewById(R.id.editNameSignUp);
        phoneNumber = view.findViewById(R.id.editNumberSignUp);
        userEmail = view.findViewById(R.id.editEmailSignUp);
        userPassword = view.findViewById(R.id.editPassSignUp);

        signUpButton = view.findViewById(R.id.signUpBtn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),
                        userPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user = new User(userName.getText().toString(),userEmail.getText().toString(),
                                            phoneNumber.getText().toString(),userPassword.getText().toString());
                                    String id = task.getResult().getUser().getUid();

                                    database.getReference().child("Users").child(id).setValue(user);

                                    Toast.makeText(getContext(), "User Created Successfully", Toast.LENGTH_SHORT).show();
                                    userName.setText("");
                                    userEmail.setText("");
                                    phoneNumber.setText("");
                                    userPassword.setText("");
                                }else {
                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    userName.setText("");
                                    userEmail.setText("");
                                    phoneNumber.setText("");
                                    userPassword.setText("");
                                }
                            }
                        });
            }
        });


        return view;
    }
}