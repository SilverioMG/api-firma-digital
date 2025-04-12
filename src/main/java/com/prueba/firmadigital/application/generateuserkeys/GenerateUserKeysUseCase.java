package com.prueba.firmadigital.application.generateuserkeys;

import com.prueba.firmadigital.application.UserKeyRepository;
import com.prueba.firmadigital.domain.KeyPair;
import com.prueba.firmadigital.domain.User;
import com.prueba.firmadigital.domain.UserKeyPairAggregate;
import org.springframework.stereotype.Component;

@Component
public class GenerateUserKeysUseCase {

    private UserKeyRepository userKeyRepository;
    private final KeyPairGenerationService keyPairGenerationService;

    public GenerateUserKeysUseCase(
            UserKeyRepository userKeyRepository,
            KeyPairGenerationService keyPairGenerationService) {
        this.userKeyRepository = userKeyRepository;
        this.keyPairGenerationService = keyPairGenerationService;
    }

    public void generateKeys(String userName) {
        UserKeyPairAggregate userKeyPair = userKeyRepository.findUserKeyPairByName(userName).orElse(null);
        if(userKeyPair == null) {
            createNewUserKeyPair(userName);

            return;
        }

        updateUserKeyPair(userKeyPair);
    }

    private void createNewUserKeyPair(String userName) {
        User user = new User(userName);
        KeyPair keyPair = keyPairGenerationService.generate();
        UserKeyPairAggregate userKeyPairAggregate = new UserKeyPairAggregate(user, keyPair);

        userKeyRepository.save(userKeyPairAggregate);
    }

    private void updateUserKeyPair(UserKeyPairAggregate userKeyPairAggregate) {
        KeyPair keyPair = keyPairGenerationService.generate();
        userKeyPairAggregate.changeKeyPair(keyPair);

        userKeyRepository.save(userKeyPairAggregate);
    }
}
