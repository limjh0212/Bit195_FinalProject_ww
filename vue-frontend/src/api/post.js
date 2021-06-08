import {api} from '@/api/index'

export function savePost(postData) {
    return api.post('/api/cmnty/post', postData)
}

export function freeboardList() {
    return api.get('/api/cmnty/freeBoard/posts')
}

export function OOTDList() {
    return api.get('/api/cmnty/OOTD/posts')
}

export function qnaList() {
    return api.get('/api/cmnty/qna/posts')
}
