package dream.api.dmf.cn.dreaming.utils;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TrustAllCerts implements X509TrustManager {

    public static final SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory factory = null;
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init((KeyManager[])null,
             new TrustManager[]{(TrustManager)(new TrustAllCerts())}, new SecureRandom());
            //Intrinsics.checkExpressionValueIsNotNull(context, "context");
            factory = context.getSocketFactory();
        } catch (Exception e) {

        }

        return factory;
    }
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }


    public static final class TrustAllHostnameVerifier implements HostnameVerifier {
        public boolean verify( String hostname, SSLSession session) {
            return true;
        }
    }

}
