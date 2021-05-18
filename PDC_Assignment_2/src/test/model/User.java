package test.model;

import com.online.shopping_gui.enumerations.State;

/**
 * This is an abstract class that expresses characteristics (such as login
 * credentials) of a system user. Characteristics which are used for
 * authentication purposes during login.
 *
 * <p>
 * Attributes:</p>
 * <ul>
 * <li>loginID : Unique identifier for individual users</li>
 * <li>password : String representation of the account's password</li>
 * <li>state : Enum specifying the state of the account</li>
 * </ul>
 * <p>
 * Behaviours:
 * <ul>
 * <li>1-Parameter Constructor</li>
 * <li>Getters and Setters</li>
 * <li>Reset Password</li>
 * <li>Methods for hashing purposes(equals and hashcode)</li>
 * </ul>
 *
 * <p>
 * The setPassword method is abstract which must be overridden by User's
 * subclasses, as different types of User have different requirement (e.g. an
 * administrator will be required to have a longer, more complex password
 * compared to a regular user).</p>
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.03
 * @since 29/03/2021
 *
 */
public abstract class User {

    protected String password;
    private String loginID;
    private State state;

    /**
     * 1-parameter constructor for User class. Subclasses of User must call
     * super() and pass in the loginID to initialise their account.
     *
     * <p>
     * Newly created accounts have their state set to Active.</p>
     *
     * @param loginID : user's login id.
     *
     */
    public User(String loginID) {
        this.setLoginID(loginID);
        this.setState(State.ACTIVE);
    }

    //-------------------------------------------------------
    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID.trim(); // Trim any whitespace...
    }

    //-------------------------------------------------------
    public String getPassword() {
        return password;
    }

    public abstract boolean setPassword(String password);

    //-------------------------------------------------------
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    //-------------------------------------------------------

    /**
     * Method to resets password to a null value.
     *
     */
    public void resetPassword() {
        this.password = "password";
    }

    // Overridden methods for the purpose of hashing.
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @Override
    public boolean equals(Object o) {
        // Check for duplicate login ids (case insensitive).
        return (o instanceof User
                && ((User) o).getLoginID().equalsIgnoreCase(this.getLoginID()));
    }

    @Override
    public int hashCode() {
        // Returns a generated int based on the login id.
        return this.getLoginID().hashCode();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
