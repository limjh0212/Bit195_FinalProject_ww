import {api} from '@/api/index'

//전체 회원 조회
export function fetchMemberList() {
    return api.get('/api/member/findall')
}

//관리 페이지 Main
export function fetchAdminMain() {
    return api.get('/api/mngmt/cmnty')
}

//커뮤니티 관리 페이지
export function fetchCmnty() {
    return api.get('/api/mngmt/cmnty')
}

//회원 관리 페이지
export function fetchMember() {
    return api.get('/api/mngmt/member')
}

//Q&A 관리 페이지
export function fetchQna() {
    return api.get('/api/mngmt/qna')
}

//회우너 삭제
export function deleteId(id) {
    return api.delete(`/api/member/delete/${id}`);
}
