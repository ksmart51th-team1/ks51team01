package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.dto.*;
import team01.yaksutor.admin.mapper.AdMedicineMapper;
import team01.yaksutor.dto.Ingredient;
import team01.yaksutor.file.dto.FileRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdMedicineService {
    private final AdMedicineMapper adMedicineMapper;

    public Map<String, Object> getMedicineList(int currentPage){
        // 보여줄 행의 갯수
        int rowPerPage = 10;
        // 테이블의 조회할 시작행
        int startRow = (currentPage - 1) * rowPerPage;

        // 시작할  페이지 설정 초기화
        int startPageNum = 1;

        // 마지막 페이지 설정 초기화
        int endPageNum = 10;

        List<Map<String, Object>> medicineList = adMedicineMapper.getMedicineList(startRow, rowPerPage);

        double cnt = adMedicineMapper.getMedicineListCnt();

        // 마지막 페이지
        int lastPage = (int) Math.ceil(cnt/rowPerPage);

        endPageNum = lastPage < 10 ? lastPage : endPageNum;

        // 동적 페이지 설정
        if(currentPage > 6 && lastPage > 9){
            startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            // 마지막 페이지 번호가 마지막 페이지 수보다 클경우에 페이지 번호를 고정
            if(endPageNum >= lastPage){
                startPageNum = lastPage - 9;
                endPageNum = lastPage;
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("medicineList", medicineList);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("endPageNum", endPageNum);

        return resultMap;
    }
    @Transactional
    public void insertMedicine(RequestMedicine requestMedicine,
                               List<FileRequest> fileRequestList){
        AdMedicine adMedicine = requestMedicine.getMedicalInfo();
        AdSellMedicine adSellMedicine = requestMedicine.getSellMediInfo();
        for(FileRequest fileRequest : fileRequestList){
            adMedicineMapper.insertImg(fileRequest);
        }
        log.info("adMedicine: {}" , adMedicine);
        adMedicineMapper.insertMedicine(adMedicine);
        log.info("adMedicine: {}" , adMedicine);
        String regMId = requestMedicine.getMedicalInfo().getRegMId();
        String mediCode = requestMedicine.getMedicalInfo().getMediCode();
        List<AdIngredient> ingrList = requestMedicine.getMedicalInfo().getIngrList();
        ingrList.forEach(ingr -> {
            ingr.setRegMId(regMId);
            ingr.setMediCode(mediCode);
            adMedicineMapper.insertIngrdient(ingr);
        });
        List<AdEfficacy> effiList = requestMedicine.getMedicalInfo().getEffiList();
        effiList.forEach(effi -> {
            effi.setRegMId(regMId);
            effi.setMediCode(mediCode);
            adMedicineMapper.insertEfficacy(effi);
        });
        AdSellMedicine sellMedicine = requestMedicine.getSellMediInfo();
        String suppCode = adMedicineMapper.getSuppCode(regMId);
        sellMedicine.setSuppCode(suppCode);
        sellMedicine.setMediCode(mediCode);
        sellMedicine.setState("판매중");
        adMedicineMapper.insertSellMedicine(sellMedicine);
    }
}
