<template>
  <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
  <div ref="main" style="width: 600px; height: 400px"></div>
</template>

<script setup lang="ts">
import echarts from "@/utils/echarts";

const main = ref();

onMounted(() => {
  // 基于准备好的dom，初始化echarts实例
  // 绘制图表
  echarts.init(main.value).setOption({
    title: {
      text: "ECharts 入门示例",
    },
    tooltip: {},
    xAxis: {
      data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
    },
    yAxis: {},
    series: [
      {
        name: "销量",
        type: "bar",
        data: [5, 20, 36, 10, 10, 20],
      },
    ],
  });
});
</script>

<style lang="less" scoped></style>
@/utils/echarts
