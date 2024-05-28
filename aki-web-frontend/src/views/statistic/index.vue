<template>
  <div class="container">
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div ref="calendar" style="width: 800px; height: 250px; margin: 0 auto" />
    <div ref="study" style="width: 800px; height: 600px; margin: 0 auto" />
    <div ref="memory" style="width: 800px; height: 500px; margin: 0 auto" />
  </div>
</template>

<script setup lang="ts">
// import echarts from "@/utils/echarts";
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
      Math.floor(Math.random() * 10000),
    ]);
  }
  return data;
}

onMounted(() => {
  // 基于准备好的dom，初始化echarts实例
  // 绘制图表
  echarts.init(calendar.value).setOption({
    title: {
      top: 30,
      left: "center",
      text: "Overview",
    },
    tooltip: {},
    visualMap: {
      min: 0,
      max: 10000,
      type: "piecewise",
      orient: "horizontal",
      left: "center",
      top: 65,
    },
    calendar: {
      top: 120,
      left: 30,
      right: 30,
      cellSize: ["auto", 13],
      range: "2016",
      itemStyle: {
        borderWidth: 0.5,
      },
      yearLabel: { show: false },
    },
    series: {
      type: "heatmap",
      coordinateSystem: "calendar",
      data: getVirtualData("2016"),
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
        data: ["6天前", "5天前", "4天前", "3天前", "前天", "昨天", "今天"],
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
        emphasis: {
          focus: "series",
        },
        data: [320, 332, 301, 334, 390, 330, 320],
      },
      {
        name: "认识的错题量",
        type: "bar",
        stack: "Ad",
        emphasis: {
          focus: "series",
        },
        data: [120, 132, 101, 134, 90, 230, 210],
      },
      {
        name: "模糊的错题量",
        type: "bar",
        stack: "Ad",
        emphasis: {
          focus: "series",
        },
        data: [220, 182, 191, 234, 290, 330, 310],
      },
      {
        name: "忘记的错题量",
        type: "bar",
        stack: "Ad",
        emphasis: {
          focus: "series",
        },
        data: [150, 232, 201, 154, 190, 330, 410],
      },
    ],
  });

  echarts.init(memory.value).setOption({
    legend: {},
    tooltip: {
      trigger: "axis",
      showContent: false,
    },
    dataset: {
      source: [
        ["product", "5天前", "4天前", "3天前", "前天", "昨天", "今天"],
        ["记忆持久度>90天的错题量", 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
        ["记忆持久度>60天的错题量", 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
        ["记忆持久度>30天的错题量", 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
        ["记忆持久度>10天的错题量", 25.2, 37.1, 41.2, 18, 33.9, 49.1],
      ],
    },
    xAxis: { type: "category" },
    yAxis: { gridIndex: 0 },
    grid: { top: "55%" },
    series: [
      {
        type: "line",
        smooth: true,
        seriesLayoutBy: "row",
        emphasis: { focus: "series" },
      },
      {
        type: "line",
        smooth: true,
        seriesLayoutBy: "row",
        emphasis: { focus: "series" },
      },
      {
        type: "line",
        smooth: true,
        seriesLayoutBy: "row",
        emphasis: { focus: "series" },
      },
      {
        type: "line",
        smooth: true,
        seriesLayoutBy: "row",
        emphasis: { focus: "series" },
      },
      {
        type: "pie",
        id: "pie",
        radius: "30%",
        center: ["50%", "25%"],
        emphasis: {
          focus: "self",
        },
        label: {
          formatter: "{b}: {@5天前} ({d}%)",
        },
        encode: {
          itemName: "product",
          value: "5天前",
          tooltip: "5天前",
        },
      },
    ],
  });
});
</script>

<style lang="less" scoped></style>
