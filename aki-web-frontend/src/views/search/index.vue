<template>
  <h1>搜索</h1>
</template>

<script setup lang="ts">
import * as fsrsJs from "fsrs.js";
let fsrs = new fsrsJs.FSRS();
let card = new fsrsJs.Card();
let rating = fsrsJs.Rating;
let state = fsrsJs.State;

console.log(card);
</script>

<style lang="less" scoped></style>
