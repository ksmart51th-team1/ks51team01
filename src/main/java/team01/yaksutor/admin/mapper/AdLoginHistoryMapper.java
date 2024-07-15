package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import team01.yaksutor.dto.LoginHistory;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdLoginHistoryMapper {


    void insertLoginHistory(LoginHistory loginHistory);


    void updateLogoutDate(LoginHistory loginHistory);

    @Select("SELECT * FROM log_data WHERE m_id = #{memberId} ORDER BY log_num DESC LIMIT 1")
    LoginHistory findLatestLoginHistory(String mId);


    List<Map<String, Object>> getLoginHistory(int startRow, int rowPerPage);

    int getLoginHistoryCnt();
}
