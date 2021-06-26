<template>
    <div>
        <div>
            <h1>오늘의 날씨</h1>
            <h3>{{ this.locationInfo }}</h3>
            <li v-for="item in wwResult"><img :src="item" alt="이미지" /></li>

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
                        <td>
                            <v-img :src="this.iconSrc"></v-img>
                        </td>
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
                    <tr v-for="(item,i) in weeklyWeather" :key="i">
                        <td>{{ $moment().add(i, 'days').format('YYYY-MM-DD') }}</td>
                        <td>
                            <v-img :src="`https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png`"></v-img>
                        </td>
                        <td>{{ item.weather[0].main }}</td>
                        <td>{{ item.weather[0].description }}</td>
                        <td>{{ item.temp.max }}℃</td>
                        <td>{{ item.temp.min }}℃</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-if="isLoading">
            <LoadingSpinner></LoadingSpinner>
        </div>
    </div>
</template>

<script>
import {api_wwData, loadCoords, weatherData} from "@/api/weather";
import LoadingSpinner from "@/components/common/LoadingSpinner";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            locationInfo  : '',
            currentWeather: {},
            weeklyWeather : [],
            iconSrc       : "",
            isLoading     : false,
            wwResult      : {}
        }
    },
    methods: {
        async fetchWeather() {
            await loadCoords();
            this.locationInfo = weatherData.timezone;
            this.currentWeather = weatherData.current;
            this.iconSrc = `https://openweathermap.org/img/wn/${weatherData.current.weather[0].icon}@2x.png`;
            this.weeklyWeather = weatherData.daily;
            console.log(weatherData)

            const Data = {
                user_id: this.$store.state.id,
                temp   : weatherData.daily[0].temp.day
            }
            console.log(Data)
            //날씨-코디 img 예측값 가져오기
            const {data} = await api_wwData(Data);
            this.wwResult = data;
            console.log(this.wwResult)

        },
    }
    ,
    created() {
        this.fetchWeather();
    },
};
</script>
