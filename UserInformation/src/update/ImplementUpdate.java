package update;

import message.DBUtils;
import message.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ImplementUpdate implements UserUpdate {
    @Override
    public void update(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        String sql = "update user_score set score=? where username=?";
        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getuScore());
            preparedStatement.setString(2,user.getuName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new SQLException("更新失败!");
        }finally {
            DBUtils.close(null,preparedStatement,connection);
        }

    }
}
