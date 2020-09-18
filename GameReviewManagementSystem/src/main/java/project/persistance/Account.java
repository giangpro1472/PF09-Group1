package project.persistance;

public class Account {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String address;
    private String phone;
    private String account_status;
    private String create_date;
    private String user_title;
    private String last_date;
    private int role_id;

    public Account() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.gender = "";
        this.address = "";
        this.phone = "";
        this.account_status = "";
        this.create_date = "";
        this.user_title = "";
        this.role_id = 0;
        this.last_date = "";
    }

    public void register() {
        this.username = Input.username(username);
        this.password = Input.password(password);
        this.email = Input.email(email);
        this.gender = Input.gender(gender);
        this.address = Input.address(address);
        this.phone = Input.phone(phone);
        this.role_id = Input.role_id();
    }

    public void display() {
        System.out.printf("|%-20s|%-30s|%-15s|%-15s|%-12s|%-8s|%-7s|%-11s|%-11s|\n", this.username, this.email, this.gender,
                this.address, this.phone, this.account_status, this.user_title, this.create_date, this.last_date);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount_status() {
        return this.account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getRole_id() {
        return this.role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUser_title() {
        return this.user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getLast_date() {
        return this.last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

}