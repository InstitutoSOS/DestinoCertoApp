package institutosos.org.br.destinocerto.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("success")
    private boolean success;

    @SerializedName("userExists")
    private boolean userExists;

    @SerializedName("model")
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public boolean userExists() {
        return userExists;
    }

    public User getUser() {
        return user;
    }
}
