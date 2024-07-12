package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import team01.yaksutor.dto.LoginHistory;

@Mapper
public interface AdLoginHistoryMapper {

    @Insert("INSERT INTO log_data (m_id, browser, login_date) VALUES (#{memberId}, #{browser}, #{loginDate})")
    void insertLoginHistory(LoginHistory loginHistory);

    @Update("UPDATE log_data SET logout_date = #{logoutDate} WHERE m_id = #{memberId} AND logout_date IS NULL ORDER BY log_num DESC LIMIT 1")
    void updateLogoutDate(LoginHistory loginHistory);

    @Select("SELECT * FROM log_data WHERE m_id = #{memberId} ORDER BY log_num DESC LIMIT 1")
    LoginHistory findLatestLoginHistory(String mId);
}
