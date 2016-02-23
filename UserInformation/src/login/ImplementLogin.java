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
        String sqluName = "select username from user_score";
        String sqluPassword = "select password,score from user_score where username=?";

        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sqluName);
            resultSet = preparedStatement.executeQuery();
            boolean isName = resultSet.next();
            //判断username是否存在
            if(isName){
                try{
                    preparedStatement = connection.prepareStatement(sqluPassword);
                    preparedStatement.setString(1,user.getuName());
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    //判断密码是否正确
                    if(resultSet.getString("password").equals( user.getuPassword()))
                    {
                        return resultSet.getInt("score");
                    }else{
                        return  -2;
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
