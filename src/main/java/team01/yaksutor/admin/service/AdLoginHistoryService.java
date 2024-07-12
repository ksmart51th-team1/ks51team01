package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team01.yaksutor.admin.mapper.AdLoginHistoryMapper;
import team01.yaksutor.dto.LoginHistory;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdLoginHistoryService {

    private final AdLoginHistoryMapper adLoginHistoryMapper;

    public List<LoginHistory> getAllLoginHistories() {
        List<LoginHistory> LoginHistoryList = adLoginHistoryMapper.getAllLoginHistories();
        return LoginHistoryList;
    }
}
