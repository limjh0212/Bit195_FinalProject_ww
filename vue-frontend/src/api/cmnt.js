import {api} from '@/api/index'

//댓글 저장
export function api_saveCmnt1(postData) {
    return api.post('/api/cmnty/cmnt/post', postData)
}

//댓글 수정
export function api_editCmnt1(cmntId, postData) {
    return api.patch(`/api/cmnty/cmnt/${cmntId}`, postData)
}

//댓글 삭제(대댓글 없는 경우)
export function api_deleteCmnt1(cmntId) {
    return api.delete(`/api/cmnty/cmnt/${cmntId}`)
}

//댓글 삭제(대댓글 있는 경우)
export function api_deleteCmnt1exist(cmntId, postData) {
    return api.put(`/api/cmnty/cmnt/${cmntId}`, postData)
}

//대댓글 작성
export function api_saveCmnt2(cmntId, postData) {
    return api.post(`/api/cmnty/cmnt/post/${cmntId}`, postData)
}

