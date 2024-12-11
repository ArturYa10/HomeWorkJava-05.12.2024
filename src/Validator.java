import java.util.regex.Pattern;


public class Validator {

    // 1. Регулярное выражение для проверки номера телефона
    public static final String PHONE_REGEX = "^(\\d{7}|\\d{3}[- ]\\d{4})$";

    public static boolean validatePhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_REGEX, phoneNumber);
    }

    // 2. Метод валидации адреса электронной почты
    public static boolean validateEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    // 3. Метод проверки логина и пароля
    public static boolean checkLoginAndPassword(String login, String password, String confirmPassword) {
        try {
            // Проверка логина
            if (!Pattern.matches("^[a-zA-Z]{3,20}$", login)) {
                throw new WrongLoginException("Login must contain only Latin letters and be between 3 and 20 characters long.");
            }

            // Проверка пароля
            if (!Pattern.matches("^[a-zA-Z0-9]{5,20}$", password)) {
                throw new WrongPasswordException("Password must contain only Latin letters and digits, and be between 5 and 20 characters long.");
            }

            // Сравнение пароля и подтверждения
            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Password and confirmPassword do not match.");
            }

            return true; // Если все проверки пройдены

        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Пример проверки номера телефона
        System.out.println(validatePhoneNumber("1234567")); // true
        System.out.println(validatePhoneNumber("123-4567")); // true
        System.out.println(validatePhoneNumber("123 4567")); // true
        System.out.println(validatePhoneNumber("12345678")); // false

        // Пример проверки email
        System.out.println(validateEmail("test@example.com")); // true
        System.out.println(validateEmail("invalid-email")); // false

        // Пример проверки логина и пароля
        System.out.println(checkLoginAndPassword("john", "1234a", "1234a"));
        System.out.println(checkLoginAndPassword("john", "12345", "123456"));
        System.out.println(checkLoginAndPassword("john", "12345@", "12345@"));
        System.out.println(checkLoginAndPassword("john1", "12345", "12345"));
    }
}
