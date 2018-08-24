package struts2.common.users;

public enum UserRole {
    ADMIN("Admin"),
    STAFF("Staff"),
    UNKNOWN("Unknown");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
