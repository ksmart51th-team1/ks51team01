package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.security.handler.FormAuthenticationSuccessHandler;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {



    @GetMapping("/supplierMain")
    public String supplierMain(HttpServletRequest request) {
        /* 아래 코드를 주석 해제하면 현재 저장된 세션 ID를 불러올 수 있음*/
        //String S_ID = (String) request.getSession().getAttribute("S_ID");
        //System.out.println("납품업체 메인페이지에 저장된 세션 ID : " + S_ID);

        return "user/supplier/supplierMain";
    }
}
