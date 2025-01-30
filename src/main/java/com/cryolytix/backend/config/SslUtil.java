package com.cryolytix.backend.config;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

public class SslUtil {

    public static SSLSocketFactory getSocketFactory(final String caCrtFile, final String crtFile, final String keyFile) throws Exception {
        // Load CA certificate
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream caInputStream = new FileInputStream(caCrtFile);
        X509Certificate caCert = (X509Certificate) cf.generateCertificate(caInputStream);
        caInputStream.close();

        KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        caKeyStore.load(null, null);
        caKeyStore.setCertificateEntry("ca-cert", caCert);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(caKeyStore);

        // Load client certificate
        FileInputStream crtInputStream = new FileInputStream(crtFile);
        X509Certificate clientCert = (X509Certificate) cf.generateCertificate(crtInputStream);
        crtInputStream.close();

        // Load client private key
        FileInputStream keyInputStream = new FileInputStream(keyFile);
        byte[] keyBytes = keyInputStream.readAllBytes();
        keyInputStream.close();

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        clientKeyStore.load(null, null);
        clientKeyStore.setKeyEntry("client-key", privateKey, null, new java.security.cert.Certificate[]{clientCert});

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientKeyStore, null);

        // Set up SSL context
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext.getSocketFactory();
    }
}
