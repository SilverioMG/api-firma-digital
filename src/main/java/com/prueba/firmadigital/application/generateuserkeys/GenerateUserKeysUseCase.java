package com.prueba.firmadigital.application.generateuserkeys;

import com.prueba.firmadigital.application.UserKeyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

@Component
public class GenerateUserKeysUseCase {

    private final KeyPairGenerationService keyPairGenerationService;
    private final UserKeyRepository userKeyRepository;

    public GenerateUserKeysUseCase(
            KeyPairGenerationService keyPairGenerationService,
            UserKeyRepository userKeyRepository) {
        this.keyPairGenerationService = keyPairGenerationService;
        this.userKeyRepository = userKeyRepository;
    }

    public void generateKeys(GenerateUserKeysCommand command) {
        validateCommand(command);
        String userName = command.userName();
        KeyPair keyPair = keyPairGenerationService.generate();
        userKeyRepository.save(userName, keyPair);
    }


    private void validateCommand(GenerateUserKeysCommand command) {
        if(command == null) { throw new GenerateUserKeysValidationException("Valor incorrecto para 'command': " + command); }
        if(StringUtils.isBlank(command.userName())) { throw new GenerateUserKeysValidationException("Valor incorrecto para 'userName': " + command.userName()); }
    }
}
