import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private class Entry {
        private String login;
        private String pass;
        private String nick;
        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }
    private List<Entry> entries;
    Connection con;
    PreparedStatement statement;
    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }
    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("admin", "pass1", "nick1"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }
    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    @Override
    public String getNickByLoginPassDB(String login, String pass) throws SQLException, ClassNotFoundException {
        connect();
        String returnNick = null;
        statement = con.prepareStatement("SELECT nick FROM users WHERE login = ? AND pass = ?");
        statement.setString(1,login);
        statement.setString(2,pass);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            returnNick = rs.getString("Nick");
        }
        con.close();
        return returnNick;
    }
    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:D:\\sql\\users.db");


    }
}