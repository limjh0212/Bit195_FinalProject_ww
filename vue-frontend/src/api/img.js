import {api} from '@/api/index'

// 이미지 저장
export function saveImg(formData){
    return api.post('/api/cmnty/postimg', formData);
}
// 이미지 출력
// export function getImg(num){
//     return api.get(`/api/cmnty/getimg/${num}`, {responseType : "arraybuffer"});
// }
// 이미지 + 포스트 : OOTD 저장
export function saveOotdPost(ootdData){
    return api.post('/api/cmnty/ootdpost', ootdData);
}
// 이미지 리스트 출력
export function getImgList(num){
    return api.get(`/api/cmnty/getimglist/${num}`);
}
// 이미지 카드 형식 리스트 출력
export function getOotdList(){
    return api.get('/api/cmnty/getootdlist');
}
// 내글보기
export function getMyOotdList(uid){
    return api.get(`/api/cmnty/myootdlist/${uid}`);
}
export function getRecoImgList(){
    return api.get('/api/reco/imgList');
}
export function getFileNameList(){
    return api.get('/api/reco/filenamelist');
}
export function getWelcomeImg(){
    return api.get('/api/welcome/imglist');
}