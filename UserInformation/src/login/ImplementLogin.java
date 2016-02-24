package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import message.DBUtils;
import message.User;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ImplementLogin implements UserLogin {
    @Override
    public int login(User user) {
        Connection connection ;
        PreparedStatement preparedStatement ;
        ResultSet resultSet ;
        String sqlString = "select username,password,score from user_score where username=?";

        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1,user.getuName());
            resultSet = preparedStatement.executeQuery();

            //判断用户名是否存在
            if(resultSet.next()){
                //判断密码是否正确
                if(resultSet.getString("password").equals( user.getuPassword()))
                {
                    return resultSet.getInt("score");
                }else{
                    return  -2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
