package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team01.yaksutor.admin.mapper.AdLoginHistoryMapper;
import team01.yaksutor.dto.LoginHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdLoginHistoryService {

    private final AdLoginHistoryMapper adLoginHistoryMapper;

    public Map<String, Object> getLoginHistory(int currentPage){
        // 보여줄 행의 갯수
        int rowPerPage = 10;
        // 테이블의 조회할 시작행
        int startRow = (currentPage - 1) * rowPerPage;

        // 시작할  페이지 설정 초기화
        int startPageNum = 1;

        // 마지막 페이지 설정 초기화
        int endPageNum = 10;

        List<Map<String, Object>> loginHistory = adLoginHistoryMapper.getLoginHistory(startRow, rowPerPage);

        double cnt = adLoginHistoryMapper.getLoginHistoryCnt();

        // 마지막 페이지
        int lastPage = (int) Math.ceil(cnt/rowPerPage);

        endPageNum = lastPage < 10 ? lastPage : endPageNum;

        // 동적 페이지 설정
        if(currentPage > 6 && lastPage > 9){
            startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            // 마지막 페이지 번호가 마지막 페이지 수보다 클경우에 페이지 번호를 고정
            if(endPageNum >= lastPage){
                startPageNum = lastPage - 9;
                endPageNum = lastPage;
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("loginHistory", loginHistory);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("endPageNum", endPageNum);

        return resultMap;
    }
}
