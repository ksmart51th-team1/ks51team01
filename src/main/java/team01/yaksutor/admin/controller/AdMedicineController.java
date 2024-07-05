package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.dto.AdEfficacy;
import team01.yaksutor.admin.dto.AdIngredient;
import team01.yaksutor.admin.dto.AdMedicine;
import team01.yaksutor.admin.dto.AdSellMedicine;
import team01.yaksutor.admin.mapper.AdMedicineMapper;
import team01.yaksutor.dto.Efficacy;
import team01.yaksutor.dto.Ingredient;
import team01.yaksutor.dto.Medicine;
import team01.yaksutor.dto.SellMedicine;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/medicine")
@RequiredArgsConstructor
@Slf4j
public class AdMedicineController {
    private final AdMedicineMapper adMedicineMapper;
    List<AdIngredient> newAdIngredientList = new ArrayList<AdIngredient>();
    List<AdEfficacy> newAdEfficacyList = new ArrayList<AdEfficacy>();

    @GetMapping("/medicineInsert")
    public String medicineInsert(Model model) {
        model.addAttribute("title", "의약품 등록");
        model.addAttribute("content", "의약품 등록");
        return "admin/medicine/medicineInsert";
    }

    @GetMapping("/medicineSearchList")
    public String medicineSearchList(Model model) {
        model.addAttribute("title", "의약품 리스트");
        model.addAttribute("content", "의약품 리스트");

        return "admin/medicine/medicineSearchList";
    }

    @GetMapping("/medicineModify")
    public String medicineModify(Model model) {

        model.addAttribute("title", "의약품 수정");
        model.addAttribute("content", "의약품 수정");

        return "admin/medicine/medicineModify";
    }

    @GetMapping("/medicineDelete")
    public String medicineDelete(Model model) {

        model.addAttribute("title", "의약품 삭제");
        model.addAttribute("content", "의약품 삭제");

        return "admin/medicine/medicineDelete";
    }

    @GetMapping("/medicineDetailView")
    public String medicineDetailView(Model model) {

        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");

        return "admin/medicine/medicineDetailView";
    }

    @PostMapping("/checkLevel")
    @ResponseBody
    public int checkLevel(@RequestParam(value="regMId") String regMId,
                             Model model) {
        log.info("아이디 체크 : {}", regMId);
        int idLevel;
        idLevel = adMedicineMapper.checkLevel(regMId);

        return idLevel;
    }

    @PostMapping("/medicineInsert")
    public String medicineInsert(AdMedicine adMedicine,
                                 AdSellMedicine adSellMedicine,
                                 List<AdIngredient> adIngredientList,
                                 List<AdEfficacy> adEfficacyList) {

        newAdIngredientList = adIngredientList;
        newAdEfficacyList = adEfficacyList;
        log.info("의약품 등록 Medicine: {}", adMedicine);
        log.info("의약품 등록 SellMedicine: {}", adSellMedicine);
        log.info("의약품 등록 IngredientList: {}", newAdIngredientList);
        log.info("의약품 등록 EfficacyList: {}", newAdEfficacyList);

        return "redirect:/admin/adminMain";
    }
}