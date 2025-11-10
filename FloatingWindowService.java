package com.taobao.autobuy;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FloatingWindowService extends Service {
    
    private WindowManager windowManager;
    private View floatingView;
    private WindowManager.LayoutParams params;
    
    private Button btnStartAutoBuy;
    private Button btnStopAutoBuy;
    private Button btnClose;
    private TextView tvStatus;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        createFloatingWindow();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    
    private void createFloatingWindow() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        // 创建悬浮窗口布局参数
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;
        
        // 加载悬浮窗口布局
        LayoutInflater inflater = LayoutInflater.from(this);
        floatingView = inflater.inflate(R.layout.floating_window, null);
        
        initFloatingViews();
        setupFloatingClickListeners();
        
        windowManager.addView(floatingView, params);
    }
    
    private void initFloatingViews() {
        btnStartAutoBuy = floatingView.findViewById(R.id.btn_start_auto_buy);
        btnStopAutoBuy = floatingView.findViewById(R.id.btn_stop_auto_buy);
        btnClose = floatingView.findViewById(R.id.btn_close);
        tvStatus = floatingView.findViewById(R.id.tv_status);
    }
    
    private void setupFloatingClickListeners() {
        btnStartAutoBuy.setOnClickListener(v -> startAutoBuyService());
        btnStopAutoBuy.setOnClickListener(v -> stopAutoBuyService());
        btnClose.setOnClickListener(v -> stopSelf());
        
        // 添加拖动功能
        floatingView.setOnTouchListener(new View.OnTouchListener() {
            private int initialX, initialY;
            private float initialTouchX, initialTouchY;
            
            @Override
            public boolean onTouch(View v, android.view.MotionEvent event) {
                switch (event.getAction()) {
                    case android.view.MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case android.view.MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(floatingView, params);
                        return true;
                }
                return false;
            }
        });
    }
    
    private void startAutoBuyService() {
        Intent autoBuyIntent = new Intent(this, TaobaoAutoBuyService.class);
        startService(autoBuyIntent);
        tvStatus.setText("状态: 自动抢购已启动");
        Toast.makeText(this, "自动抢购服务已启动", Toast.LENGTH_SHORT).show();
    }
    
    private void stopAutoBuyService() {
        Intent autoBuyIntent = new Intent(this, TaobaoAutoBuyService.class);
        stopService(autoBuyIntent);
        tvStatus.setText("状态: 自动抢购已停止");
        Toast.makeText(this, "自动抢购服务已停止", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingView != null) {
            windowManager.removeView(floatingView);
        }
    }
}