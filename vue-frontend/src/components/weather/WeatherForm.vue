<template>
    <div>
        <h1>오늘의 날씨</h1>
        <table>
            <thead>
                <tr>
                    <th>날짜</th>
                    <th>위치</th>
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
                    <td>{{ this.weatherInfo.name}}</td>
                    <td><v-img :src="this.iconSrc"></v-img></td>
                    <td>{{ this.weatherInfo.weather[0].main }}</td>
                    <td>{{ this.weatherInfo.weather[0].description }}</td>
                    <td>{{ this.weatherInfo.main.temp }}℃</td>
                    <td>{{ this.weatherInfo.main.temp_max }}℃</td>
                    <td>{{ this.weatherInfo.main.temp_min }}℃</td>
                    <td>{{ this.weatherInfo.main.humidity }}</td>
                    <td>{{ this.weatherInfo.wind.speed }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {loadCoords, weatherData } from "@/api/weather";

export default {
    data() {
        return {
            weatherInfo   : {},
            iconSrc : "",
        }
    },
    methods: {
        fetchWeather() {
            loadCoords();
            this.weatherInfo = weatherData;
            console.log(this.weatherInfo);
            this.iconSrc = `https://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png`;
            console.log(this.iconSrc);
            },
    },
    created() {
        this.fetchWeather();
    },
};
</script>

<style scoped>

</style>