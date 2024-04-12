<template>
  <h1>Search</h1>
  <a-space>
    <a-input v-model="form.cardnameOrDeckname" size="large" />
    <a-button type="primary" @click="search">Search</a-button>
  </a-space>
  <br />
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
import { CardQueryDTO } from "@/api";
import { Card } from "@/types/global";
import { Modal } from "@arco-design/web-vue";
import dayjs from "dayjs";
import * as fsrsJs from "fsrs.js";
// let fsrs = new fsrsJs.FSRS();
// let card = new fsrsJs.Card();
// let rating = fsrsJs.Rating;
// let state = fsrsJs.State;
// console.log(fsrs.repeat(card, new Date()));

const router = useRouter();

const form: CardQueryDTO = reactive({
  cardnameOrDeckname: "deck:current",
});

const cards = ref<Card[]>([
  {
    id: "1",
    did: "1",
    data: "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    ans: "aaa",
    tags: "aaa",
    state: 0,
    difficuty: 0,
    stability: 0,
    reps: 0,
    lapess: 0,
    elapsedDays: 0,
    scheduledDays: 0,
    due: "2018-04-04T16:00:00.000Z",
    lastReview: "2018-04-04T16:00:00.000Z",
    gmtCreate: "2018-04-04T16:00:00.000Z",
    gmtModified: "2018-04-04T16:00:00.000Z",
    isDeleted: 0,
  },
  {
    id: "2",
    did: "2",
    data: "bbb",
    ans: "bbb",
    tags: "bbb",
    state: 0,
    difficuty: 0,
    stability: 0,
    reps: 0,
    lapess: 0,
    elapsedDays: 0,
    scheduledDays: 0,
    due: "2018-04-04T16:00:00.000Z",
    lastReview: "2018-04-04T16:00:00.000Z",
    gmtCreate: "2018-04-04T16:00:00.000Z",
    gmtModified: "2018-04-04T16:00:00.000Z",
    isDeleted: 0,
  },
  {
    id: "3",
    did: "3",
    data: "ccc",
    ans: "ccc",
    tags: "ccc",
    state: 0,
    difficuty: 0,
    stability: 0,
    reps: 0,
    lapess: 0,
    elapsedDays: 0,
    scheduledDays: 0,
    due: "2018-04-04T16:00:00.000Z",
    lastReview: "2018-04-04T16:00:00.000Z",
    gmtCreate: "2018-04-04T16:00:00.000Z",
    gmtModified: "2018-04-04T16:00:00.000Z",
    isDeleted: 0,
  },
]);

const search = () => {};

const edit = (id: string) => {
  router.push(`/edit/${id}/`);
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
      // Service.deleteDeck(id).then(async (res) => {
      //   if (res.code === "00000") {
      //     Message.success("删除成功！！");
      //     await loadData();
      //     done(true);
      //   } else {
      //     Message.error(res.msg);
      //     done(false);
      //   }
      // });
      done(true);
    },
  });
};
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
