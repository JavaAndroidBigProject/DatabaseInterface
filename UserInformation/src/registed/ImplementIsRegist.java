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
    //已经注册过返回真，否则你返回假
    public boolean isRegisted(User user) {
        boolean registed = false;

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql="select username from user_score where username=?";
        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getuName());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                registed =  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registed;
    }
}
