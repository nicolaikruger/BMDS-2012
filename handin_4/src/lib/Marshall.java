package lib;
import javax.xml.bind.*;
import java.io.*;

public class Marshall {
	public static String marshall(Object o) {
		String ret = null;
		try {
			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			StringWriter sw = new StringWriter();
			m.marshal(o, sw);
			ret = sw.toString();
		} catch(JAXBException e) {
			throw new RuntimeException(e);
		}
			return ret;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T unMarshall(InputStream is, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller m = context.createUnmarshaller();
			t = (T) m.unmarshal(is);
		} catch(JAXBException e) {
			throw new RuntimeException(e);
		}
		return t;
	}

	public static <T> T unMarshall(String str, Class<T> c) {
		return unMarshall(new ByteArrayInputStream(str.getBytes()), c);
	}
}
