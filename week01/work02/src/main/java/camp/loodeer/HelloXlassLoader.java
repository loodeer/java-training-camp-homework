package camp.loodeer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luzuheng
 * @since 2021-06-23 00:05
 */
public class HelloXlassLoader extends ClassLoader {

    public static void main(String[] args)
            throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException,
            IllegalAccessException {

        HelloXlassLoader helloXlassLoader = new HelloXlassLoader();
        Class<?> targetClass = helloXlassLoader.loadClass("Hello");
        Method targetMethod = targetClass.getDeclaredMethod("hello");
        targetMethod.invoke(targetClass.getDeclaredConstructor().newInstance());

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (InputStream inputStream = this.getClass().getResourceAsStream("/Hello.xlass")) {
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            byte[] decodedBytes = decode(bytes);
            return defineClass(name, decodedBytes, 0, length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private byte[] decode(byte[] in) {
        int length = in.length;
        byte[] out = new byte[length];
        for (int i = 0; i < length; i++) {
            out[i] = (byte) (255 - in[i]);
        }
        return out;
    }

}
