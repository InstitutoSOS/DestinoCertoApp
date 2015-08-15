package institutosos.org.br.destinocerto.model;

import java.util.Date;

@SuppressWarnings("unused")
public class Location {

    private Date timestamp;
    private Site site;

    public Site getSite() {
        return site;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
