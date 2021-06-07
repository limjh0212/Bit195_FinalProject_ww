import {instance} from './index';

// 회원가입 API
export function registerUser(userData) {
    return instance.post('/auth/signup', userData);
}

// 로그인 API
export function loginUser(userData) {
    return instance.post('/auth/login', userData);
}

export function checkId(id) {
    return instance.post('/auth/checkId', id);
}


