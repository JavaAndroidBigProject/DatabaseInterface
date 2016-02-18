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
    int score;
    @Override
    public int login(User user) {
        Connection connection ;
        PreparedStatement preparedStatement ;
        ResultSet resultSet ;
        String sqluName = "select username from user_score";
        String sqluPassword = "select password from user_score where username=?";
        String sqluScore = "select score from user_score where username=? and password=? ";

        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sqluName);
            resultSet = preparedStatement.executeQuery();
            boolean isName = resultSet.next();
            //判断username是否存在
            if(isName){
                try{
                    user = new User();
                    preparedStatement = connection.prepareStatement(sqluPassword);
                    preparedStatement.setString(1,user.getuName());
                    resultSet = preparedStatement.executeQuery();
                    //判断密码是否正确
                    if(resultSet.next())
                    {
                        try{
                            user = new User();
                            preparedStatement = connection.prepareStatement(sqluScore);
                            preparedStatement.setString(1,user.getuName());
                            preparedStatement.setString(2,user.getuPassword());
                            resultSet = preparedStatement.executeQuery();
                            //两个都正确，返回分数
                            if(resultSet.next()){
                                score = resultSet.getInt("score");
                            }
                        }catch (SQLException e){
                            e.printStackTrace();
                        }


                    }else{
                        score = -2;
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else{
                score = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }
}
