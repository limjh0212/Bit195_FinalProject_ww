import {api} from '@/api/index'

// 이미지 저장
export function saveImg(formData) {
    return api.post('/api/cmnty/postimg', formData);
}

// 이미지 + 포스트 : OOTD 저장
export function saveOotdPost(ootdData) {
    return api.post('/api/cmnty/ootdpost', ootdData);
}

// OOTD 상세보기
export function getImgList(num) {
    return api.get(`/api/cmnty/getimglist/${num}`);
}

// OOTD 수정
export function updateOotdPost(num, ootdData) {
    return api.post(`/api/cmnty/ootdpost/${num}`, ootdData);
}

// 이미지 카드 형식 리스트 출력
export function getOotdList() {
    return api.get('/api/cmnty/getootdlist');
}

// 내글보기
export function getMyOotdList(uid) {
    return api.get(`/api/cmnty/myootdlist/${uid}`);
}

//추천 이미지
export function getRecoImgList() {
    return api.get('/api/reco/imgList');
}

//추천 이미지 파일이름
export function getFileNameList() {
    return api.get('/api/reco/filenamelist');
}

//회원가입 스타일 선택
export function userPreference(Data) {
    return api.post('http://54.180.151.43:5000/userpreference', Data);
}

//웰컴페이지 이미지 출력용
export function getWelcomeImg() {
    return api.get('/api/welcome/imglist');
}