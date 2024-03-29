<template>
  <div class="container">
    <a-layout class="layout">
      <a-layout-header class="header">
        <NavBar />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view v-slot="{ Component }">
          <!-- <transition> -->
          <component :is="Component" />
        </router-view>
      </a-layout-content>
      <a-layout-footer class="footer">
        <Footer />
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="less">
.container {
  height: 100vh;

  .layout {
    height: 100%;

    .header {
      /* height: 50px; */
      background-color: var(--color-bg-2);
      border-bottom: var(--color-border-2) 1px solid;
    }

    .footer {
      // height: 360px;
      text-align: center;
    }
  }
}

body[arco-theme="light"] .footer {
  color: #fff;
  background-color: #1a1a1a;
}

body[arco-theme="dark"] .footer {
  background-color: var(--color-bg-1);
  color: var(--color-text-1);
}

body[arco-theme="light"] .content {
  background-color: var(--color-bg-2);
}

body[arco-theme="dark"] .content {
  background-color: var(--color-bg-1);
}
</style>
