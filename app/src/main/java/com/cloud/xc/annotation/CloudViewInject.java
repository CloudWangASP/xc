package com.cloud.xc.annotation;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by cloud on 2017/7/12.
 */

public class CloudViewInject {
    private static Class<?> activityClass;

    public static void inject(Object obj) {
        activityClass = obj.getClass();
        injectContent(obj);
        injectView(obj);
        injectOnClick(obj);
    }

    private static void injectContent(Object obj) {
        CloudContentView annotation = activityClass.getAnnotation(CloudContentView.class);
        if (annotation != null) {
            int id = annotation.value();
            try {
                Method method = activityClass.getMethod("setContentView", int.class);
                method.invoke(obj, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Object actOrFrag) {
        Field[] declaredFields = null;
        if (activityClass != null) {
            declaredFields = activityClass.getDeclaredFields();
        }

        if (declaredFields != null) {
            for (Field field : declaredFields) {
                CloudFindView annotation = field.getAnnotation(CloudFindView.class);
                if (annotation != null) {
                    int id = annotation.value();
                    Object obj = null;
                    try {
                        obj = activityClass.getMethod("findViewById", int.class).invoke(actOrFrag, id);
                        if (Modifier.PUBLIC != field.getModifiers()) {
                            field.setAccessible(true);
                        }
                        field.set(actOrFrag, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectOnClick(Object actOrFrag) {
        Method[] methods = null;
        methods = activityClass.getMethods();
        for (Method method : methods) {
            CloudClickView annotation = method.getAnnotation(CloudClickView.class);
            if (annotation != null) {
                int[] ids = annotation.value();
                MinvocationHanlder handler = new MinvocationHanlder(actOrFrag, method);
                Object newProxyInstance = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class<?>[]{View.OnClickListener.class}, handler);
                for (int i : ids) {
                    Object view = null;
                    try {
                        view = activityClass.getMethod("findViewById", int.class).invoke(actOrFrag, i);
                        if (view != null) {
                            Method clickMethod = view.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                            clickMethod.invoke(view, newProxyInstance);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class MinvocationHanlder implements InvocationHandler {

        private Object target;
        private Method method;

        public MinvocationHanlder(Object target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return this.method.invoke(target, objects);
        }
    }

}
