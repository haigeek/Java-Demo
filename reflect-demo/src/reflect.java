import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class reflect {
    private static void PrintMethod() {
        //获取类的名称
        Class mClass = SonClass.class;
        System.out.println("类的名称" + mClass.getName());
        //2.1 获取所有 public 访问权限的方法
        //包括自己声明和从父类继承的
        Method[] mMethods = mClass.getMethods();
        //获取所有本类的的方法（不问访问权限）
//        Method[] mMethods = mClass.getDeclaredMethods();
        //遍历所有方法
        for (Method method : mMethods
        ) {
            //获取并输出方法的访问权限（Modifiers：修饰符）
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            //获取并输出方法的返回值类型
            Class returnType = method.getReturnType();
            System.out.print(returnType.getName() + " "
                    + method.getName() + "( ");
            //获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter :
                    parameters) {
                System.out.print(parameter.getType().getName()
                        + " " + parameter.getName() + ",");
            }
            //获取并输出方法抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0) {
                System.out.println(" )");
            } else {
                for (Class c : exceptionTypes) {
                    System.out.println(" ) throws "
                            + c.getName());
                }
            }

        }
    }
    public static void main(String[] args) {

        PrintMethod();
    }
}
