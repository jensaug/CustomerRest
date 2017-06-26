package xmlParsing;

import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamDemo {

	public static void main(String[] args) throws Exception {
		InputStream is = null;
		int i;
		char c;

		try {

			// new input stream created
			is = new FileInputStream("C://Files/sam.xml");

			System.out.println("Characters printed:");

			// reads till the end of the stream
			while ((i = is.read()) != -1) {

				// converts integer to character
				c = (char) i;

				// prints character
				System.out.print(c);
			}

		} catch (Exception e) {

			// if any I/O error occurs
			e.printStackTrace();
		} finally {

			// releases system resources associated with this stream
			if (is != null)
				is.close();
		}
	}
}
