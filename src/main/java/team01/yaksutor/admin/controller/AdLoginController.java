package team01.yaksutor.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.service.AdLoginHistoryService;
import team01.yaksutor.dto.LoginHistory;

import java.util.List;

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
    public String loginHistory(Model model) {

        List<LoginHistory> loginHistoryList = adLoginHistoryService.getAllLoginHistories();
        model.addAttribute("loginHistoryList", loginHistoryList);
        model.addAttribute("title", "로그인이력조회");
        model.addAttribute("content", "로그인이력조회");
        return "admin/loginhistory/loginHistory";
    }

}
