package test;

import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by twisty on 14-8-10.
 */
public class TestSocket {
    private static final String IP = "192.168.1.110";
    private static final int PORT = 5003;
    private CommonLog log = LogFactory.createLog();
    public static void main(String[] args) {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(5000);
        connector.connect(new InetSocketAddress(IP,PORT));
        connector.getFilterChain().addLast( "logger", new LoggingFilter() );
        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName("UTF-8"))));

    }


}
