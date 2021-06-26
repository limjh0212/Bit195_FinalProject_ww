<template>
  <div>
    <div id="wrapper">
      <div class="content-area">
        <div class="container-fluid">
          <div class="text-right mt-3 mb-3 d-fixed">
<!--            <a-->
<!--                href="https://github.com/apexcharts/apexcharts.js/tree/master/samples/vanilla-js/dashboards/modern"-->
<!--                target="_blank"-->
<!--                class="btn btn-outline-primary mr-2"-->
<!--            >-->
<!--              <span class="btn-text">View Code</span>-->
<!--            </a>-->
<!--            <button class="btn btn-success" id="fusionexport-btn">-->
<!--              <span-->
<!--                  class="spinner-border spinner-border-sm"-->
<!--                  role="status"-->
<!--                  aria-hidden="true"-->
<!--              ></span>-->
<!--              <span class="btn-text">Download as PDF</span>-->
<!--            </button>-->
<!--          </div>-->
          <div class="main">
            <div class="row sparkboxes mt-4 mb-4">
              <div class="col-md-4">
                <div class="box box1">
                  <div id="spark1"></div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="box box2">
                  <div id="spark2"></div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="box box3">
                  <div id="spark3"></div>
                </div>
              </div>
            </div>

            <div class="row mt-5 mb-4">
              <div class="col-md-6">
                <div class="box">
                  <div id="bar">
                    <!--                    내가 추가한 것-->
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="box">
                  <div id="donut">
                    <!--                    내가 추가한 것-->
                  </div>
                </div>
              </div>
            </div>

            <div class="row mt-4 mb-4">
              <div class="col-md-6">
                <div class="box">
                  <div id="area"></div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="box">
                  <div id="line">
                    <!--                    내가 추가한 것-->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "TestPage",
  components: {DonutChart, LineChart, BarChart},
}
</script>

<style scoped>

</style>