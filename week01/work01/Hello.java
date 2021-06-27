public class Hello {
	public static void main(String[] args) {
		int num1 = 1;
		double num2 = 2.0D;
		long num3 = 3L;
		byte num4 = 4;
		if ("".length() < 10) {
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
		}
		for (int i = 0; i < num1; i++) {
			System.out.print("四则运算: num1 * num4 = ");
            System.out.println(num1 * num4);		}
	}
}