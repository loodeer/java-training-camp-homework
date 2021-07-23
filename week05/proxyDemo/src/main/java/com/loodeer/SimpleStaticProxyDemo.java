package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-21 09:08
 */
public class SimpleStaticProxyDemo {

    interface IService {
        void sayHello();
    }

    static class RealService implements IService {
        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class TraceProxy implements IService {
        private IService realService;

        public TraceProxy(IService realService) {
            this.realService = realService;
        }

        @Override
        public void sayHello() {
            System.out.println("entering sayHello");
            this.realService.sayHello();
            System.out.println("leaving sayHello");
        }
    }

    public static void main(String[] args) {
        RealService realService = new RealService();
        TraceProxy proxy = new TraceProxy(realService);
        proxy.sayHello();
    }
}
