package model;

import java.util.regex.Pattern;

public record Customer(String firstName, String lastName, String email) {

    private static final String emailRegex = "^(.+)@(.+).com$";

    private static final Pattern pattern = Pattern.compile(emailRegex);

    public Customer {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
    }

    @Override
    public String toString() {

        return String.format("First Name: %s Last Name: %s Email: %s", this.firstName,this.lastName,this.email );
    }
}
