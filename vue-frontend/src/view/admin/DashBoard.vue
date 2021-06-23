<template>
  <div id="chart"><p>Today's Chart</p>
    <apexchart width="500" height="350" type="bar" :options="options" :series="series"/>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //그래프
      options: {
        xaxis:
            {categories: ['1월', '2월', '3월', '4월', '5월', '6월'],},
        colors: ['#FEDD36'],
        fill: {
          type: 'gradient',
          gradient: {
            shadeIntensity: 1,
            type: "vertical",
            opacityFrom: 0.7,
            opacityTo: 0.9,
            colorStops: [{offset: 0, color: "#fbc2eb", opacity: 1}, {offset: 100, color: "#a18cd1", opacity: 1}]
          }
        },
        plotOptions: {bar: {columnWidth: '30%', endingShape: 'rounded', dataLabels: {position: 'top'}}},
      }, series: [{name: 'data', data: [1, 2, 3, 4, 5, 4]}],
    }
  }, methods: {}
}
</script>
<style>
#chart {
  padding: 50px;
}

#chart p {
  font-size: 30px;
  font-weight: bold;
  color: #737373;
  margin: 0 0 50px 0;
}
</style>
