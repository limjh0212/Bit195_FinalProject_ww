package com.bit.ww.service;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.entity.CmntEntity;
import com.bit.ww.repository.CmntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CmntService {
    // 페이징
    private static final int CNTPAGENUM = 5; // 화면에서 보이는 페이지 번호 개수
    private static final int CNTPAGEPOST = 10; // 한 페이지에서 보이는 게시글 개수
    private final CmntRepository cmntRepository;
    private double cntPosts = 0.0; // 총 포스트 개수 ( 중복 코드 줄이기 위해 )

    // 공통 - 페이지 리스트
    @Transactional
    public Integer[] pageList(int pagenum) { // pagenum = 현재페이지
        // 총 게시글 수만 각 페이지리스트 메소드에서 입력하면 됨.
        // 페이지 리스트
        Integer[] pageList = new Integer[CNTPAGENUM];
        // 총 게시글 수 기준 마지막 페이지 번호 계산 (올림)
        int lastPagenum = (int) (Math.ceil((cntPosts / CNTPAGEPOST)));
        // 현재 페이지 기준으로 화면에서 보이는 마지막 페이지 번호 계산
        int lastViewPagenum;
        if (lastPagenum <= 5) {
            lastViewPagenum = lastPagenum;
            pagenum = 1;
        } else {
            if (pagenum <= 3) {
                pagenum = 1;
                lastViewPagenum = 5;
            } else {
                if (pagenum + 2 <= lastPagenum) {
                    lastViewPagenum = pagenum + 2;
                    pagenum = pagenum - 2;
                } else {
                    lastViewPagenum = lastPagenum;
                    pagenum = lastPagenum - 4;
                }
            }
        }
        // 페이지 번호 할당 - 보이는 페이지 수 5개 안에 들어갈 숫자 범위
        for (int value = pagenum, i = 0; value <= lastViewPagenum; value++, i++) {
            pageList[i] = value;
        }
        return pageList;
    }

    public CmntDTO convertEntityToDTO(CmntEntity cmntEntity) {
        return CmntDTO.builder()
                .num(cmntEntity.getNum())
                .boardnum(cmntEntity.getBoardnum())
                .postnum(cmntEntity.getPostnum())
                .cmntnum(cmntEntity.getCmntnum())
                .depth(cmntEntity.getDepth())
                .writer(cmntEntity.getWriter())
                .content(cmntEntity.getContent())
                .regdate(cmntEntity.getRegdate())
                .editdate(cmntEntity.getEditdate())
                .build();
    }

    @Transactional
    public List<CmntDTO> getCmntList(int boardnum, int postnum, int depth) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByBoardnumAndPostnumAndDepthOrderByNumAsc(boardnum, postnum, depth);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }

    @Transactional
    public List<CmntDTO> getCmnt2List(int boardnum, int postnum, int cmntnum, int depth) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByBoardnumAndPostnumAndCmntnumAndDepthOrderByNumAsc(boardnum, postnum, cmntnum, depth);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }

    @Transactional
    public CmntEntity saveCmnt(CmntDTO cmntDTO) {
        return cmntRepository.save(cmntDTO.toEntity());
    }

    @Transactional
    public void deleteCmnt(int num) {
        cmntRepository.deleteById(num);
    }

    @Transactional
    public boolean existCmnt2(int cmntnum) {
        return cmntRepository.existsByCmntnum(cmntnum);
    }

    @Transactional
    public List<CmntDTO> getMyCmntList(String writer, int pagenum) {
        Page<CmntEntity> pageCmnts = cmntRepository.findAllByWriterOrderByNumDesc(writer, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<CmntEntity> cmntEntities = pageCmnts.getContent();
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }

    @Transactional
    public Integer[] pageListMyCmntList(String writer, int pagenum) {
        int cntMyCmnts = cmntRepository.countAllByWriter(writer);
        cntPosts = (double) cntMyCmnts;
        return pageList(pagenum);
    }

    public CmntDTO getAnswer(int boardnum, int postnum) {
        Optional<CmntEntity> cmntEntityWrapper = cmntRepository.findByBoardnumAndPostnum(boardnum, postnum);
        CmntEntity cmntEntity = cmntEntityWrapper.get();

        return convertEntityToDTO(cmntEntity);
    }
}
