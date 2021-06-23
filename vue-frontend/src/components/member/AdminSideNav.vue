<template>
    <div class="content-view">
      <v-container>
          <div id="side-nav">
                <div class="side-nav-box">
                    <router-link to="/admin/main"><div class="side-nav-box-default">서비스 현황</div></router-link>
                </div>
                <div class="side-nav-box">
                    <router-link to="/admin/cmnty"><div class="side-nav-box-default">커뮤니티 관리</div></router-link>
                </div>
                <div class="side-nav-box">
                    <router-link to="/admin/member"><div class="side-nav-box-default">회원 관리</div></router-link>
                </div>
                <div class="side-nav-box">
                    <router-link to="/admin/qna"><div class="side-nav-box-default">Q&A 관리</div></router-link>
                </div>
                <div class="side-nav-box">
                    <router-link to="/admin/memberlist"><div class="side-nav-box-default">회원 조회</div></router-link>
                </div>
          </div>
      </v-container>
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>
@import '../../css/cmnty/main.css';
</style>