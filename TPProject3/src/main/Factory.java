package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class Factory {
	private Properties properties;
	private static Factory instance = new Factory();
	private Map<String,Class> classes = new TreeMap<String, Class>();
	private Map<String, Object> objects = new TreeMap<String, Object>();
	
	private Factory(){
		initProperties();
	}

	public static Factory getFactory() {
		if (instance == null) {
			instance = new Factory();
		}

		return instance;
	}

	/**************************** ALWAYS CREATES NEW INSTANCE **************************/
	public Object newInstance(String abstractName, Class<?>[] paramTypes,
			Object[] params) {
		Object obj = null;

		try {
			Class<?> cls = classes.get(abstractName);

			if (cls == null) {
				throw new RuntimeException("No class registered under " +
						abstractName);
			}

			Constructor<?> ctor = cls.getConstructor(paramTypes);
			obj = ctor.newInstance(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return obj;
	}

	public Object newInstance(String abstractName) {
		return newInstance(abstractName, new Class[] {  }, new Object[] {  });
	}

	/************************ CREATES OBJECT AND STORES IT ********************/	
	public Object getInstance(String abstractName, Class[] paramTypes,
			Object[] params) {
		Object obj = objects.get(abstractName);

		if (obj == null) {
			obj = newInstance(abstractName, paramTypes, params);
			objects.put(abstractName, obj);
		}

		return obj;
	}

	public Object getInstance(String abstractName) {
		return getInstance(abstractName, new Class[] {  }, new Object[] {  });
	}


	/****************INITIALIZATION - GET CLASSES INTO MAP ******************/
	public void initProperties(){
		properties = new Properties();

		try {
			InputStream is = this.getClass().getResourceAsStream("/FactoryInventory.xml");
			properties.loadFromXML(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Iterator it = properties.entrySet().iterator();

		while(it.hasNext()){

			try {
				Map.Entry entry = (Map.Entry) it.next();
				String keyName = (String) entry.getKey();
				String className = (String) entry.getValue();
				Class cls = Class.forName(className);
				classes.put(keyName, cls);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
