package dk.itu.bmds.teamkolera.src.lib;

import javax.xml.bind.*;
import java.io.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*; //for the testing in main()

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

	public static void main(String[] args) {
		User kruger = new User("42", "Nicolai", "MinPenis");
		String krugerAsXML = marshall(kruger);
		User who = unMarshall(krugerAsXML, User.class);
		System.out.println(who);
	}
}
