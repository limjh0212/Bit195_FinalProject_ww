<template>
    <div>
      <div id="main-form">
        <div id="todayWeather" class="todayWeather box">
          <div class="card-header card-title">
            <h4>오늘의 날씨</h4>
          </div>
          <div class="card-location">현위치 {{ this.locationInfo }}</div>
          <div class="card-icon">
            <div class="card-round"><img class="weatherIcon" :src="this.iconSrc"></div>
          </div>
          <div class="card-weather">
            <div class="card-weather-box">
              <div class="card-weather1">{{ this.currentWeather.weather[0].main }}</div>
              <div class="card-weather3">{{ this.currentWeather.weather[0].description }}</div>
              <div class="card-weather3">평균 기온  {{ this.currentWeather.temp }}℃</div>
              <div class="card-weather2">
                <div class="card-weather2-left"> 최고 기온  {{ this.weeklyWeather[0].temp.max }}℃ </div>
                <div class="card-weather2-right"> 최저 기온  {{ this.weeklyWeather[0].temp.min }}℃</div>
              </div>
            </div>
          </div>
        </div>
        <div id="todayWear" class="todayWear box">
          <div class="card-title">
            <div class="card-header">
              <h4>오늘의 코디</h4>
            </div>
          </div>
          <div class="card-wear" v-for="item in wwResult">
            <div class="todayReco"><img class="wear-img" :src="item" alt="이미지" /></div>
          </div>
        </div>
        <div id="weeklyWeather" class="weeklyWeather box">
          <div class="card-title">
            <div class="card-header">
              <h4>주간 날씨 정보</h4>
            </div>
          </div>
          <div class="card-week">
            <div class="card-day" v-if="i > 0" v-for="(item,i) in weeklyWeather" :key="i">
              <div class="card-day-box">{{ $moment().add(i, 'days').format('YYYY-MM-DD') }}</div>
              <div class="card-day-box">
                <div><img class="card-day-icon" :src="`https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png`"></div>
              </div>
              <div class="card-day-box">{{ item.weather[0].main }}</div>
              <div class="card-day-box">{{ item.temp.day }}℃</div>
              <div class="card-day-box">
                <div class="card-day-box-left">{{ item.temp.max }}℃</div>
                <div class="card-day-box-right">{{ item.temp.min }}℃</div>
              </div>
            </div>
          </div>
        </div>
        </div>
<!--        <div>-->
<!--            <h1>일주일 날씨</h1>-->
<!--            <table>-->
<!--                <thead>-->
<!--                    <tr>-->
<!--                        <th>날짜</th>-->
<!--                        <th>아이콘</th>-->
<!--                        <th>날씨</th>-->
<!--                        <th>설명</th>-->
<!--                        <th>최고기온</th>-->
<!--                        <th>최저기온</th>-->
<!--                    </tr>-->
<!--                </thead>-->
<!--                <tbody>-->
<!--                    <tr v-for="(item,i) in weeklyWeather" :key="i">-->
<!--                        <td>{{ $moment().add(i, 'days').format('YYYY-MM-DD') }}</td>-->
<!--                        <td>-->
<!--                            <v-img @click="" :src="`https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png`"></v-img>-->
<!--                        </td>-->
<!--      method(){-->
<!--      this.dd = item[i].temp.day-->
<!--      }-->
<!--                        <td>{{ item.weather[0].main }}</td>-->
<!--                        <td>{{ item.weather[0].description }}</td>-->
<!--                        <td>{{ item.temp.max }}℃</td>-->
<!--                        <td>{{ item.temp.min }}℃</td>-->
<!--                    </tr>-->
<!--                </tbody>-->
<!--            </table>-->
<!--        </div>-->
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
            console.log(this.weatherData);

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
<style scoped>
@import '../../css/common/mainP.css';
</style>
