package com.lavalliere.daniel.projects.patterns.structural.facade;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@IsDemoable
public class ClientApp implements Demoable {

    private ClientApp encryptIt() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String input = "Some text to encrypt";

        var encryptionFacade = new EncryptionFacade();
        encryptionFacade.encrypt(input);

        System.out.println();
        return this;
    }

    public void demo() {
        try {
            encryptIt();
        } catch(Exception ex) {}
    }
}
