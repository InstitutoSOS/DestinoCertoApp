package institutosos.org.br.destinocerto.model;

@SuppressWarnings("unused")
public class User {
    private int id;
    private String username;
    private Site site;

    public Site getSite() {
        return site;
    }

    public int getId() {
        return id;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }
}
