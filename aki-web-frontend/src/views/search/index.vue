<template>
  <h1>Search</h1>
  <a-space wrap>
    <a-input v-model="form.searchCommand" size="large" />

    <a-select
      v-if="tagsVisible"
      v-model="form.tags"
      :options="tags"
      placeholder="标签"
      multiple
      :max-tag-count="1"
      allow-clear
      style="width: 250px"
    >
      <template #label="{ data }">
        <span><icon-plus />{{ data?.label }}</span>
      </template>
      <template #footer>
        <div style="padding: 6px 0; text-align: center">
          <a-button @click="form.tags = []">
            <template #icon>
              <icon-refresh />
            </template>
            <template #default>重置</template>
          </a-button>
        </div>
      </template>
    </a-select>

    <a-button @click="tagsVisible = !tagsVisible">
      <template #icon>
        <icon-tag />
      </template>
    </a-button>
    <a-button type="primary" @click="search">Search</a-button>
  </a-space>

  <br />
  <a-space wrap v-show="form.tags.length !== 0">
    <a-tag
      size="large"
      closable
      v-for="(tag, index) of form.tags"
      :key="tag"
      @close="handleTagClose(index)"
      >{{ tag }}</a-tag
    >
  </a-space>
  <br />
  <a-list :data="cards" :bordered="false">
    <template #item="{ item }">
      <a-list-item>
        <a-list-item-meta :description="item.data"> </a-list-item-meta>
        <template #actions>
          <a-link @click="edit(item.id)"><icon-edit />Edit</a-link>
          <a-link status="danger" @click="remove(item.id)"
            ><icon-delete />Delete</a-link
          >
        </template>
      </a-list-item>
    </template>
  </a-list>
</template>

<script setup lang="ts">
import { CardQueryDTO, Service } from "@/api";
import { useStudyStore } from "@/stores/study";
import { Card } from "@/types/global";
import {
  Message,
  Modal,
  Notification,
  SelectOptionGroup,
} from "@arco-design/web-vue";
import { reactive, ref } from "vue";
import defaultTags from "@/config/tags.json";

const tags: SelectOptionGroup[] = [
  {
    isGroup: true,
    label: "数据结构",
    options: defaultTags.ds,
  },
];

const router = useRouter();

const studyStore = useStudyStore();

const form: CardQueryDTO = reactive({
  searchCommand: "deck:current",
  tags: [],
});

const cards = ref<Card[]>([]);

const tagsVisible = ref(false);

const search = async () => {
  const res = await Service.getCardList({
    searchCommand:
      form.searchCommand === "deck:current"
        ? `deck:${studyStore.current.name}`
        : form.searchCommand,
    tags: form.tags,
  });

  if (res.code === "00000") {
    cards.value = res.data as Card[];
  } else Notification.error(res.msg);
};

const edit = (id: string) => {
  router.push(`/edit/${id}`);
};

const remove = (id: string) => {
  Modal.warning({
    title: "Warning Notification",
    content: "Are you sure you want to delete?",
    simple: false,
    hideCancel: false,
    onBeforeOk: (
      done: (closed: boolean) => void
    ): boolean | void | Promise<boolean | void> => {
      Service.deleteCard(id).then(async (res) => {
        if (res.code === "00000") {
          Message.success("删除成功");
          await search();
          done(true);
        } else {
          Message.error(res.msg);
          done(false);
        }
      });
      done(true);
    },
  });
};

const handleTagClose = async (index: number) => {
  form.tags.splice(index, 1);
  // await nextTick();
};

onMounted(() => {
  search();
});
</script>

<style lang="less" scoped>
::v-deep .arco-list-item-main {
  width: calc(100% - 135px);
  .arco-list-item-meta-content {
    width: calc(100% - 135px);
    .arco-list-item-meta-description {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
}

::v-deep .arco-list-item {
  padding: 13px 12px !important;
}
</style>
