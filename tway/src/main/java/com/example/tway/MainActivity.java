package com.example.tway;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    CircleImageView babyFace;
    Button imgAddBtn;
    Button insertBtn;
    EditText editTName;
    TextView yearText;
    TextView monthText;
    TextView dayText;
    RadioGroup gender;
    RadioGroup blood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //insert
        //form
        yearText = findViewById(R.id.yearText);
        monthText = findViewById(R.id.monthText);
        dayText = findViewById(R.id.dayText);

        Calendar cal = Calendar.getInstance();
        yearText.setHint("예) " + cal.get(Calendar.YEAR));
        monthText.setHint("예) " + cal.get(Calendar.MONTH));
        dayText.setHint("예) " + cal.get(Calendar.DATE));

        insertBtn = findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info_insert();
                //홈화면
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);

            }
        });

        //image add
        babyFace = findViewById(R.id.babyFace);
        imgAddBtn = findViewById(R.id.imgAddBtn);
        imgAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupAlertDialog();
            }
        });
    }

    //아기정보저장
    public void info_insert() {
        editTName = findViewById(R.id.nameText) ;
        String name = editTName.getText().toString() ;

        String year = yearText.getText().toString();
        String month = monthText.getText().toString();
        String day = dayText.getText().toString();
        String birth= year + "-" + month + "-" + day;

        InfoVO vo = new InfoVO();
        vo.setBaby_name(name);
        vo.setBirthday(birth);

        //성별
        gender = findViewById(R.id.gender);
        int gid = gender.getCheckedRadioButtonId();
        RadioButton gd = findViewById(gid);
        vo.setGender(gd.getText().toString());

        //혈액형
        blood = findViewById(R.id.blood);
        int bid = blood.getCheckedRadioButtonId();
        RadioButton bd = findViewById(bid);
        vo.setAbo(bd.getText().toString());


        //이미지 맵의 비트맵가져오기
        BitmapDrawable drawable = (BitmapDrawable) babyFace.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //bitmap to String
        String face_bite = BitmapToString(bitmap);
        vo.setBaby_face(face_bite);


        new InfoDAO().insert(getApplicationContext(), vo);

    }

    //갤러리와 카메라 중 선택
    public void popupAlertDialog(){
        final CharSequence[] items = {"갤러리", "카메라"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this
        // 속성 설정
        builder.setTitle("사진가져오기")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index){
                        Toast.makeText(getApplicationContext(), items[index], Toast.LENGTH_SHORT).show();

                        if(index == 0){
                            openGallery();
                        } else if(index == 1){
                            dispatchTakePictureIntent();
                        }

                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    //갤러리 선택 후 실행할 메서드
    //갤러리 앱 열기
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT); //앱여는 엑티비티이름
        startActivityForResult(intent, 101); // 결과값 가져오는거 안에 숫자는 내가 보낸 그것인지 확인하는 용도
    }


    //카메라 선택 후 실행할 메서드
    //카메라 앱 열기
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.tway.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }
    }

    //onActivityResult : A에서 B로 갔다가 다시 A로 넘어올 때 사용하는, 안드로이드에서 제공하는 기본 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //갤러리에서 선택한 이미지를 가져오기
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Uri fileUri= data.getData();
            ContentResolver resolver = getContentResolver();
            try{
                InputStream instream = resolver.openInputStream(fileUri);
                Bitmap imageBitmap = BitmapFactory.decodeStream(instream);
                babyFace.setImageBitmap(imageBitmap);
                instream.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        //카메라앱에서 찍은 이미지 가져오기
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic(); //imageView에 이미지 표시
            galleryAddPic(); //다른앱에서 접근할 수 있도록 broadcast
        }
    }
    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("sample camera ", currentPhotoPath );
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = babyFace.getWidth();
        int targetH = babyFace.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        babyFace.setImageBitmap(bitmap);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }



    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }

    @Override // 수락하고나면 이거뒤에 Denied Granted 진행됨
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }
}