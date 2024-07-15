package team01.yaksutor.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.service.AdLoginHistoryService;
import team01.yaksutor.dto.LoginHistory;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdLoginController {

    private final AdLoginHistoryService adLoginHistoryService;

    @PostMapping("/loginHistory/deleteHistory")
    public String deleteHistory(@RequestParam(value = "logNum") int logNum ) {


        return "redirect:/admin/loginHistory";
    }

    @GetMapping("/loginHistory")
    public String loginHistory(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage,
                               Model model) {

        Map<String, Object> resultMap = adLoginHistoryService.getLoginHistory(currentPage);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> loginHistoryList = (List<Map<String, Object>>) resultMap.get("loginHistory");
        int lastPage = (int) resultMap.get("lastPage");
        int startPageNum = (int) resultMap.get("startPageNum");
        int endPageNum = (int) resultMap.get("endPageNum");

        model.addAttribute("loginHistoryList", loginHistoryList);
        model.addAttribute("title", "로그인이력조회");
        model.addAttribute("content", "로그인이력조회");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        return "admin/loginhistory/loginHistory";
    }

}
