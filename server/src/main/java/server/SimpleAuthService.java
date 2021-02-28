package server;

import db.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException{
        return DB.getNickname(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        try {
            if (DB.checkUserExisting(login) == null) {
                DB.addUser(login, password, nickname);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.getErrorCode();
            return false;
        }
    }
}
