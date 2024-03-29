<template>
  <div class="menu-demo">
    <a-menu
      mode="horizontal"
      :default-selected-keys="['1']"
      @menu-item-click="handleMenuClick"
    >
      <a-menu-item
        key="0"
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
      <a-menu-item key="/decks/">Decks</a-menu-item>
      <a-menu-item key="/add/">Add</a-menu-item>
      <a-menu-item key="/search/">Search</a-menu-item>
    </a-menu>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

const handleMenuClick = (key: string) => {
  router.push(key);
};
</script>

<style lang="less" scoped>
.menu-demo {
  box-sizing: border-box;
  width: 100%;
  background-color: var(--color-neutral-2);
}
</style>
