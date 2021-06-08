package com.bit.ww.service;

import com.bit.ww.dto.BoardDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.BoardRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@DynamicUpdate
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    // 게시판 이름으로 게시판 조회
    @Transactional
    public BoardDTO findBoard(String boardname){
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findByBoardname(boardname);
        BoardEntity boardEntity = boardEntityWrapper.get();
        BoardDTO boardDTO= BoardDTO.builder()
                .boardnum(boardEntity.getBoardnum())
                .boardname(boardEntity.getBoardname())
                .lastnum(boardEntity.getLastnum())
                .build();
        return boardDTO;
    }

    // 게시판 이름으로 게시물 리스트 조회
    @Transactional
    public List<PostDTO> findPosts(String boardname){
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByNumDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntPosts(String boardname){
        return postRepository.countAllByBoardname(boardname);
    }
    // 게시물 단건 조회
    @Transactional
    public PostDTO getPost(int num){
        Optional<PostEntity> postEntityWrapper = postRepository.findById(num);
        PostEntity postEntity = postEntityWrapper.get();

        PostDTO postDTO = PostDTO.builder()
                .num(postEntity.getNum())
                .boardname(postEntity.getBoardname())
                .boardnum(postEntity.getBoardnum())
                .postnum(postEntity.getPostnum())
                .uid(postEntity.getUid())
                .writer(postEntity.getWriter())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .img(postEntity.getImg())
                .readcount(postEntity.getReadcount())
                .isanswered(postEntity.isIsanswered())
                .istemp(postEntity.isIstemp())
                .tempnum(postEntity.getTempnum())
                .regdate(postEntity.getRegdate())
                .editdate(postEntity.getEditdate())
                .build();
        return postDTO;
    }
    // 게시물 등록 - 게시물 등록 시 해당 게시판의 마지막 번호에 +1
    @Transactional
    public PostEntity savePost(PostDTO postDTO){
        if (postDTO.getBoardname().equals("freeBoard")){
            postDTO.setBoardnum(1);
        }else if (postDTO.getBoardname().equals("OOTD")){
            postDTO.setBoardnum(2);
        }else if (postDTO.getBoardname().equals("hotDeal")){
            postDTO.setBoardnum(3);
        }else if (postDTO.getBoardname().equals("qna")){
            postDTO.setBoardnum(4);
        }else if (postDTO.getBoardname().equals("temp")){
            postDTO.setBoardnum(5);
        }
        return postRepository.save(postDTO.toEntity());
    }
    // 게시물 등록 시 마지막 번호 수정 용
    @Transactional
    public BoardEntity saveBoard(BoardDTO boardDTO){
        return boardRepository.save(boardDTO.toEntity());
    }
    // 게시물 삭제 - 게시물 등록자와 로그인 회원정보가 다르면 실패
    @Transactional
    public void deletePost(int num){
        postRepository.deleteById(num);
    }
    //검색 기능
    //제목
    @Transactional
    public List<PostDTO> searchTitle(String search, String boardname){
        List<PostEntity> postEntities = postRepository.findByTitleIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(search, boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntSearchTitle(String search, String boardname){
        return postRepository.countAllByTitleIgnoreCaseIsContainingAndBoardname(search, boardname);
    }
    // 제목+내용
    @Transactional
    public List<PostDTO> searchTitleOrContent(String search, String boardname){
        List<PostEntity> postEntities = postRepository.findByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(search, search, boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntSearchTitleOrContent(String search, String boardname){
        return postRepository.countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardname(search, search, boardname);
    }
    // 내글보기
    @Transactional
    public List<PostDTO> searchUid(String boardname, String uid){
        List<PostEntity> postEntities = postRepository.findByBoardnameAndUidOrderByNumDesc(boardname, uid);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntSearchUid(String boardname, String uid){
        return postRepository.countAllByBoardnameAndUid(boardname, uid);
    }
    // 내글보기 - 검색 - 제목
    @Transactional
    public List<PostDTO> searchUidAndTitle(String boardname, String uid, String search){
        List<PostEntity> postEntities = postRepository.findByBoardnameAndUidAndTitleIgnoreCaseOrderByNumDesc(boardname, uid, search);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntSearchUidAndTitle(String boardname, String uid, String search){
        return postRepository.countAllByBoardnameAndUidAndTitleIgnoreCase(boardname, uid, search);
    }
    // 내글보기 - 검색 - 제목
    @Transactional
    public List<PostDTO> searchUidAndTitleOrContent(String boardname, String uid, String search){
        List<PostEntity> postEntities = postRepository.findByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingOrderByNumDesc(boardname, uid, search,search);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntSearchUidAndTitleOrContent(String boardname, String uid, String search){
        return postRepository.countAllByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContaining(boardname, uid, search,search);
    }
    // 인기글
    @Transactional
    public List<PostDTO> findPopularPosts(String boardname){
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByReadcountDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    // 관리자 및 마이페이지
    // uid가 쓴 모든 글
    @Transactional
    public List<PostDTO> findUidPosts(String uid){
        List<PostEntity> postEntities = postRepository.findByUidOrderByNumDesc(uid);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntUidPosts(String uid){
        return postRepository.countAllByUid(uid);
    }
    // writer가 쓴 모든 글
    @Transactional
    public List<PostDTO> findWriterPosts(String writer){
        List<PostEntity> postEntities = postRepository.findByWriterOrderByNumDesc(writer);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntWriterPosts(String writer){
        return postRepository.countAllByWriter(writer);
    }
    // 총 게시물 수
    @Transactional
    public int cntTotalPosts(int boardnum){
        return postRepository.countAllByBoardnumIsNot(boardnum);
    }
    // 최근 게시물 수
    @Transactional
    public int cntRecentPosts(LocalDateTime start, LocalDateTime end, int boardnum){
        return postRepository.countAllByRegdateIsBetweenAndBoardnumIsNot(start, end, boardnum);
    }
    @Transactional
    public int cntRecentBoard(LocalDateTime start, LocalDateTime end, int boardnum){
        return postRepository.countAllByRegdateIsBetweenAndBoardnum(start, end, boardnum);
    }
    // 미해결 문의
    @Transactional
    public List<PostDTO> findNotAnswered(){
        int boardnum = 4;
        List<PostEntity> postEntities = postRepository.findByBoardnumAndIsansweredIsFalseOrderByNumDesc(boardnum);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntNotAnswered() {
        int boardnum = 4;
        return postRepository.countAllByBoardnumAndIsansweredIsFalse(boardnum);
    }
}
