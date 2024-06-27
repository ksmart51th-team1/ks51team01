package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.admin.dto.Efficacy;
import team01.yaksutor.admin.dto.Ingredient;
import team01.yaksutor.admin.dto.Medicine;
import team01.yaksutor.admin.dto.SellMedicine;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping("/adminMain")
    public String adminMain(Model model) {
        model.addAttribute("title", "메인 대쉬보드");
        model.addAttribute("content", "여기가 메인");
        return "admin/adminMain";
    }

    @GetMapping("/medicine/medicineInsert")
    public String medicineInsert(Model model) {
        model.addAttribute("title", "의약품 등록");
        model.addAttribute("content", "의약품 등록");
        return "admin/medicine/medicineInsert";
    }

    @PostMapping("/medicine/medicineInsert")
    public String medicineInsert(Medicine medicine,
                                 SellMedicine sellMedicine,
                                 Ingredient ingredient,
                                 Efficacy efficacy) {
        log.info("의약품 등록 Medicine: {}", medicine);
        log.info("의약품 등록 SellMedicine: {}", sellMedicine);
        log.info("의약품 등록 Ingredient: {}", ingredient);
        log.info("의약품 등록 Efficacy: {}", efficacy);

        return "redirect:/admin/adminMain";
    }

    @GetMapping("/medicine/medicineSearchList")
    public String medicineSearchList(Model model) {
        model.addAttribute("title", "의약품 리스트");
        model.addAttribute("content", "의약품리스트");

        return "admin/medicine/medicineSearchList";
    }

    @GetMapping("/medicine/medicineModify")
    public String medicineModify(Model model) {

        return "admin/medicine/medicineModify";
    }

    @GetMapping("/medicine/medicineDelete")
    public String medicineDelete(Model model) {
        return "admin/medicine/medicineDelete";
    }
}