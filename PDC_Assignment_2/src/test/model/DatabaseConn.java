package test.model;

/**
 * Created by Miguel Emmara - 18022146
 */
public class DatabaseConn {

    /**
     * We reference this in lots of places lets store it somewhere once
     */
    public static final String CONNECTION_URL = "jdbc:derby:SiHax;create=true";

    private DatabaseConn() {
    }

}