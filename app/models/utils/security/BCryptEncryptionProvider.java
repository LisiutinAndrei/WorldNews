package models.utils.security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptEncryptionProvider implements IEncryptionProvider {
    @Override
    public String encryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is empty");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String encryptedPassword) {
        if (password == null || password.isEmpty() || encryptedPassword == null || encryptedPassword.isEmpty()) {
            return false;
        }

        return BCrypt.checkpw(password, encryptedPassword);
    }
}
