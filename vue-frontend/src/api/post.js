import {api} from '@/api/index'

export function savePost(postData) {
    return api.post('/api/cmnty/post', postData)
}

export function freeboardList() {
    return api.get('/api/freeBoard/post')
}

export function OOTDList() {
    return api.get('/api/OOTD/post')
}

export function qnaList() {
    return api.get('/api/qna/post')
}
