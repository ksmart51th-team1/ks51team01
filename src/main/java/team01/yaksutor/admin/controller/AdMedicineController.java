package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team01.yaksutor.admin.dto.*;
import team01.yaksutor.admin.mapper.AdMedicineMapper;
import team01.yaksutor.admin.service.AdMedicineService;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/medicine")
@RequiredArgsConstructor
@Slf4j
public class AdMedicineController {
    private final FileUtils fileUtils;
    private final AdMedicineMapper adMedicineMapper;
    private final AdMedicineService adMedicineService;

    /**
     * 의약품 등록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/medicineInsert")
    public String medicineInsert(Model model) {
        model.addAttribute("title", "의약품 등록");
        model.addAttribute("content", "의약품 등록");
        return "admin/medicine/medicineInsert";
    }

    /**
     * 의약품 목록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param currentPage
     * @return
     */
    @GetMapping("/medicineSearchList")
    public String medicineSearchList(Model model,
                                     @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
        Map<String, Object> medicineListObject = adMedicineService.getMedicineList(currentPage);

        @SuppressWarnings("unchecked")
        List<SellMediInfo> medicineList = (List<SellMediInfo>) medicineListObject.get("medicineList");
        int lastPage = (int) medicineListObject.get("lastPage");
        int startPageNum = (int) medicineListObject.get("startPageNum");
        int endPageNum = (int) medicineListObject.get("endPageNum");

        model.addAttribute("title", "의약품 리스트");
        model.addAttribute("content", "의약품 리스트");
        model.addAttribute("medicineList", medicineList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);

        return "admin/medicine/medicineSearchList";
    }

    /**
     * 의약품 수정 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param goodsCode
     * @return
     */
    @GetMapping("/medicineModify")
    public String medicineModify(Model model,
                                 @RequestParam(value="goodsCode") String goodsCode) {
        Map<String, Object> mediObj = adMedicineService.getMedicalInfo(goodsCode);
        RequestMedicine requestMedicine = (RequestMedicine) mediObj.get("requestMedicine");
        String suppName = mediObj.get("suppName").toString();

        model.addAttribute("requestMedicine", requestMedicine);
        model.addAttribute("title", "의약품 수정");
        model.addAttribute("content", "의약품 수정");

        return "admin/medicine/medicineModify";
    }

    /**
     * 의약품 삭제 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param goodsCode
     * @return
     */
    @GetMapping("/medicineDelete")
    public String medicineDelete(Model model,
                                 @RequestParam(value="goodsCode") String goodsCode) {

        String mediCode = adMedicineMapper.getMediCode(goodsCode);
        String mediName = adMedicineMapper.getMedicineName(mediCode);

        model.addAttribute("mediCode", mediCode);
        model.addAttribute("mediName", mediName);
        model.addAttribute("title", "의약품 삭제");
        model.addAttribute("content", "의약품 삭제");

        return "admin/medicine/medicineDelete";
    }

    /**
     * 의약품 상세 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param goodsCode
     * @return
     */
    @GetMapping("/medicineDetailView")
    public String medicineDetailView(Model model,
                                     @RequestParam(value="goodsCode") String goodsCode) {
        Map<String, Object> mediObj = adMedicineService.getMedicalInfo(goodsCode);
        RequestMedicine requestMedicine = (RequestMedicine) mediObj.get("requestMedicine");
        String suppName = mediObj.get("suppName").toString();
        String effies ="";
        List<AdEfficacy> efficacyList = requestMedicine.getMedicalInfo().getEffiList();
        boolean first = true;
        // 의약품 효능 한줄 출력
        for (AdEfficacy adEfficacy : efficacyList) {
            if(!first){
                effies += ", ";
                first = false;
            }
            effies += adEfficacy.getEfficacy();
            effies += " ";
        }

        model.addAttribute("effies", effies);
        model.addAttribute("requestMedicine", requestMedicine);
        model.addAttribute("suppName", suppName);
        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");

        return "admin/medicine/medicineDetailView";
    }

    /**
     * 판매자 아이디 확인 ajax요청
     * 작성자: 길범진
     * @param regMId
     * @param model
     * @return
     */
    @PostMapping("/checkLevel")
    @ResponseBody
    public int checkLevel(@RequestParam(value="regMId") String regMId,
                             Model model) {
        log.info("아이디 체크 : {}", regMId);
        int idLevel;
        idLevel = adMedicineMapper.checkLevel(regMId);

        return idLevel;
    }

    /**
     * 의약품 및 판매 의약품 등록 ajax요청
     * 작성자: 길범진
     * @param model
     * @param requestMedicine
     * @param multipartFile
     * @return
     */
    @PostMapping("/medicineInsert")
    @ResponseBody
    public String medicineInsert(Model model,
                                 @RequestPart(value="requestMedicine") RequestMedicine requestMedicine,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile){

        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            requestMedicine.getMedicalInfo().setMediImg(file.getFileIdx());
            requestMedicine.getSellMediInfo().setMediImg(file.getFileIdx());
        });
        log.info("requestMedicine: {}", requestMedicine);
        log.info("img: {}",multipartFile.size());
        adMedicineService.insertMedicine(requestMedicine, fileList);

        return "redirect:/admin/medicine/medicineSearchList";
    }

    /**
     * 의약품 수정 ajax요청
     * 작성자: 길범진
     * @param model
     * @param requestMedicine
     * @param multipartFile
     * @return
     */
    @PostMapping("/medicineModify")
    @ResponseBody
    public String medicineModify(Model model,
                                 @RequestPart(value="requestMedicine") RequestMedicine requestMedicine,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile){
        log.info("requestMedicine: {}", requestMedicine);

        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            requestMedicine.getMedicalInfo().setMediImg(file.getFileIdx());
            requestMedicine.getSellMediInfo().setMediImg(file.getFileIdx());
        });
        adMedicineService.modifyMedicine(requestMedicine, fileList);

        return "redirect:/admin/medicine/medicineSearchList";
    }

    /**
     * 의약품 삭제 ajax요청 기능-판매 의약품 판매상태 수정
     * 작성자: 길범진
     * @param mediCode
     * @return
     */
    @PostMapping("/medicineDelete")
    public String medicineDelete(@RequestParam(value="mediCode") String mediCode){
        adMedicineService.deleteMedicine(mediCode);

        return "redirect:/admin/medicine/medicineSearchList";
    }

}