<template>
    <div class="content-view">
        <v-container>
            <div id="side-nav" class="mt-3 mb-3 d-fixed">
                <div class="side-nav-box">
                    <router-link class="btn btn-light mr-2" to="/admin/main">
                        <div class="side-nav-box-default"><span class="btn-text">서비스 현황</span></div>
                    </router-link>
                </div>
                <div class="side-nav-box">
                    <router-link class="btn btn-light mr-2" to="/admin/cmnty">
                        <div class="side-nav-box-default"><span class="btn-text">커뮤니티 관리</span></div>
                    </router-link>
                </div>
                <div class="side-nav-box">
                    <router-link class="btn btn-light mr-2" to="/admin/member">
                        <div class="side-nav-box-default"><span class="btn-text">회원 관리</span></div>
                    </router-link>
                </div>
                <div class="side-nav-box">
                    <router-link class="btn btn-light mr-2" to="/admin/qna">
                        <div class="side-nav-box-default"><span class="btn-text">Q&A 관리</span></div>
                    </router-link>
                </div>
                <div class="side-nav-box">
                    <router-link class="btn btn-light mr-2" to="/admin/memberlist">
                        <div class="side-nav-box-default"><span class="btn-text">회원 조회</span></div>
                    </router-link>
                </div>
            </div>
        </v-container>
    </div>
</template>

<script>
export default {}
</script>

<style scoped>
@import '../../css/cmnty/main.css';
</style>