package ACSL2;

public class StringTester {
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("Bikini ");
		s.append("hi");
		System.out.println("hi: " + s.codePointBefore(2));
		System.out.println(s);
		System.out.println(s.length());
		System.out.println(s.charAt(3));
		s.setLength(2);
		System.out.println(s);
		StringBuffer d = new StringBuffer("group");
		int dint = 0;
		for (int i = 0; i < d.length(); i++) {
			dint = d.lastIndexOf("et", i);
		}
		System.out.println(dint);
		d.equals(s.delete(0, 1));
		System.out.println("me: " + s);
		//char AT
	}
}
