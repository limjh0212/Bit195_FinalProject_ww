import {instance} from './index';

// 회원가입 API
export function api_registerUser(userData) {
    return instance.post('/auth/signup', userData);
}

// 로그인 API
export function api_loginUser(userData) {
    return instance.post('/auth/login', userData);
}

// ID 중복확인
export function api_checkId(id) {
    return instance.post('/auth/checkId', id);
}
