<template>
  <a-table :show-header="false" :data="decks" :pagination="false">
    <template #columns>
      <a-table-column title="name" data-index="name" />
      <a-table-column title="Actions" :width="100">
        <template #cell="{ record }">
          <a-dropdown position="bottom">
            <a-button>Actions</a-button>
            <template #content>
              <a-doption>Rename</a-doption>
              <a-doption>Delete</a-doption>
            </template>
          </a-dropdown>
        </template>
      </a-table-column>
    </template>
  </a-table>
  <br />

  <a-row>
    <a-col flex="200">
      <div>
        <a-button type="outline" @click="addDeck">
          <template #icon>
            <icon-plus />
          </template>
          <!-- Use the default slot to avoid extra spaces -->
          <template #default>添加错题本</template>
        </a-button>
      </div>
    </a-col>
    <a-col flex="auto">
      <a-pagination :total="50" />
    </a-col>
  </a-row>

  <a-modal
    v-model:visible="visible"
    title="input DeckName"
    @cancel="handleCancel"
    @before-ok="handleBeforeOk"
  >
    <a-form :model="form">
      <a-form-item field="deckName" label="deckName">
        <a-input v-model="form.deckName" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { IconPlus } from "@arco-design/web-vue/es/icon";
import { useRouter } from "vue-router";
import {
  Notification,
  TableColumnData,
  type TableData,
} from "@arco-design/web-vue";
import axios from "axios";
import { TestControllerService } from "@/api";
import { Deck } from "@/types/global";

const show = ref(true);

//拿取到跳转到的信息
const router = useRouter();

//使用抽屉式的添加方法
const addDeck = () => {
  visible.value = true;
};

const visible = ref(false);
const form = reactive({
  deckName: "",
});

//在此处提交牌组的信息
const handleBeforeOk = (done: any) => {
  console.log(form);
  window.setTimeout(() => {
    done();
    // prevent close
    // done(false)
  }, 3000);
};
const handleCancel = () => {
  visible.value = false;
};

const decks = ref<Deck[]>([]);

const handleCellClick = (
  record: TableData,
  column: TableColumnData,
  ev: Event
) => {
  console.log(column);
};

const loadData = async () => {
  const res = await TestControllerService.list();

  if (res.code === "00000") {
    decks.value = res.data as Deck[];
  } else Notification.error("获取牌组失败");
};

onMounted(async () => {
  loadData();
});
</script>

<style lang="less"></style>
