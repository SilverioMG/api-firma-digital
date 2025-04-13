package com.prueba.firmadigital.infraestructure.repository;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Optional;

import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.ContentSigner;

@Component
public class KeyStoreDao {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static final String KEY_STORE_TYPE = "JKS";
    public static final String KEY_STORE_FILE_EXTENSION = ".jks";

    public void saveToKeyStore(KeyPair keyPair, String userName, String alias, String password, String keyStoreDirPath) throws Exception {
        checkExistsKeyStoreFilePath(keyStoreDirPath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);
        ks.load(null, null);
        X509Certificate certificate = createSelfSignedCertificate(keyPair);
        ks.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[]{ certificate });
        String keyStoreUserFilePath = generateKeyStoreUserFilePath(userName, keyStoreDirPath);
        try (FileOutputStream fos = new FileOutputStream(keyStoreUserFilePath)) {
            ks.store(fos, password.toCharArray());
        }
    }

    private void checkExistsKeyStoreFilePath(String keyStoreDirPath) throws IOException {
        Path dirPath = Paths.get(keyStoreDirPath);
        if(Files.exists(dirPath) && Files.isDirectory(dirPath)) {

            return;
        }

        Files.createDirectories(dirPath);
    }

    public Optional<KeyStore> loadKeyStore(String userName, String password, String keyStoreDirPath) throws Exception {
        KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);
        String keyStoreUserFilePath = generateKeyStoreUserFilePath(userName, keyStoreDirPath);
        if(Files.notExists(Paths.get(keyStoreUserFilePath))) {

            return Optional.empty();
        }

        try (FileInputStream fis = new FileInputStream(keyStoreUserFilePath)) {
            ks.load(fis, password.toCharArray());
        }

        return Optional.of(ks);
    }

    private X509Certificate createSelfSignedCertificate(KeyPair keyPair) throws Exception {
        long now = System.currentTimeMillis();
        Date notBefore = new Date(now);
        Date notAfter = new Date(now + 365L * 24 * 60 * 60 * 1000); // 1 año

        X500Principal subject = new X500Principal("CN=MiClaveRSA");
        BigInteger serial = BigInteger.valueOf(now);

        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA")
                .build(keyPair.getPrivate());

        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                subject, serial, notBefore, notAfter, subject, keyPair.getPublic());

        return new JcaX509CertificateConverter()
                .setProvider("BC")
                .getCertificate(certBuilder.build(signer));
    }

    private String generateKeyStoreUserFilePath(String userName, String keyStoreDirPath) {
        String fileName = userName + KEY_STORE_FILE_EXTENSION;

        return Paths.get(keyStoreDirPath, fileName).toString();
    }

}
