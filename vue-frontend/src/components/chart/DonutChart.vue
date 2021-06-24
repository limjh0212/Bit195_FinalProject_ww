<template>
  <div>
    <apexchart width="380" type="donut" :options="options" :series="series"></apexchart>
  </div>
</template>

<script>
export default {
  name: "DonutChart",
  data() {
    return {
      options: {},
      series: [44, 55, 41, 17, 15],
      }
  },
  methods: {

  },
}
</script>

<style scoped>

</style>