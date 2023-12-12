//package com.example.medatlas.config;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.ClassPathResource;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.security.*;
//import java.security.cert.CertificateException;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//
//@Slf4j
//@Getter
//@Setter
//@AllArgsConstructor
//public class SSLConfig {
//
//    private final Path privateKeyPath;
//    private final Path pemFilePath;
//
//    public SSLConfig(String pemFileName, String privateKeyFileName) throws IOException, URISyntaxException {
//        // Assuming pem files are stored in resources/ssl
//        ClassPathResource pemResource = new ClassPathResource("ssl/" + pemFileName);
//        ClassPathResource privateKeyResource = new ClassPathResource("ssl/" + privateKeyFileName);
//        this.pemFilePath = Path.of(pemResource.getURL().toURI());
//        this.privateKeyPath = Path.of(privateKeyResource.getURL().toURI());
//
////        this.pemFilePath = pemResource.getFile().toPath();
////        this.privateKeyPath = privateKeyResource.getFile().toPath();
//    }
//
//    public SSLSocketFactory getSSLSocketFactory() throws IOException {
//        byte[] pemFileBytes = Files.readAllBytes(pemFilePath);
//        byte[] privateKeyBytes = Files.readAllBytes(privateKeyPath);
//
//        // Replace SSLSocketFactoryUtils.createSSLSocketFactory
//        // with your actual implementation or library
//        // depending on how you handle SSL in your project.
//        SSLSocketFactory sslSocketFactory = createSSLSocketFactory(pemFileBytes, privateKeyBytes);
//
//        return sslSocketFactory;
//    }
//
//    // Replace this method with your actual implementation
//    private SSLSocketFactory createSSLSocketFactory(byte[] pemFileBytes, byte[] privateKeyBytes) {
//        try {
//            Security.addProvider(new BouncyCastleProvider());
//
//            // Decode PEM-encoded certificate
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            ByteArrayInputStream pemInputStream = new ByteArrayInputStream(pemFileBytes);
//            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(pemInputStream);
//
//            // Decode PEM-encoded private key
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//            // Create KeyStore and add the certificate and private key
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("cert", cert);
//            keyStore.setKeyEntry("key", privateKey, "".toCharArray(), new java.security.cert.Certificate[]{cert});
//
//            // Initialize KeyManagerFactory
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(keyStore, "".toCharArray());
//
//            // Create SSLContext
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
//
//            return sslContext.getSocketFactory();
//        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
//                 IOException | InvalidKeySpecException | NoSuchProviderException | KeyManagementException e) {
//            log.error("Error creating SSLSocketFactory", e);
//            return null;
//        } catch (UnrecoverableKeyException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}