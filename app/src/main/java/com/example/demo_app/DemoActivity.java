package com.example.demo_app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.demo_app.seller.SellerHomeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class DemoActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EditText nameEditText,addressEditText,Description,Price,Rating,Type,Shop_name;
    private Button mUploadButton;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private FirebaseFirestore db;
    ProgressDialog progressDialog;
    String name ,description , price ,rating ,type ,shop_name ,shop_location;

    private static final int PICK_IMAGE_REQUEST = 1;

    boolean valid = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        nameEditText = findViewById(R.id.pname);
        Description = findViewById(R.id.pdesc);
        Price = findViewById(R.id.pprice);
        Rating = findViewById(R.id.prating);
        Type = findViewById(R.id.ptype);
        Shop_name = findViewById(R.id.pshop_name);
        addressEditText = findViewById(R.id.ploc);
        mImageView = findViewById(R.id.imageView);
        mUploadButton = findViewById(R.id.uploadButton);
        progressDialog = new ProgressDialog(DemoActivity.this);



        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        db = FirebaseFirestore.getInstance();

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mUploadButton.setOnClickListener(new View.OnClickListener() {
            Handler handler = new Handler(){
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(1);
                }
            };
            @Override
            public void onClick(View view) {
                uploadFile();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax()) {
                                Thread.sleep(350);
                                handler.sendMessage(handler.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog.getMax()){
                                    //progressDialog.dismiss();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this)
                    .load(mImageUri)
                    .into(mImageView);
        }

    }

    public boolean checkField(EditText editText) {
        if (nameEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            nameEditText.setError("Please Enter Product Name");
            progressDialog.dismiss();
            valid = false;
        }if (Description.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Description is Empty!", Toast.LENGTH_SHORT).show();
            Description.setError("Please Enter Product Name");
            progressDialog.dismiss();
            valid = false;
        }if (Price.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Price is Empty!", Toast.LENGTH_SHORT).show();
            Price.setError("Please Enter Product Price");
            progressDialog.dismiss();
            valid = false;
        }if (Rating.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Rating is Empty!", Toast.LENGTH_SHORT).show();
            Rating.setError("Please Enter Rating");
            progressDialog.dismiss();
            valid = false;
        }if (Type.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Type is Empty!", Toast.LENGTH_SHORT).show();
            Type.setError("Please Enter Product Type");
            progressDialog.dismiss();
            valid = false;
        }if (Shop_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Shop Name is Empty!", Toast.LENGTH_SHORT).show();
            Shop_name.setError("Please Enter Shop Name");
            progressDialog.dismiss();
            valid = false;
        }if (addressEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Shop Location is Empty!", Toast.LENGTH_SHORT).show();
            addressEditText.setError("Please Enter Shop Location");
            progressDialog.dismiss();
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }

    private void uploadFile() {

        checkField(nameEditText);
        checkField(Description);
        checkField(Price);
        checkField(Rating);
        checkField(Type);
        checkField(Shop_name);
        checkField(addressEditText);

        if (valid) {
            progressDialog.setTitle("Product Details.");
            progressDialog.setMessage("Uploading.....");
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(100);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            if (mImageUri != null) {

                final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
                fileReference.putFile(mImageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {


                                         name = nameEditText.getText().toString().trim();
                                         description = Description.getText().toString().trim();
                                         price = Price.getText().toString().trim();
                                         rating = Rating.getText().toString().trim();
                                         type = Type.getText().toString().trim();
                                         shop_name = Shop_name.getText().toString().trim();
                                         shop_location = addressEditText.getText().toString().trim();


                                        Map<String, Object> upload = new HashMap<>();
                                        upload.put("name", name);
                                        upload.put("img_url", uri.toString());
                                        upload.put("description", description);
                                        upload.put("price", price);
                                        upload.put("rating", rating);
                                        upload.put("type", type);
                                        upload.put("shop_name", shop_name);
                                        upload.put("shop_location", shop_location);

                                        db.collection("Upload")
                                                .add(upload)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Toast.makeText(DemoActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(DemoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DemoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
            } else {
                Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
    }
        private String getFileExtension (Uri uri){
            ContentResolver cR = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cR.getType(uri));
        }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DemoActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }
}