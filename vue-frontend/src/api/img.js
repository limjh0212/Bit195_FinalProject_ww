import {api} from '@/api/index'

// 이미지 저장
export function saveImg(formData){
    return api.post('/api/cmnty/postimg', formData);
}
// 이미지 출력
export function getImg(num){
    return api.get(`/api/cmnty/getimg/${num}`);
}
// 진행중
export function byteToSrc(byteArray){
    var imgsrc = "data:image/png;base64," + btoa(String.fromCharCode.apply(null, new Uint8Array(byteArray)));
    return imgsrc;
}