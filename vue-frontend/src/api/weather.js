import {instance} from './index';

// 날씨 API
export function get7daysWeather(lat, lon) {
    return instance.get(`https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&exclude=minutely,hourly,alerts&appid=bb6aa03a2b159ae4faf856d6cb781642&units=metric&lang=kr`);
}

const COORDS = "coords";
export let weatherData = '';

function saveCoords(coordsObj) {
    localStorage.setItem(COORDS, JSON.stringify(coordsObj));
}

function handleGeoSuccess(position) {
    console.log(position);
    const latitude = position.coords.latitude;  // 경도
    const longitude = position.coords.longitude;  // 위도
    const coordsObj = {latitude, longitude};
    saveCoords(coordsObj);
}

function handleGeoError(err) {
    console.log("위치를 찾을 수 없습니다.");
}

export function loadCoords() {
    const loadedCoords = localStorage.getItem(COORDS); // localStorage에서 위치정보 가져옴
    if (loadedCoords === null) { // 위치 정보가 없으면
        navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError); // 위치 정보 요청 함수
        loadCoords();

    } else {
        const parseCoords = JSON.parse(loadedCoords); // json형식을 객체 타입으로 바꿔서 저장
        get7daysWeather(parseCoords.latitude, parseCoords.longitude)
            .then((res) => {
                console.log(res);
                weatherData = res.data;
                return res;
            }); // 날씨 요청 함수
    }
}

