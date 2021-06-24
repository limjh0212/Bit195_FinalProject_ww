<template>
  <div>
    <apexchart width="400" type="line" :options="options" :series="series"></apexchart>
  </div>
</template>

<script>
export default {
  name: "LineChart",
  data() {
    return {
      options: {
        chart: {
          id: 'example'
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
        }
      },
      series: [{
        name: 'data',
        data: [30, 40, 45, 50, 49, 60, 70, 91]
      }],
    }
  },
  methods: {

  },
}
</script>

<style scoped>

</style>