package de.tchuensu.home.springbootserver.services;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.stereotype.Component;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Component
public class PasswordEncryptorManager {

    private ConfigurablePasswordEncryptor passwordEncryptor;

    public PasswordEncryptorManager() {
        this.passwordEncryptor = new ConfigurablePasswordEncryptor();
        this.passwordEncryptor.setAlgorithm("SHA-256");
        this.passwordEncryptor.setPlainDigest(false);
    }

    /**
     *
     * Generate a hashed password
     *
     * @param password plaintext password to be hashed
     * @return String corresponding to the hash of the plaintext password
     */
    public String generatePasswordHash(String password) {
        return passwordEncryptor.encryptPassword(password);
    }

    /**
     * Check that the password matches the hashed password
     *
     * @param password plaintext password to be validated
     * @param passwordHash hashed password stored in the database
     * @return true if the passwords matches and false otherwise
     */
    public  boolean checkPassword(String password, String passwordHash) {
        return passwordEncryptor.checkPassword(password, passwordHash);
    }
}
