import {apexchart} from "@/utils/charts";

export var grid = apexchart.grid = {
    padding: {
        right: 0,
        left: 0
    }
}

export var dataLabels = apexchart.dataLabels = {
    enabled: false
}
export var randomizeArray = function (arg) {
    var array = arg.slice();
    var currentIndex = array.length, temporaryValue, randomIndex;

    while (0 !== currentIndex) {

        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;

        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }

    return array;
}

// 예시 데이터
export var sparklineData = [47, 45, 54, 38, 56, 24, 65, 31, 37, 39, 62, 51, 35, 41, 35, 27, 93, 53, 61, 27, 54, 43, 19, 46];
export var colorPalette = ['#00D8B6','#008FFB',  '#FEB019', '#FF4560', '#775DD0']