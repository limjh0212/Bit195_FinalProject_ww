import {api} from '@/api/index'

//회원 id 조회
export function aboutme(id) {
    return api.get(`/api/member/findbyid/${id}`);
}

//회원 img 조회
export function getimg(num) {
    return api.get(`/api/member/getimg/${num}`, {responseType : "arraybuffer"});
}