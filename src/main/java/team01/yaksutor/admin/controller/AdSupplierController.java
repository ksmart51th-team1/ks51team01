package team01.yaksutor.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/supplier")
public class AdSupplierController {


    @GetMapping("/supplierInsert")
    public String supplierInsert(Model model) {

        model.addAttribute("title", "납품업체추가");
        model.addAttribute("content","납품업체추가");

        return "admin/supplier/supplierInsert";
    }
}
