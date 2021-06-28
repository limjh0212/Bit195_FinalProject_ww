<template>
    <div style="height: 1000px;">
        <!--<v-container>-->
            <h1> 오늘 날씨엔 뭐입지 ? </h1>
            <div class="welcome-Logo">Weather Wear</div>
            <div>
              <router-link to="/login">Log in</router-link>
              |
              <router-link to="/signup">Sign up</router-link>
            </div>
            <div>
              # 날씨 맞춤 자동 코디 추천
                현재 날씨에 맞는 코디를 자동으로 추천해줍니다.
                오늘 외투를 입어야 할까? 반팔 하나만 입기엔 춥지 않을까?
                고민하지 말고, 날씨 맞춤 자동 코디 기능을 활용해보세요 !
              # 내 취향에 맞는 맞춤형 코디
                회원 가입시 마음에 드는 옷 5 가지를 고르면
                기온에 따라 취향저격 맞춤형 코디를 제공해드립니다 !
              # 수많은 사람들과 소통하는 패션 커뮤니티
                패션에 관심이 많은 사람들과 자유롭게 소통해보세요 !
                나의 오늘의 코디를 공유하고 다른 사람들의 OOTD를 참고할 수 있습니다.

              매일 무슨 옷을 입어야 할지 고민된다면 Weather Wear 서비스를 이용해보세요 !

            </div>
            <div>
              <router-link to="/login">Log in</router-link>
              |
              <router-link to="/signup">Sign up</router-link>
            </div>
        <!--</v-container>-->
    </div>
<!--<SelectImg></SelectImg>-->
</template>

<script>

import SelectImg from "@/view/member/SelectImg";
export default {
    components: {SelectImg},
    methods   : {},
}
</script>

<style scoped>

</style>