# 反射



```text
/**
 * just for quick practice
 */
public class Test {

    public static void main(String[] args) {
        ReflectClass reflectClass = new ReflectClass();
        //常规使用
        Class cl = reflectClass.getClass();
        //result is : Test$ReflectClass
        System.out.println(cl.getName());

        //throw exception if use "Test.ReflectClass", that is for package path
        String className = "Test$ReflectClass";
        try {
            Class cl1 = Class.forName(className);
            System.out.println(cl1.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //for history reason, Double[].class.getName() equals [Ljava.lang.Double; int[] .. return [I
        Class cl2 = ReflectClass.class;
        cl2 = int.class;
        cl2 = Double[].class;
        System.out.println(cl2.getName());

        //create instance
        Class cl;
        try {
            cl.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //access
        try {
            Field f = reflectClass.getClass().getDeclaredField("field");
            //without control of safety manager
            f.setAccessible(true);
            Object obj = f.get(reflectClass);
            //for double is a primary type
            f.getDouble(reflectClass);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    static class ReflectClass {

        public static void reflectClass(Class cl) {
            Class supreCl = cl.getSuperclass();
            //the modifiers : public , protected,
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(" " + modifiers);
            }
            printConstructors(cl);
            printMethods(cl);
            printFields(cl);
        }

        private static void printConstructors(Class cl) {
            Constructor[] constructors = cl.getDeclaredConstructors();
            for (Constructor c : constructors) {
                String name = c.getName();
                System.out.print("    ");
                //
                Class[] paramTypes = c.getParameterTypes();

            }
        }

        private static void printMethods(Class cl) {
            Method[] methods = cl.getMethods();
            //
            methods[0].getReturnType();
        }

        private static void printFields(Class cl) {
            Field[] fields = cl.getDeclaredFields();
            //
            fields[0].getType();
        }

        private ArrayList<Object> reflected = new ArrayList<>();

        //universal toString by reflect
        public String toString(Object obj) {
            if (obj == null) return "null";
            if (reflected.contains(obj)) return "...";
            reflected.add(obj);

            Class cl = obj.getClass();
            if (cl == String.class) return (String) obj;
            if (cl.isArray()) {
                String r = cl.getComponentType() + "[]{";
                for (int i = 0; i < Array.getLength(obj); i++) {
                    if (i > 0) r += ",";
                    Object o = Array.get(obj, i);
                    if (cl.getComponentType().isPrimitive()) r += o;
                    else r += toString(o);
                }
                return r + "}";
            }

            String str = cl.getName();
            do {
                str += "[";
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                for (Field f : fields) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        if (!str.endsWith("[")) {
                            str += ",";
                        }
                        str += f.getName() + "= ";

                        try {
                            Class c = f.getType();
                            Object o = f.get(this);
                            if (c.isPrimitive()) {
                                str += o;
                            } else {
                                str += toString(o);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                str += "]";
                cl = cl.getSuperclass();
            } while (cl != null);
            return str;
        }
    }

}
```

这句话其实不是很理解，也许以后做到java的时候会理解吧。 ![201848-163013.jpg](https://upload-images.jianshu.io/upload_images/1936727-ecd50ed63b24ea68.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

