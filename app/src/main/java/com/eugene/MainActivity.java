package com.eugene;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eugene.algorithm.R;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        executor.submit()


    }
}
