package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team01.yaksutor.admin.mapper.AdLoginHistoryMapper;
import team01.yaksutor.dto.LoginHistory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdLoginHistoryService {

    private final AdLoginHistoryMapper adLoginHistoryMapper;

    public List<LoginHistory> getAllLoginHistories() {
        //return adLoginHistoryMapper.get
    }
}
