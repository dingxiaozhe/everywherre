package com.example.administrator.everywherretrip.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.bean.UpLoadBean;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.example.administrator.everywherretrip.widget.GlideApp;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HeadActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    private static final int CAMERA_CODE = 100;
    private static final int ALBUM_CODE = 200;
    @BindView(R.id.img_replaces)
    ImageView imgReplaces;
    @BindView(R.id.mImg_popup)
    ImageView mImgPopup;
    @BindView(R.id.mTool_head)
    Toolbar mToolHead;
    @BindView(R.id.mImg_head)
    ImageView mImgHead;
    @BindView(R.id.tv_out)
    TextView tvOut;
    private PopupWindow mWindow;
    private File mFile;
    private Uri mImageUri;
    private static final String TAG = "HeadActivity";

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {

        mToolHead.setTitle("");
        setSupportActionBar(mToolHead);

        Intent intent = getIntent();
        String img = intent.getStringExtra("img");

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhanweitu_touxiang);
        Glide.with(this).load(img).apply(options).into(mImgHead);
        //Glide 圆形图片
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(true);//不做内存缓存
        GlideApp.with(this).load(R.mipmap.d).apply(mRequestOptions).into(mImgPopup);

    }

    @OnClick({R.id.img_replaces, R.id.mImg_popup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replaces:
                startActivity(new Intent(HeadActivity.this, MessageActivity.class));
                finish();
                break;
            case R.id.mImg_popup:
                initpopupPhoto();
                break;
        }
    }

    private void initpopupPhoto() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popup_photo, null);
        mWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv_no = view.findViewById(R.id.mTv_noss);
        TextView tv_camera = view.findViewById(R.id.mTv_camera);
        TextView tv_photo = view.findViewById(R.id.mTv_photo);

        mWindow.setBackgroundDrawable(new ColorDrawable());
        mWindow.setOutsideTouchable(true);
        //设置除布局外的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭popop
                mWindow.dismiss();
            }
        });
        mWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });

        //相机
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                mWindow.dismiss();
            }
        });
        //相册
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePICK();
                mWindow.dismiss();
            }
        });
    }

    // 相机权限
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    // 相册权限
    private void takePICK() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
                openCamera();
            } else if (requestCode == 200) {
                openAlbum();
            }
        }
    }

    //打开相机
    private void openCamera() {

        //创建文件用于保存图片
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //适配7.0,  等到对应的mImageUri路径地址
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }


    //打开相册
    private void openAlbum() {

        //启动相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }


    //获取回传数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//判断回调成功

            if (requestCode == CAMERA_CODE) {//拍照

                //显示拍照后的图片
                /*try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                //拍照后的图片上传
                uploadFile(mFile);
            } else if (requestCode == ALBUM_CODE) {//相册

                //获取到相册选中后的图片URI路径
                Uri imageUri = data.getData();

                //显示相册选中后的图片
                /*try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                //文件上传，将Uri路径转换为File对象
                //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                File file = getFileFromUri(imageUri, this);

                if (file.exists()) {
                    uploadFile(file);
                }
            }
            showLoading();
        }
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }


    //上传
    private void uploadFile(final File mFile) {
        String url = "http://yun918.cn/study/public/file_upload.php";
        OkHttpClient client = new OkHttpClient.Builder()
                        .build();

                //  file-->RequestBody
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

                // 创建多媒体 请求对象
                MultipartBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("key", "H1808C")
                        .addFormDataPart("file", mFile.getName(), requestBody)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final UpLoadBean upLoadBean = gson.fromJson(string, UpLoadBean.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (upLoadBean != null) {
                                    if (upLoadBean.getCode() == 200) {
                                        Toast.makeText(HeadActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();


                                        String url1 = upLoadBean.getData().getUrl();
                                        SpUtil.setParam(Constants.PHOTO, url1);
                                        Glide.with(HeadActivity.this).load(url1).into(mImgHead);
                                        hideLoading();
                                        Log.e(TAG, "run: " + upLoadBean.getData().getUrl());

                                    } else {
                                        Toast.makeText(HeadActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        });
                    }
                });




    }


}
