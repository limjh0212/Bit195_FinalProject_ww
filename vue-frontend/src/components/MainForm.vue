<template>
  <div>
    <h1>오늘의 날씨</h1>
    <table>
      <thead>
      <tr>
        <th>날짜</th>
        <th>위치</th>
        <th>날씨</th>
        <th>설명</th>
        <th>기온</th>
        <th>최고기온</th>
        <th>최저기온</th>
        <th>습도</th>
        <th>풍속</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>{{dateBuilder()}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {getCurrentWeather} from "@/api/auth";

const COORDS = "coords";
function saveCoords(coordsObj){
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
  console.log("Can't get your position.");
}
function askForCoords(){
  navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
}
function loadCoords(){
  const loadedCoords = localStorage.getItem(COORDS); // localStorage에서 위치정보 가져옴
  if (loadedCoords === null) { // 위치 정보가 없으면
    askForCoords(); // 위치 정보 요청 함수
  } else {
    const parseCoords = JSON.parse(loadedCoords); // json형식을 객체 타입으로 바꿔서 저장
    getCurrentWeather(parseCoords.latitude, parseCoords.longitude)
           .then((res) => {
             console.log(res.data);
             const mainTemp = (res.data.main.temp+'℃');
             console.log(mainTemp);
             return res.data;
           }); // 날씨 요청 함수

  }
}

export default {
  data() {
    return {
      weatherInfo: {},
    }
  },
  methods: {
    async fetchData(){
      const {data} = await loadCoords();


    },
    dateBuilder: function () {
      let d = new Date();
      let months = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", ];
      let days = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", ];
      let day = days[d.getDay()];
      let date = d.getDate();
      let month = months[d.getMonth()];
      let year = d.getFullYear();
      return `${day} ${date} ${month} ${year}`;
    },
  },
  created() {
        this.fetchData();
  },
};
</script>

<style scoped>

</style>