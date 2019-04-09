import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> mclass=methodClass.class;
        Object obj = mclass.newInstance();
        Method method=mclass.getMethod("add", int.class, int.class);
        Object result=method.invoke(obj,1,4);
        System.out.println(result);
    }
}
    class methodClass{
        public final int fuck = 3;
        public int add(int a,int b) {
            return a+b;
        }
        public int sub(int a,int b) {
            return a+b;
        }
    }