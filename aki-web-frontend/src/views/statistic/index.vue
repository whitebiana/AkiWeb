<template>
  <div class="container">
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div ref="calendar" style="width: 800px; height: 250px; margin: 0 auto" />
    <div ref="study" style="width: 1000px; height: 700px; margin: 0 auto" />
    <div ref="memory" style="width: 1000px; height: 500px; margin: 0 auto" />
  </div>
</template>

<script setup lang="ts">
// import echarts from "@/utils/echarts";
import { Service } from "@/api";
import { Message } from "@arco-design/web-vue";
import dayjs from "@/utils/dayjs";
import * as echarts from "echarts";

const calendar = ref();
const study = ref();
const memory = ref();

function getVirtualData(year: string) {
  const date = +echarts.time.parse(year + "-01-01");
  const end = +echarts.time.parse(+year + 1 + "-01-01");
  const dayTime = 3600 * 24 * 1000;
  const data: [string, number][] = [];
  for (let time = date; time < end; time += dayTime) {
    data.push([
      echarts.time.format(time, "{yyyy}-{MM}-{dd}", false),
      Math.floor(Math.random() * 40),
    ]);
  }
  return data;
}

let dailyData;
let stabilityData;
let reviewData;

const loadData = async () => {
  const res = await Promise.all([
    Service.getDailyCountsForCurrentYear(),
    Service.countByStability(),
    Service.countByRating(),
  ]);

  if (res[0].code === "00000") {
    dailyData = res[0].data.map((item) => {
      return [item.date, item.count];
    });
  } else Message.error(res[0].msg);

  if (res[1].code === "00000") {
    stabilityData = res[1].data;
  } else Message.error(res[1].msg);

  if (res[2].code === "00000") {
    // 二维数组转置
    reviewData = res[2].data.reduce(
      (prev, next) => next.map((item, i) => (prev[i] || []).concat(next[i])),
      []
    );
  } else Message.error(res[2].msg);
};

onMounted(async () => {
  await loadData();
  // 基于准备好的dom，初始化echarts实例
  // 绘制图表

  echarts.init(calendar.value).setOption({
    title: {
      top: 30,
      left: "center",
      text: "Overview",
    },
    tooltip: {
      valueFormatter(value: number | string, dataIndex: number) {
        return value + "次复习，在" + dailyData[dataIndex][0];
      },
    },
    visualMap: {
      min: 0,
      // max: 30,
      type: "piecewise",
      orient: "horizontal",
      left: "center",
      top: 65,
      pieces: [
        { lte: 0, color: "#ededed" },
        { gte: 1, lte: 9, color: "#bedaff" },
        { gte: 10, lte: 19, color: "#6aa1ff" },
        { gte: 20, lte: 29, color: "#4080ff" },
        { gte: 30, color: "#0e42d2" },
      ],
    },
    calendar: {
      top: 120,
      left: 30,
      right: 30,
      cellSize: ["auto", 16],
      range: dayjs().year(),
      itemStyle: {
        borderWidth: 2,
        borderColor: "#fff",
      },
      splitLine: {
        show: false,
      },
      yearLabel: { show: false },
    },
    series: {
      type: "heatmap",
      coordinateSystem: "calendar",
      data: dailyData,
    },
  });

  echarts.init(study.value).setOption({
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "shadow",
      },
    },
    legend: {},
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    xAxis: [
      {
        type: "category",
        data: [
          "7天前",
          "6天前",
          "5天前",
          "4天前",
          "3天前",
          "前天",
          "昨天",
          "今天",
        ],
      },
    ],
    yAxis: [
      {
        type: "value",
      },
    ],
    series: [
      {
        name: "熟知的错题量",
        type: "bar",
        stack: "asdf",
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#789461",
        },
        data: reviewData[3],
      },
      {
        name: "认识的错题量",
        type: "bar",
        stack: "asdf",
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#ABC270",
        },
        data: reviewData[2],
      },
      {
        name: "模糊的错题量",
        type: "bar",
        stack: "asdf",
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#FEC868",
        },
        data: reviewData[1],
      },
      {
        name: "忘记的错题量",
        type: "bar",
        stack: "asdf",
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#FA7070",
        },
        data: reviewData[0],
      },
    ],
  });

  echarts.init(memory.value).setOption({
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "cross",
        label: {
          backgroundColor: "#6a7985",
        },
      },
    },
    legend: {
      data: [
        "已加入记忆规划的全部错题",
        "记忆持久度>10天的错题量",
        "记忆持久度>30天的错题量",
        "记忆持久度>60天的错题量",
        "记忆持久度>90天的错题量",
      ],
    },
    toolbox: {
      feature: {
        saveAsImage: {},
      },
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    xAxis: [
      {
        type: "category",
        boundaryGap: false,
        data: [
          "7天前",
          "6天前",
          "5天前",
          "4天前",
          "3天前",
          "前天",
          "昨天",
          "今天",
        ],
      },
    ],
    yAxis: [
      {
        type: "value",
      },
    ],
    series: [
      {
        name: "记忆持久度>90天的错题量",
        type: "line",
        stack: "Total",
        areaStyle: {},
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#789461",
        },
        data: stabilityData["ninety"],
      },
      {
        name: "记忆持久度>60天的错题量",
        type: "line",
        stack: "Total",
        areaStyle: {},
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#ABC270",
        },
        data: stabilityData.sisty,
      },
      {
        name: "记忆持久度>30天的错题量",
        type: "line",
        stack: "Total",
        areaStyle: {},
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#FEC868",
        },
        data: stabilityData.thirty,
      },
      {
        name: "记忆持久度>10天的错题量",
        type: "line",
        stack: "Total",
        areaStyle: {},
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#FDA769",
        },
        data: stabilityData.ten,
      },
      {
        name: "已加入记忆规划的全部错题",
        type: "line",
        stack: "Total",
        label: {
          show: true,
          position: "top",
        },
        areaStyle: {},
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#FA7070",
        },
        data: stabilityData.total,
      },
    ],
  });
});
</script>

<style lang="less" scoped></style>
