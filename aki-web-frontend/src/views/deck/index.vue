<template>
  <a-table :show-header="false" :data="decks" :pagination="false">
    <template #columns>
      <a-table-column title="name" data-index="name" />
      <a-table-column title="Actions" :width="100">
        <template #cell="{ record }">
          <a-dropdown position="bottom">
            <a-button>Actions</a-button>
            <template #content>
              <a-doption @click="rename">Rename</a-doption>
              <a-doption @click="remove">Delete</a-doption>
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
      <a-pagination
        :total="pagination.total"
        :page-size="pagination.pageSize"
        simple
        v-model:current="pagination.current"
        @change="loadData"
      />
    </a-col>
  </a-row>

  <a-modal
    v-model:visible="visible"
    title="input deckname"
    @cancel="handleCancel"
    @before-ok="handleBeforeOk"
  >
    <a-form :model="form">
      <a-form-item field="deckname" label="deckname">
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
  PaginationProps,
  TableColumnData,
  type TableData,
} from "@arco-design/web-vue";
import axios from "axios";
import { Service } from "@/api";
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
  deckname: "",
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

const pagination = ref<PaginationProps>({
  current: 1,
  pageSize: 1,
  total: 1,
});

const handleCellClick = (
  record: TableData,
  column: TableColumnData,
  ev: Event
) => {
  console.log(column);
};

const loadData = async () => {
  const res = await Service.getDeckList(
    pagination.value.current,
    pagination.value.pageSize
  );

  if (res.code === "00000") {
    decks.value = res.data.records;
    pagination.value.current = Number(res.data.current);
    pagination.value.pageSize = Number(res.data.size);
    pagination.value.total = Number(res.data.total);
  } else Notification.error("获取牌组失败");
};

const rename = async () => {
  console.log("rename");
};

const remove = async () => {
  console.log("remove");
};

onMounted(async () => {
  loadData();
});
</script>

<style lang="less"></style>
