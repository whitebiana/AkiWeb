<template>
  <router-view v-slot="{ Component }">
    <!-- <transition> -->
    <component :is="Component" />
  </router-view>
</template>

<script setup lang="ts"></script>

<style scoped></style>
