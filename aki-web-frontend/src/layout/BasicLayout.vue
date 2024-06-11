<template>
  <div class="container">
    <a-layout class="layout">
      <a-affix :offsetTop="0" style="width: calc(100% - 1px)">
        <a-layout-header class="header">
          <NavBar />
        </a-layout-header>
      </a-affix>

      <a-scrollbar style="height: calc(100% - 59px); overflow: auto">
        <a-layout-content class="content">
          <router-view v-slot="{ Component }">
            <!-- <transition> -->
            <component :is="Component" />
          </router-view>
        </a-layout-content>
      </a-scrollbar>
    </a-layout>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="less">
.container {
  height: 100%;

  .layout {
    height: 100%;

    .header {
      /* height: 50px; */
      background-color: var(--color-bg-2);
      border-bottom: var(--color-border-2) 1px solid;
    }

    .content {
      width: 82%;
      height: calc(100vh - 59px);
      padding: 24px;
      margin: 0 auto;
    }
  }
}
</style>
