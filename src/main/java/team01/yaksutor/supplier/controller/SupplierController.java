package team01.yaksutor.supplier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "user/supplier/login/supplierLogin";
    }

    @GetMapping("/supplierMain")
    public String supplierMain() {
        return "user/supplier/supplierMain";
    }
}
