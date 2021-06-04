package com.bit.ww.service;

import com.bit.ww.dto.QuestionDTO;
import com.bit.ww.entity.QuestionEntity;
import com.bit.ww.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;
    // CRUD 메인 기능
    @Transactional
    public List<QuestionDTO> getQuestionList() {
        List<QuestionEntity> questionEntities = questionRepository.findAllByOrderByNumDesc();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .num(questionEntity.getNum())
                    .writer(questionEntity.getWriter())
                    .title(questionEntity.getTitle())
                    .content(questionEntity.getContent())
                    .img(questionEntity.getImg())
                    .isanswered(questionEntity.isIsanswered())
                    .regdate(questionEntity.getRegdate())
                    .build();

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Transactional
    public QuestionDTO getPost(int num) {
        Optional<QuestionEntity> questionEntityWrapper = questionRepository.findById(num);
        QuestionEntity questionEntity = questionEntityWrapper.get();

        QuestionDTO questionDTO = QuestionDTO.builder()
                .num(questionEntity.getNum())
                .writer(questionEntity.getWriter())
                .title(questionEntity.getTitle())
                .content(questionEntity.getContent())
                .img(questionEntity.getImg())
                .isanswered(questionEntity.isIsanswered())
                .regdate(questionEntity.getRegdate())
                .build();

        return questionDTO;
    }

    @Transactional
    public QuestionEntity savePost(QuestionDTO questionDTO){
        return questionRepository.save(questionDTO.toEntity());
    }

    @Transactional
    public void deletePost(int num){
        questionRepository.deleteById(num);
    }

    @Transactional
    public int countList(){
        return questionRepository.countAllByNumIsNotNull();
    }
    // 최근 작성된 문의 목록
    @Transactional
    public List<QuestionDTO> getRecentList(LocalDateTime startdate, LocalDateTime enddate) {
        List<QuestionEntity> questionEntities = questionRepository.findAllByRegdateIsBetweenOrderByNumDesc(startdate, enddate);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .num(questionEntity.getNum())
                    .writer(questionEntity.getWriter())
                    .title(questionEntity.getTitle())
                    .content(questionEntity.getContent())
                    .img(questionEntity.getImg())
                    .isanswered(questionEntity.isIsanswered())
                    .regdate(questionEntity.getRegdate())
                    .build();

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Transactional
    public int countRecentList(LocalDateTime startdate, LocalDateTime enddate){
        return questionRepository.countAllByRegdateIsBetween(startdate, enddate);
    }
    // 미답변 문의 목록
    @Transactional
    public List<QuestionDTO> getListByIsansweredIsFalse() {
        List<QuestionEntity> questionEntities = questionRepository.findAllByIsansweredIsFalseOrderByNumDesc();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .num(questionEntity.getNum())
                    .writer(questionEntity.getWriter())
                    .title(questionEntity.getTitle())
                    .content(questionEntity.getContent())
                    .img(questionEntity.getImg())
                    .isanswered(questionEntity.isIsanswered())
                    .regdate(questionEntity.getRegdate())
                    .build();

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Transactional
    public int countListByIsansweredIsFalse(){
        return questionRepository.countAllByIsansweredIsFalse();
    }
    //검색 기능 - 작성자
    @Transactional
    public List<QuestionDTO> getListByWriter(String writer) {
        List<QuestionEntity> questionEntities = questionRepository.findAllByWriterOrderByNumDesc(writer);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .num(questionEntity.getNum())
                    .writer(questionEntity.getWriter())
                    .title(questionEntity.getTitle())
                    .content(questionEntity.getContent())
                    .img(questionEntity.getImg())
                    .isanswered(questionEntity.isIsanswered())
                    .regdate(questionEntity.getRegdate())
                    .build();

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Transactional
    public int countListByWriter(String writer){
        return questionRepository.countAllByWriter(writer);
    }

}
