<template>
  <router-view v-slot="{ Component }">
    <!-- <transition> -->
    <component :is="Component" />
  </router-view>
</template>

<script setup lang="ts"></script>

<style>
* {
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
}

h1 {
  font-size: 200% !important;
  font-weight: 500;
}
</style>
