package com.cloud.xc.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import com.cloud.xc.R;
import com.cloud.xc.model.TestModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflexActivity extends AppCompatActivity {
    Object obj;
    Method[] methodArray;
    private static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demo2);

        float sss = TypedValue.applyDimension(10, TypedValue.COMPLEX_UNIT_DIP, getResources().getDisplayMetrics());
        Log.d(TAG, sss + "");

        Class clz = TestModel.class;
        try {
            Class clzs = Class.forName("TestModel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取类修饰符
        int modifier = clz.getModifiers();
        try {
            obj = clz.newInstance();
            methodArray = obj.getClass().getDeclaredMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Method m : methodArray) {
            System.out.println(TAG + "method:" + m.toString());
            System.out.println(TAG + "修饰符: " + Modifier.toString(m.getModifiers()));
            System.out.println(TAG + "返回值类型: " + m.getReturnType());
            System.out.println(TAG + "方法名称: " + m.getName());
            System.out.println(TAG + "参数类型列表: " + m.getParameterTypes());
            System.out.println(TAG + "****************************************************");

            if (m.getName().equals("addMethod")) {
                try {
                    int result = (int) m.invoke(obj, 2, 2);
                    System.out.println(TAG + "计算结果: " + result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "方法参数的值的获取可能需要用CGLIB解决");
    }

    /**
     * 返回的16进制修饰符
     * <p>
     * The <code>int</code> value representing the <code>public</code>
     * modifier.
     */
    public static final int PUBLIC = 0x00000001;

    /**
     * The <code>int</code> value representing the <code>private</code>
     * modifier.
     */
    public static final int PRIVATE = 0x00000002;

    /**
     * The <code>int</code> value representing the <code>protected</code>
     * modifier.
     */
    public static final int PROTECTED = 0x00000004;

    /**
     * The <code>int</code> value representing the <code>static</code>
     * modifier.
     */
    public static final int STATIC = 0x00000008;

    /**
     * The <code>int</code> value representing the <code>final</code>
     * modifier.
     */
    public static final int FINAL = 0x00000010;

    /**
     * The <code>int</code> value representing the <code>synchronized</code>
     * modifier.
     */
    public static final int SYNCHRONIZED = 0x00000020;

    /**
     * The <code>int</code> value representing the <code>volatile</code>
     * modifier.
     */
    public static final int VOLATILE = 0x00000040;

    /**
     * The <code>int</code> value representing the <code>transient</code>
     * modifier.
     */
    public static final int TRANSIENT = 0x00000080;

    /**
     * The <code>int</code> value representing the <code>native</code>
     * modifier.
     */
    public static final int NATIVE = 0x00000100;

    /**
     * The <code>int</code> value representing the <code>interface</code>
     * modifier.
     */
    public static final int INTERFACE = 0x00000200;

    /**
     * The <code>int</code> value representing the <code>abstract</code>
     * modifier.
     */
    public static final int ABSTRACT = 0x00000400;

    /**
     * The <code>int</code> value representing the <code>strictfp</code>
     * modifier.
     */
    public static final int STRICT = 0x00000800;
}

