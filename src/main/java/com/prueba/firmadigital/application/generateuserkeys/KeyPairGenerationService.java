package com.prueba.firmadigital.application.generateuserkeys;

import java.security.KeyPair;

public interface KeyPairGenerationService {

    public KeyPair generate(String userName);
}
