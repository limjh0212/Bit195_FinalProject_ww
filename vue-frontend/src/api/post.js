import {api} from '@/api/index'

//게시글 저장
export function savePost(postData) {
    return api.post('/api/cmnty/post', postData)
}

//게시글 수정
export function updatePost(postId, postData) {
    return api.patch(`/api/cmnty/post/${postId}`, postData)
}

//자유게시판 목록 조회
export function freeboardList(page) {
    return api.get(`/api/cmnty/freeBoard/posts?page=${page}`)
}

//OOTD 목록 조회
export function OOTDList() {
    return api.get('/api/cmnty/OOTD/posts')
}

//Q&A 목록 조회
export function qnaList() {
    return api.get('/api/cmnty/qna/posts')
}

//게시글 상세 조회
export function freeBoardView(postId, uid) {
    return api.get(`/api/cmnty/post/${postId}?uid=${uid}`);
}