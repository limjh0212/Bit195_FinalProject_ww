import {api} from '@/api/index'

//게시글 저장
export function savePost(postData) {
    return api.post('/api/cmnty/post', postData)
}

//게시글 수정
export function updatePost(postId, postData) {
    return api.patch(`/api/cmnty/post/${postId}`, postData)
}

//게시글 삭제
export function deletePost(postId) {
    return api.delete(`/api/cmnty/post/${postId}`)
}

//자유게시판 목록 조회
export function freeboardList(page) {
    return api.get(`/api/cmnty/freeBoard/posts?page=${page}`)
}

//OOTD 목록 조회
export function OOTDList() {
    return api.get('/api/cmnty/OOTD/postList')
}

//Q&A 목록 조회
export function qnaList(page) {
    return api.get(`/api/cmnty/qna/posts?page=${page}`)
}

//게시글 상세 조회
export function freeBoardView(postId, uid) {
    return api.get(`/api/cmnty/post/${postId}?uid=${uid}`);
}

//게시글 검색 조회(제목+내용)
export function freeBoardTitle(keyword, page) {
    return api.get(`/api/cmnty/freeBoard/searchTitle/${keyword}?page=${page}`);
}

//게시글 검색 조회(제목+내용)
export function freeBoardAll(keyword, page) {
    return api.get(`/api/cmnty/freeBoard/searchTitleOrContent/${keyword}?page=${page}`);
}

//Qna 내글보기(사용자)
export function boardUserList(userid, num, boardname) {
    return api.get(`/api/cmnty/${boardname}/searchUid/${userid}?page=${num}`);
}

//Qna 내글보기(사용자) - 페이징 없음
export function boardUserListNoPage(userid, boardname) {
    return api.get(`/api/cmnty/${boardname}/myList/${userid}`);
}

//Qna 내글보기(관리자)
export function qnaAdminList(num) {
    return api.get(`/api/cmnty/qna/posts?page=${num}`);
}

//좋아요 생성
export function likePost(postNum, Data) {
    return api.post(`/api/cmnty/like/${postNum}`, Data);
}

//좋아요 생성
export function myCmnt(writer) {
    return api.get(`/api/mngmt/mypage/mycmnts?writer=${writer}`);
}

