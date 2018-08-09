package io.renren.vas.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取计算机本地地址的相关信息
 */
public class NetAddressUtil {

    private static InetAddress localAddress = null;
    private static String localIp = null;
    private static String localName = null;

    private static  io.renren.vas.util.NetAddressUtil instance = null;

    private NetAddressUtil() {
        if (localAddress == null) {
            try {
                localAddress = InetAddress.getLocalHost();
                localIp = localAddress.getHostAddress().toString();
                localName = localAddress.getHostName().toLowerCase();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取本地IP
     * @return
     */
    public String getLocalIp() {
        return localIp;
    }

    /**
     * 获取本地计算名
     * @return
     */
    public String getLocalName() {
        return localName;
    }

    public static  io.renren.vas.util.NetAddressUtil getInstance() {
        if (instance == null) {
            instance = new  io.renren.vas.util.NetAddressUtil();
        }
        return instance;
    }
}
