package institutosos.org.br.destinocerto.model;

public class LoginResult {
    private boolean success;
    private boolean userExists;
    private User model;

    public boolean isSuccess() {
        return success;
    }

    public boolean userExists() {
        return userExists;
    }

    public User getModel() {
        return model;
    }
}
