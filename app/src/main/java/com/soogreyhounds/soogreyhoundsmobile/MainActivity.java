package com.soogreyhounds.soogreyhoundsmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mPhotoRecyclerView;
    private final int REQUEST_PHOTO = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
    private class PhotoHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private Photo mPhoto;
        private TextView mTitleTextView;
        public PhotoHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView;
        }
        public void bindPhoto(Photo photo) {
            mPhoto = photo;
            mTitleTextView.setText(photo.getTitle());
        }
        @Override
        public void onClick(View v) {
            Intent editPhotoIntent = new Intent(v.getContext(), PhotoDetailActivity.class);
            editPhotoIntent.putExtra(PhotoDetailActivity.EXTRA_UUID, mPhoto.getUUID());
            startActivityForResult(editPhotoIntent, REQUEST_PHOTO);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PHOTO:
                    updateList();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhotoRecyclerView = findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateList();
        // Button photoDetailButton = findViewById(R.id.photoDetailButton);
       // photoDetailButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
             //   Intent intent = new Intent(getBaseContext(), PhotoDetailActivity.class);
          //      startActivity(intent);
         //   }
        //});

    }
    private void updateList() {
        mPhotoRecyclerView.setAdapter(new PhotoAdapter(PhotoStorage.get(this).getPhotos()));
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        private List<Photo> mPhotos;
        public PhotoAdapter(List<Photo> photos) {
            mPhotos = photos;
        }
        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            TextView textView = new TextView(getBaseContext());
            return new PhotoHolder(textView);
        }
        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position) {
            Photo photo = mPhotos.get(position);
            photoHolder.bindPhoto(photo);
        }
        @Override
        public int getItemCount() {
            return mPhotos.size();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                return true;
            case R.id.create_account:
                Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
                return true;
            case R.id.edit_profile:
                Intent editProfileIntent = new Intent(this, EditProfileActivity.class);
                startActivity(editProfileIntent);
                return true;
            case R.id.add_photo:
                //Photo photo = new Photo();
                //photo.setUUID("234243-dsfsa-23sdf");
                //photo.setTitle("A photo");
                //photo.setURL("https://www.test.com/Test.jpg");
                //photo.setNote("This is a note");
                //PhotoStorage.get(getBaseContext()).addPhoto(photo);
                //updateList();
                Intent addPhotoIntent = new Intent(this, PhotoDetailActivity.class);
                startActivityForResult(addPhotoIntent, REQUEST_PHOTO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

