package regist;

import message.DBUtils;
import message.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ImplementRegist implements UserRegist {
    @Override
    public void regist(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into user_score values(?,?,?)";
        try{
            connection = DBUtils.getconnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getuName());
            preparedStatement.setString(2,user.getuPassword());
            preparedStatement.setInt(3,user.getuScore());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new SQLException("数据添加失败！");
        }finally {
            DBUtils.close(null,preparedStatement,connection);
        }
    }
}
