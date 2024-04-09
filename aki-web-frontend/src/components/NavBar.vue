<template>
  <a-menu
    mode="horizontal"
    :default-selected-keys="['/']"
    @menu-item-click="handleMenuClick"
  >
    <a-menu-item
      key="asdfasdf"
      :style="{ padding: 0, marginRight: '38px' }"
      disabled
    >
      <div
        :style="{
          width: '80px',
          height: '30px',
          borderRadius: '2px',
          background: 'var(--color-fill-3)',
          cursor: 'text',
        }"
      />
    </a-menu-item>
    <a-menu-item key="/">错题本</a-menu-item>
    <a-menu-item key="/add/">添加</a-menu-item>
    <a-menu-item key="/search/">搜索</a-menu-item>
  </a-menu>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

const handleMenuClick = (key: string) => {
  router.push(key);
};
</script>

<style lang="less" scoped></style>
