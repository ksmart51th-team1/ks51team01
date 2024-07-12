package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.pharmacy.mapper.PhMedicineMapper;

import java.util.List;

@Service("phMedicineService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhMedicineService {
    private final PhMedicineMapper phMedicineMapper;

    /*============================================
             의약품판매등록
    ============================================*/

    // 조회
    public List<SellMedicine> getSellMedicineList(){
        List<SellMedicine> sellMedicineList = phMedicineMapper.getSellMedicineList();
        return sellMedicineList;
    }
}
