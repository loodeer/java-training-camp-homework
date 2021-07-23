package com.loodeer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author luzuheng
 * @since 2021-07-21 09:13
 */
public class SimpleJDKDynmicProxyDemo {
    interface IService {
        void sayHello();
    }

    static class RealService implements IService {
        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class SimpleInvocationHandler implements InvocationHandler {
        private Object realObj;

        public SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        IService realService = new RealService();

        IService proxyService = (IService) Proxy.newProxyInstance(
                IService.class.getClassLoader(),
                new Class<?>[]{IService.class},
                new SimpleInvocationHandler(realService)
        );
        proxyService.sayHello();

//        Class<?> proxyClass = Proxy.getProxyClass(IService.class.getClassLoader(), new Class<?>[] {IService.class});
//        Constructor<?> ctor = proxyClass.getConstructor(new Class<?>[]{InvocationHandler.class});
//        SimpleInvocationHandler handler = new SimpleInvocationHandler(realService);
//        IService proxy = (IService) ctor.newInstance(handler);
//        proxy.sayHello();
    }
}
