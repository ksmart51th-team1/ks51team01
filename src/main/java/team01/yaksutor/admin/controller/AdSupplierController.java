package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.admin.service.AdSupplierService;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Supplier;

import java.util.List;

@Controller
@RequestMapping("/admin/supplier")
@RequiredArgsConstructor
@Slf4j
public class AdSupplierController {
    private final AdSupplierService adSupplierService;

    @PostMapping("/supplierModify")
    public String supplierModify(Supplier supplier){
        adSupplierService.supplierModify(supplier);
        return "redirect:/admin/supplier/supplierSearchList";
    }

    @PostMapping("/supplierDelete")
    public String supplierDelete(Supplier supplier){
        adSupplierService.supplierDelete(supplier);
        return "redirect:/admin/supplier/supplierSearchList";
    }

    @GetMapping("/supplierSearchList")
    public String supplierSearchList(Model model) {
        List<Supplier> supplierList = adSupplierService.getSupplierInfo();

        model.addAttribute("title","납품업체 리스트");
        model.addAttribute("content","납품업체 리스트");
        model.addAttribute("supplierList",supplierList);


        return "admin/supplier/supplierSearchList";
    }

    @PostMapping("/supplierInsert")
    public String supplierInsert(Member member, Supplier supplier){

        adSupplierService.supplierInsert(member, supplier);

        return "/admin/supplier/supplierInsert";
    }



    @GetMapping("/supplierInsert")
    public String supplierInsert(Model model) {

        model.addAttribute("title", "납품업체추가");
        model.addAttribute("content","납품업체추가");

        return "admin/supplier/supplierInsert";
    }
}
