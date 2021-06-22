<template>
  <div>
    <div>
        <h1>오늘의 날씨</h1>
        <h3>{{ this.locationInfo }}</h3>
        <table>
            <thead>
                <tr>
                    <th>날짜</th>
                    <th>아이콘</th>
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
                    <td>{{ $moment().format('YYYY-MM-DD') }}</td>
                    <td><v-img :src="this.iconSrc"></v-img></td>
                    <td>{{ this.currentWeather.weather[0].main }}</td>
                    <td>{{ this.currentWeather.weather[0].description }}</td>
                    <td>{{ this.currentWeather.temp }}℃</td>
                    <td>{{ this.weeklyWeather[0].temp.max }}℃</td>
                    <td>{{ this.weeklyWeather[0].temp.min }}℃</td>
                    <td>{{ this.currentWeather.humidity }}</td>
                    <td>{{ this.currentWeather.wind_speed }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
      <h1>일주일 날씨</h1>
      <table>
        <thead>
        <tr>
          <th>날짜</th>
          <th>아이콘</th>
          <th>날씨</th>
          <th>설명</th>
          <th>최고기온</th>
          <th>최저기온</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for = "(item,idx) in weeklyWeather" :key="idx" >
          <td>{{ $moment().add(idx, 'days').format('YYYY-MM-DD') }}</td>
          <td><v-img :src="`https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png`"></v-img></td>
          <td>{{ item.weather[0].main }}</td>
          <td>{{ item.weather[0].description }}</td>
          <td>{{ item.temp.max }}℃</td>
          <td>{{ item.temp.min }}℃</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import {loadCoords, weatherData } from "@/api/weather";

export default {
    data() {
        return {
            locationInfo   : '',
            currentWeather : {},
            weeklyWeather : [],
            iconSrc : "",
        }
    },
    methods: {
        fetchWeather() {
            loadCoords();
            this.locationInfo = weatherData.timezone;
            this.currentWeather = weatherData.current;
            this.iconSrc = `https://openweathermap.org/img/wn/${weatherData.current.weather[0].icon}@2x.png`;
            this.weeklyWeather = weatherData.daily;
            },
    },
    created() {
        this.fetchWeather();
    },
};
</script>

<style scoped>

</style>