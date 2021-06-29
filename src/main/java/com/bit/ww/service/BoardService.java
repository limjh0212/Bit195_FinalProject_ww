package com.bit.ww.service;

import com.bit.ww.dto.BoardDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.BoardRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    // 페이징
    private static final int CNTPAGENUM = 5; // 화면에서 보이는 페이지 번호 개수
    private static final int CNTPAGEPOST = 10; // 한 페이지에서 보이는 게시글 개수
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private double cntPosts = 0.0; // 총 포스트 개수 ( 중복 코드 줄이기 위해 )

    // 공통 - Entity -> DTO
    public PostDTO convertEntityToDTO(PostEntity postEntity) {
        return PostDTO.builder()
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
                .likecount(postEntity.getLikecount())
                .isanswered(postEntity.isIsanswered())
                .istemp(postEntity.isIstemp())
                .tempnum(postEntity.getTempnum())
                .regdate(postEntity.getRegdate())
                .editdate(postEntity.getEditdate())
                .build();
    }

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

    // 게시판 이름으로 게시판 조회
    @Transactional
    public BoardDTO findBoard(String boardname) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findByBoardname(boardname);
        BoardEntity boardEntity = boardEntityWrapper.get();
        return BoardDTO.builder()
                .boardnum(boardEntity.getBoardnum())
                .boardname(boardEntity.getBoardname())
                .lastnum(boardEntity.getLastnum())
                .build();
    }

    // 게시판 이름으로 게시물 리스트 조회 + 페이징없음
    @Transactional
    public List<PostDTO> findPostsNoPage(String boardname) {
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByNumDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    // 게시판 이름으로 게시물 리스트 조회 + 페이징
    @Transactional
    public List<PostDTO> findPosts(String boardname, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findAllByBoardnameOrderByNumDesc(boardname, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListFindPosts(String boardname, int pagenum) { // pagenum = 현재페이지
        cntPosts = (double) this.cntPosts(boardname);
        return pageList(pagenum);
    }

    @Transactional
    public int cntPosts(String boardname) {
        return postRepository.countAllByBoardname(boardname);
    }

    // 게시물 단건 조회
    @Transactional
    public PostDTO getPost(int num) {
        Optional<PostEntity> postEntityWrapper = postRepository.findById(num);
        PostEntity postEntity = postEntityWrapper.get();

        return convertEntityToDTO(postEntity);
    }

    // 게시물 등록 - 게시물 등록 시 해당 게시판의 마지막 번호에 +1
    @Transactional
    public PostEntity savePost(PostDTO postDTO) {
        if (postDTO.getBoardname().equals("freeBoard")) {
            postDTO.setBoardnum(1);
        } else if (postDTO.getBoardname().equals("OOTD")) {
            postDTO.setBoardnum(2);
        } else if (postDTO.getBoardname().equals("hotDeal")) {
            postDTO.setBoardnum(3);
        } else if (postDTO.getBoardname().equals("qna")) {
            postDTO.setBoardnum(4);
        } else if (postDTO.getBoardname().equals("temp")) {
            postDTO.setBoardnum(5);
        }
        return postRepository.save(postDTO.toEntity());
    }

    // 게시물 등록 시 마지막 번호 수정 용
    @Transactional
    public BoardEntity saveBoard(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO.toEntity());
    }

    // 게시물 삭제 - 게시물 등록자와 로그인 회원정보가 다르면 실패
    @Transactional
    public void deletePost(int num) {
        postRepository.deleteById(num);
    }

    //검색 기능
    //제목 + 페이징
    @Transactional
    public List<PostDTO> searchTitle(String search, String boardname, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByTitleIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(search, boardname, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListSearchTitle(String search, String boardname, int pagenum) {
        cntPosts = (double) cntSearchTitle(search, boardname);
        return pageList(pagenum);
    }

    @Transactional
    public int cntSearchTitle(String search, String boardname) {
        return postRepository.countAllByTitleIgnoreCaseIsContainingAndBoardname(search, boardname);
    }

    // 제목+내용+ 페이징
    @Transactional
    public List<PostDTO> searchTitleOrContent(String search, String boardname, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(search, search, boardname, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListSearchTitleOrContent(String search, String boardname, int pagenum) {
        cntPosts = (double) cntSearchTitleOrContent(search, boardname);
        return pageList(pagenum);
    }

    @Transactional
    public int cntSearchTitleOrContent(String search, String boardname) {
        return postRepository.countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardname(search, search, boardname);
    }

    // 내글보기 페이징 없음.
    @Transactional
    public List<PostDTO> searchUidNoPage(String boardname, String uid) {
        List<PostEntity> postEntities = postRepository.findAllByBoardnameAndUidOrderByNumDesc(boardname, uid);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    // 내글보기 & QnA 작성자 검색
    @Transactional
    public List<PostDTO> searchUid(String boardname, String uid, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByBoardnameAndUidOrderByNumDesc(boardname, uid, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListSearchUid(String boardname, String uid, int pagenum) {
        cntPosts = (double) cntSearchUid(boardname, uid);
        return pageList(pagenum);
    }

    @Transactional
    public int cntSearchUid(String boardname, String uid) {
        return postRepository.countAllByBoardnameAndUid(boardname, uid);
    }

    // 내글보기 - 검색 - 제목
    @Transactional
    public List<PostDTO> searchUidAndTitle(String boardname, String uid, String search, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByBoardnameAndUidAndTitleIgnoreCaseOrderByNumDesc(boardname, uid, search, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListSearchUidAndTitle(String boardname, String uid, String search, int pagenum) {
        cntPosts = (double) cntSearchUidAndTitle(boardname, uid, search);
        return pageList(pagenum);
    }

    @Transactional
    public int cntSearchUidAndTitle(String boardname, String uid, String search) {
        return postRepository.countAllByBoardnameAndUidAndTitleIgnoreCase(boardname, uid, search);
    }

    // 내글보기 - 검색 - 제목
    @Transactional
    public List<PostDTO> searchUidAndTitleOrContent(String boardname, String uid, String search, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingOrderByNumDesc(boardname, uid, search, search, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListSearchUidAndTitleOrContent(String boardname, String uid, String search, int pagenum) {
        cntPosts = (double) cntSearchUidAndTitleOrContent(boardname, uid, search);
        return pageList(pagenum);
    }

    @Transactional
    public int cntSearchUidAndTitleOrContent(String boardname, String uid, String search) {
        return postRepository.countAllByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContaining(boardname, uid, search, search);
    }

    // 인기글
    @Transactional
    public List<PostDTO> findReadcountPosts(String boardname) {
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByReadcountDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public List<PostDTO> findLikecountPosts(String boardname) {
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByLikecountDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    // 관리자 및 마이페이지
    // uid가 쓴 모든 글
    @Transactional
    public List<PostDTO> findUidPosts(String uid, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByUidOrderByNumDesc(uid, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListUidPosts(String uid, int pagenum) {
        cntPosts = (double) cntUidPosts(uid);
        return pageList(pagenum);
    }

    @Transactional
    public int cntUidPosts(String uid) {
        return postRepository.countAllByUid(uid);
    }

    // writer가 쓴 모든 글
    @Transactional
    public List<PostDTO> findWriterPosts(String writer, int pagenum) {
        Page<PostEntity> pagePosts = postRepository.findByWriterOrderByNumDesc(writer, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListWriterPosts(String writer, int pagenum) {
        cntPosts = (double) cntWriterPosts(writer);
        return pageList(pagenum);
    }

    @Transactional
    public int cntWriterPosts(String writer) {
        return postRepository.countAllByWriter(writer);
    }

    // 총 게시물 수
    @Transactional
    public int cntTotalPosts(int boardnum) {
        return postRepository.countAllByBoardnumIsNot(boardnum);
    }

    // 최근 게시물 수
    @Transactional
    public int cntRecentPosts(LocalDateTime start, LocalDateTime end, int boardnum) {
        return postRepository.countAllByRegdateIsBetweenAndBoardnumIsNot(start, end, boardnum);
    }

    @Transactional
    public int cntRecentBoard(LocalDateTime start, LocalDateTime end, int boardnum) {
        return postRepository.countAllByRegdateIsBetweenAndBoardnum(start, end, boardnum);
    }

    // 미해결 문의
    @Transactional
    public List<PostDTO> findNotAnswered(int pagenum) {
        int boardnum = 4;
        Page<PostEntity> pagePosts = postRepository.findByBoardnumAndIsansweredIsFalseOrderByNumDesc(boardnum, PageRequest.of(pagenum - 1, CNTPAGEPOST, Sort.by(Sort.Direction.DESC, "num")));
        List<PostEntity> postEntities = pagePosts.getContent();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDTOList.add(this.convertEntityToDTO(postEntity));
        }
        return postDTOList;
    }

    @Transactional
    public Integer[] pageListNotAnswered(int pagenum) {
        cntPosts = (double) cntNotAnswered();
        return pageList(pagenum);
    }

    @Transactional
    public int cntNotAnswered() {
        int boardnum = 4;
        return postRepository.countAllByBoardnumAndIsansweredIsFalse(boardnum);
    }
}
