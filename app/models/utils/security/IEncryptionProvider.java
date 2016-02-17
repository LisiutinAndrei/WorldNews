package models.utils.security;

public interface IEncryptionProvider {

    String encryptPassword(String password);

    boolean checkPassword(String password, String encryptedPassword);
}
