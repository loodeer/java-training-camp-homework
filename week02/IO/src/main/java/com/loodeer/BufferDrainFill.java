package com.loodeer;

import java.nio.CharBuffer;

/**
 * @author luzuheng
 * @since 2021-07-03 08:41
 */
public class BufferDrainFill {

    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };

    public static void main(String[] argv) {

        CharBuffer buffer1 = CharBuffer.allocate(10);
        buffer1.put('a');
        buffer1.put('b');
        buffer1.put('c');

        CharBuffer buffer2 = CharBuffer.allocate(10);
        buffer2.put('a');
        buffer2.put('b');
        buffer2.put('c');

        System.out.println(buffer1.equals(buffer2));
        System.out.println(buffer1.compareTo(buffer2));

        char c = buffer2.get();

        System.out.println(c);

        System.out.println(buffer1.equals(buffer2));

        System.out.println(buffer1.compareTo(buffer2));



        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }

        return (true);
    }

}

