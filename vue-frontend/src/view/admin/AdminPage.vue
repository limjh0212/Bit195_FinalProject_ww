<template>
  <div id="main-form" class="content-view">
    <div class="side-nav">
      <AdminSideNav></AdminSideNav>
    </div>
    <div class="side-content-view">
        <div id="wrapper">
          <div class="content-area">
            <div class="container-fluid">
<!--              <div class="text-right mt-3 mb-3 d-fixed">-->
<!--                <button class="btn btn-success" id="fusionexport-btn" @click="pdfDownload">-->
<!--              <span-->
<!--                  class="spinner-border spinner-border-sm"-->
<!--                  role="status"-->
<!--                  aria-hidden="true"-->
<!--              ></span>-->
<!--                  <span class="btn-text">Download as PDF</span>-->
<!--                </button>-->
<!--              </div>-->
              <div class="main">
                <router-view></router-view>
              </div>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import AdminSideNav from "@/components/member/AdminSideNav";

export default {
  components: {AdminSideNav},
  methods: {
  },
}
</script>

<style scoped>
@import '../../css/common/mainP.css';

</style>