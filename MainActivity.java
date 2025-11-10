package com.taobao.autobuy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    
    private static final int REQUEST_CODE_OVERLAY_PERMISSION = 1001;
    
    private Button btnStartService;
    private Button btnStopService;
    private Button btnOpenTaobao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        setupClickListeners();
        
        // 检查悬浮窗口权限
        checkOverlayPermission();
    }
    
    private void initViews() {
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnOpenTaobao = findViewById(R.id.btn_open_taobao);
    }
    
    private void setupClickListeners() {
        btnStartService.setOnClickListener(v -> startFloatingService());
        btnStopService.setOnClickListener(v -> stopFloatingService());
        btnOpenTaobao.setOnClickListener(v -> openTaobaoApp());
    }
    
    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION);
            }
        }
    }
    
    private void startFloatingService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请先授予悬浮窗口权限", Toast.LENGTH_SHORT).show();
            checkOverlayPermission();
            return;
        }
        
        Intent serviceIntent = new Intent(this, FloatingWindowService.class);
        startService(serviceIntent);
        Toast.makeText(this, "悬浮窗口服务已启动", Toast.LENGTH_SHORT).show();
    }
    
    private void stopFloatingService() {
        Intent serviceIntent = new Intent(this, FloatingWindowService.class);
        stopService(serviceIntent);
        Toast.makeText(this, "悬浮窗口服务已停止", Toast.LENGTH_SHORT).show();
    }
    
    private void openTaobaoApp() {
        try {
            // 尝试打开淘宝应用
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.taobao.taobao");
            if (intent != null) {
                startActivity(intent);
            } else {
                // 如果淘宝应用未安装，打开应用商店
                Toast.makeText(this, "未检测到淘宝应用，请先安装", Toast.LENGTH_SHORT).show();
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, 
                    Uri.parse("market://details?id=com.taobao.taobao"));
                startActivity(marketIntent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "打开淘宝应用失败", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "悬浮窗口权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "悬浮窗口权限被拒绝", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}