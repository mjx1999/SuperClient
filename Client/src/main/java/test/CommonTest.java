package test;

import com.twisty.superclient.net.ReqClient;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by twisty on 14-8-10.
 */
public class CommonTest {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("192.168.1.111",5006);
        System.out.println(socket);

        ReqClient client = ReqClient.newInstance();
        boolean b = client.connectServer("192.168.1.111",5006,null);
        System.out.println(b);
    }
}
