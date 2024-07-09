package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Supplier;

@Controller
@RequestMapping("/admin/supplier")
@RequiredArgsConstructor
@Slf4j
public class AdSupplierController {

    @PostMapping("SupplierInsert")
    public String supplierInsert(Member member, Supplier supplier){
        log.info("member {}",member);
        log.info("supplier {}",supplier);

        return "redirect:/admin/supplier/supplierInsert";
    }



    @GetMapping("/supplierInsert")
    public String supplierInsert(Model model) {

        model.addAttribute("title", "납품업체추가");
        model.addAttribute("content","납품업체추가");

        return "admin/supplier/supplierInsert";
    }
}
