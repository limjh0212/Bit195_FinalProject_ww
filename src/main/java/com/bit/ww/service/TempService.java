package com.bit.ww.service;

import com.bit.ww.dto.TempDTO;
import com.bit.ww.entity.TempEntity;
import com.bit.ww.repository.TempRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TempService {
    private TempRepository tempRepository;
    // CRUD 메인 기능
    @Transactional
    public List<TempDTO> getTempList(String writer) {
        List<TempEntity> tempEntities = tempRepository.findAllByWriterOrderByNumDesc(writer);
        List<TempDTO> tempDTOList = new ArrayList<>();

        for (TempEntity tempEntity : tempEntities) {
            TempDTO tempDTO = TempDTO.builder()
                    .num(tempEntity.getNum())
                    .boardnum(tempEntity.getBoardnum())
                    .writer(tempEntity.getWriter())
                    .title(tempEntity.getTitle())
                    .content(tempEntity.getContent())
                    .regdate(tempEntity.getRegdate())
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public TempDTO getTemp(int num) {
        Optional<TempEntity> tempEntityWrapper = tempRepository.findById(num);
        TempEntity tempEntity = tempEntityWrapper.get();

        TempDTO tempDTO = TempDTO.builder()
                .num(tempEntity.getNum())
                .boardnum(tempEntity.getBoardnum())
                .writer(tempEntity.getWriter())
                .title(tempEntity.getTitle())
                .content(tempEntity.getContent())
                .regdate(tempEntity.getRegdate())
                .build();

        return tempDTO;
    }

    @Transactional
    public TempEntity saveTemp(TempDTO tempDTO){
        return tempRepository.save(tempDTO.toEntity());
    }

    @Transactional
    public void deleteTemp(int num){
        tempRepository.deleteById(num);
    }

    @Transactional
    public int countTempList(String writer){
        return tempRepository.countAllByWriter(writer);
    }

}
