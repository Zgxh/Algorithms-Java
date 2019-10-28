package advancedJavaSE;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @author Yu Yang
 * @create 2019-10-28 20:52
 */
public class DynamicCompile {

    public static void main(String[] args) throws IOException {

        // 编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null,
                "C:\\Users\\Administrator\\Desktop\\Test.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

//        // 运行
//        Runtime run = Runtime.getRuntime();
//        Process process = run.exec(
//                "java -cp C:\\Users\\Administrator\\Desktop Test");
//
//        // 获取代码运行的输出
//        InputStream in = process.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String info = "";
//        while((info = reader.readLine()) != null) {
//            System.out.println(info);
//        }

        // 通过反射的方式来进行动态运行代码
        try {
            URL[] urls = new URL[]{new URL("file:/" + "C:\\Users\\Administrator\\Desktop\\")};
            URLClassLoader loader = new URLClassLoader(urls);
            // 这里需要处理异常
            Class c = loader.loadClass("Test");
            Method m = c.getMethod("main", String[].class);
            // 由于main方法是一个静态方法，不需要指定它的实例对象
            // 第二个参数由于invoke方法的参数形式Object...，需要强转为Object，
            // 防止String[]中的各个String被拆分为好几个对象传入
            m.invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
