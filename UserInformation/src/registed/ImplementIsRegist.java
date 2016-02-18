package registed;

import message.DBUtils;
import message.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ImplementIsRegist implements UserIsRegisted{

    @Override
    public boolean isRegisted(User user) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql="select ? from user_score";
        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getuName());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
