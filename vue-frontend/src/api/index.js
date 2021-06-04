import axios from "axios";
import {setInterceptors} from "@/api/common/interceptors";

function createInstance() {
    return axios.create({
        // baseURL: process.env.VUE_APP_API_URL,
    });
}

// 액시오스 초기화 함수
function createInstanceWithAuth() {
    const instance = axios.create({
        // baseURL: `${process.env.VUE_APP_API_URL}${url}`,
    });
    return setInterceptors(instance);
}

export const instance = createInstance();
export const api = createInstanceWithAuth();


// function fetchBoardList() {
//     return axios.get('/boards')
// }
//
// function fetchUser(userId) {
//     return axios.get('/member/findbyid/${userId}')
// }
//
// // 게시글 저장 API
// function createPost(postData) {
//     return axios.post('/boards/post', postData);
// }
