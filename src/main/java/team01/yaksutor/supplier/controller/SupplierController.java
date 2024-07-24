package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.security.handler.FormAuthenticationSuccessHandler;
import team01.yaksutor.supplier.mapper.SuMedicineMapper;
import team01.yaksutor.supplier.service.SuMedicineService;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {
    private final SuMedicineService suMedicineService;
    private final SuMedicineMapper suMedicineMapper;

    @GetMapping("/supplierMain")
    public String supplierMain(HttpServletRequest request, Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suMedicineMapper.getSuppCode(sid);
        List<SellMedicine> sellMediList = suMedicineService.getSellMediList(suppCode);


        model.addAttribute("sellMediList", sellMediList);
        return "user/supplier/supplierMain";
    }
}
