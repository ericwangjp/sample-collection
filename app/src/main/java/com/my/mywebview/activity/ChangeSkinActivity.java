package com.my.mywebview.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dalvik.system.DexClassLoader;

/**
 * 将apk下载下来（本文模拟将apk文件放在assert目录下）后复制到指定位置后加载里面的资源
 * 来达到主apk不更新包实现更新资源的效果（更换皮肤实例）
 */
public class ChangeSkinActivity extends CommonBaseActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btn)
    Button btn;

    private TitleHelper title;
    private static final String pluginApkPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "plugin";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_change_skin);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        changeSkin(pluginApkPath);
    }

    private void changeSkin(String pluginApkPath) {
        downloadApk(pluginApkPath,"plugin.apk");

        PackageManager pm = getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(pluginApkPath+File.separator+"plugin.apk", PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            //得到插件apk的包名，用于dexClassLoader去加载相应的类
            String pkgName  = pkgInfo.applicationInfo.packageName;

            File optimizedDirectoryFile = getDir("dex", MODE_PRIVATE);
            DexClassLoader dexClassLoader = new DexClassLoader(pluginApkPath + File.separator +"plugin.apk", optimizedDirectoryFile.getPath(),
                    null, ClassLoader.getSystemClassLoader());
            try {
                Class<?> clazz = dexClassLoader.loadClass(pkgName + ".R$mipmap");
                Field field = clazz.getDeclaredField("img3");//得到名为img3的这张图片在R文件中对应的域值
                int resId = field.getInt(R.id.class);//得到图片id
                Resources mResources = getPluginResources("plugin.apk");//得到插件apk中的Resource
                if (mResources != null) {
                    //通过插件apk中的Resource得到resId对应的资源
                    Log.i("test","开始换肤");
                    img.setBackgroundDrawable(mResources.getDrawable(resId));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private Resources getPluginResources(String apkName) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            //通过反射调用方法addAssetPath(String path)，将插件Apk文件的添加到AssetManager中，
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginApkPath+File.separator+apkName);
            Resources superRes = this.getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            return mResources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //模拟从服务端下载插件apk的过程，这里只是简单的将apk从assets目录拷贝至apkpluginPath路径
    private void downloadApk(String pluginApkPath, String apkName) {
        File file = new File(pluginApkPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File apk = new File(pluginApkPath + File.separator + apkName);
        try {
            if(apk.exists()){
                Log.i("test","插件apk已存在");
                return;
            }
            //           Log.i("test","开始下载皮肤插件");
            FileOutputStream fos = new FileOutputStream(apk);
            InputStream is = getResources().getAssets().open(apkName);
            BufferedInputStream bis = new BufferedInputStream(is);
            int len = -1;
            byte[] by = new byte[1024];
            while ((len = bis.read(by)) != -1) {
                fos.write(by, 0, len);
                fos.flush();
            }
            fos.close();
            is.close();
            bis.close();
            //           Log.i("test","皮肤插件下载完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
