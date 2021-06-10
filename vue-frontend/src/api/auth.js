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

//날씨 API
export function getCurrentWeather(lat, lon) {
    return instance.get(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=bb6aa03a2b159ae4faf856d6cb781642&units=metric`);
}
