<template>
  <h1>Search</h1>
  <a-space>
    <a-input v-model="form.searchCommand" size="large" />
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
import {CardQueryDTO, Service} from "@/api";
import { Card } from "@/types/global";
import {Message, Modal, Notification} from "@arco-design/web-vue";
import {reactive, ref} from "vue";

const router = useRouter();

const form: CardQueryDTO = reactive({
  searchCommand: "deck:current",
});

//const cards = ref<Card[]>(
  // {
  //   id: "1",
  //   did: "1",
  //   data: "aaaaaaaaaaaaaa",
  //   ans: "aaa",
  //   tags: "aaa",
  //   state: 0,
  //   difficuty: 0,
  //   stability: 0,
  //   reps: 0,
  //   lapess: 0,
  //   elapsedDays: 0,
  //   scheduledDays: 0,
  //   due: "2018-04-04T16:00:00.000Z",
  //   lastReview: "2018-04-04T16:00:00.000Z",
  //   gmtCreate: "2018-04-04T16:00:00.000Z",
  //   gmtModified: "2018-04-04T16:00:00.000Z",
  //   isDeleted: 0,
  // },
  // {
  //   id: "3",
  //   did: "3",
  //   data: "ccc",
  //   ans: "ccc",
  //   tags: "ccc",
  //   state: 0,
  //   difficuty: 0,
  //   stability: 0,
  //   reps: 0,
  //   lapess: 0,
  //   elapsedDays: 0,
  //   scheduledDays: 0,
  //   due: "2018-04-04T16:00:00.000Z",
  //   lastReview: "2018-04-04T16:00:00.000Z",
  //   gmtCreate: "2018-04-04T16:00:00.000Z",
  //   gmtModified: "2018-04-04T16:00:00.000Z",
  //   isDeleted: 0,
  // },
//);
const cards = ref<Card[]>([]);


const search = async () => {
   const res = await Service.getCardList(form)
  if (res.code === "00000") {
    cards.value = res.data as Card[];
     console.log(cards.value)
  } else Notification.error(res.msg)

};

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
