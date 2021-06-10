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
                    <td>{{ $moment().format('YYYY-DD-MM') }}</td>
                    <td>{{ this.weatherInfo.name }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                    <td>{{ }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {loadCoords, weatherData} from "@/api/weather";

export default {
    data() {
        return {
            weatherInfo: {},
        }
    },
    methods: {
        fetchWeather() {
            loadCoords();
            this.weatherInfo = weatherData;
            console.log(this.weatherInfo);
        },
    },
    created() {
        this.fetchWeather();
    }
    ,
}
;
</script>

<style scoped>

</style>