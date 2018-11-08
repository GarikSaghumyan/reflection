
import java.lang.reflect.*;
public class Main {
	public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, InstantiationException,NoSuchFieldException {
		Class clazz = Class.forName("MyClass");
		MyClass myClass = (MyClass) clazz.newInstance();
		Field[] fields = myClass.getClass().getDeclaredFields();
		for (Field f : fields) {
			if(f.getName().equals("firstName")){
				f.setAccessible(true);
				f.set(myClass, (String) "Valodik");
				f.setAccessible(false);
			}
			if(f.getName().equals("lastName")){
				f.setAccessible(true);
				f.set(myClass, (String)"Valodikyan");
				f.setAccessible(false);
		    }
		    if(f.getName().equals("age")){
				f.setAccessible(true);
				f.setInt(myClass, 20 );
				f.setAccessible(false);
		    }    
		}
		System.out.println(toJson(myClass));
	}
	 public static String toJson(Object o) throws IllegalAccessException{
        Field[] fildes = o.getClass().getDeclaredFields();
        String jsonFormat =
                "{\n";

        for (Field currentField : fildes) {
            String fieldName = currentField.getName();
            currentField.setAccessible(true);
            Transient annotation = currentField.getDeclaredAnnotation(Transient.class);
            if (annotation != null)
                continue;
            jsonFormat += "   \"" + currentField.getName() + "\"" + ": " + "\"" + currentField.get(o)+"\"\n";
             currentField.setAccessible(false);
        }
        jsonFormat += "}";
        return jsonFormat;
    }
}