package com.four19ja.dto;

// A (temporary) class just to represent the user details to send to the front end
public class PublicUser {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Empty constructor.
     */
    public PublicUser() {
    }

    /**
     * Constructor.
     *
     * @param username the username of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     */
    public PublicUser(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
