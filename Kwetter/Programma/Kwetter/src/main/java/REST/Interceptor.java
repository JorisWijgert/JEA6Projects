package REST;

import JSONObjects.KweetJSON;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Joris on 14-3-2017.
 */
public class Interceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        Object[] parameters = context.getParameters();
        KweetJSON kweet = (KweetJSON) parameters[0];

        FileInputStream fis = new FileInputStream("E:\\woord.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line;

        while (( line = br.readLine()) != null) {
            String[] originalAndReplaceWord = line.split(";");
            kweet.message = kweet.message.replaceAll(originalAndReplaceWord[0], originalAndReplaceWord[1]);
        }
        parameters[0] = kweet;
        context.setParameters(parameters);

        fis.close();
        return context.proceed();
    }
}
