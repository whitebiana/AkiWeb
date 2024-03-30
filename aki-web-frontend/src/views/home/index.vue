<template>
  <h1>首页</h1>
</template>

<script setup lang="ts">
import * as fsrsJs from "fsrs.js";

let fsrs = new fsrsJs.FSRS();
let card = new fsrsJs.Card();
let rating = fsrsJs.Rating;
let state = fsrsJs.State;

let now = new Date(2022, 10, 29, 12, 30, 0, 0);

let scheduling_cards = fsrs.repeat(card, now);
card = scheduling_cards[rating.Again].card;
let reviewLog = scheduling_cards[rating.Again].review_log;

scheduling_cards = fsrs.repeat(card, new Date(2022, 10, 29, 12, 36, 0, 0));
card = scheduling_cards[rating.Again].card;
// reviewLog = scheduling_cards[rating.Again].review_log;

console.log(card);
</script>

<style lang="less" scoped></style>
