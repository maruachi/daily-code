package _20220926;

import java.util.Optional;

public class OptionalEx1 {
    public static void main(String[] args) {
        Optional<String> string = Optional.of("request");
        System.out.println(Optional.of("request"));
        System.out.println(Optional.ofNullable(null));
        System.out.println(Optional.empty());
        Optional<String> string2 = Optional.empty();
        System.out.println(string.orElse("request2"));
        System.out.println(string2.orElse("request2"));
        System.out.println(string2.orElseThrow(()->new RuntimeException("error")));

        User user = new User(null);
        System.out.println(user.getName().getFirstName().getLastName());

        if(user != null){
            Name name = user.getName();

            if (name != null) {
                FirstName firstName = name.getFirstName();

                if (firstName != null) {
                    LastName lastName = firstName.getLastName();

                    if (lastName != null) {
                        System.out.println(lastName.getValue());
                    }
                }
            }
        }

        String value = Optional.ofNullable(user).map(user1 -> user1.getName())
                .map(name -> name.getFirstName())
                .map(firstName -> firstName.getLastName())
                .map(lastName -> lastName.getValue())
                .orElse("Nothing");

        System.out.println(value);
    }

    public static class User{
        private final Name name;

        public Name getName() {
            return name;
        }

        public User(Name name) {
            this.name = name;
        }
    }

    public static class Name{
        private final FirstName firstName;

        public FirstName getFirstName() {
            return firstName;
        }

        public Name(FirstName firstName) {
            this.firstName = firstName;
        }
    }

    public static class FirstName{
        public LastName getLastName() {
            return lastName;
        }

        public FirstName(LastName lastName) {
            this.lastName = lastName;
        }

        private final LastName lastName;
    }

    public static class LastName{
        public String getValue() {
            return value;
        }

        public LastName(String value) {
            this.value = value;
        }

        private final String value;
    }
}
