package com.lijiankun24.iocpractice.ioc;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * ButterKnifeDynamicHandler.java
 * <p>
 * Created by lijiankun on 18/3/21.
 */

class ButterKnifeDynamicHandler implements InvocationHandler {

    private WeakReference<Object> mObjectWR = null;

    private final HashMap<String, Method> mMethodHashMap = new HashMap<>();

    ButterKnifeDynamicHandler(Object object) {
        mObjectWR = new WeakReference<>(object);
    }

    void addMethod(String methodName, Method method) {
        mMethodHashMap.put(methodName, method);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object handler = mObjectWR.get();
        String name = method.getName();
        method = mMethodHashMap.get(name);
        if (handler != null && method != null) {
            return method.invoke(mObjectWR.get(), args);
        }
        return null;
    }
}
