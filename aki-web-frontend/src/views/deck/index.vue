<template>
  <a-table :show-header="false" :data="decks" :pagination="false">
    <template #columns>
      <a-table-column title="name" data-index="name"/>
      <a-table-column title="Actions" :width="100">
        <template #cell="{ record }">
          <a-dropdown position="bottom">
            <a-button>Actions</a-button>
            <template #content>
              <a-doption @click="visible2=true;getid=record.id">
                Rename
              </a-doption>
              <a-doption @click="handleDelete(record.id)"> Delete</a-doption>
            </template>
          </a-dropdown>
        </template>
      </a-table-column>
    </template>
  </a-table>
  <br/>

  <a-row>
    <a-col flex="200">
      <div>
        <a-button type="outline" @click="visible = true">
          <template #icon>
            <icon-plus/>
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
      @ok="addDeck"
  >
    <a-form :model="form">
      <a-form-item field="name" label="错题本名称">
        <a-input v-model="form.name"/>
      </a-form-item>
    </a-form>
  </a-modal>
  <a-modal
      v-model:visible="visible2"
      title="input deckname"
      @cancel="handleCancel"
      @ok="handleRename"
  >
    <a-form :model="renameForm">
      <a-form-item field="name" label="错题本名称">
<!--        <a-input v-model="renameForm.id" :default-value="getid"/>-->
        <a-input v-model="renameForm.name"/>
      </a-form-item>
    </a-form>
  </a-modal>


</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import {IconPlus} from "@arco-design/web-vue/es/icon";
import {useRouter} from "vue-router";
import {
  Message,
  Modal,
  Notification,
  PaginationProps,
  TableColumnData,
  type TableData,
} from "@arco-design/web-vue";
import axios from "axios";
import {DeckAddDTO, type DeckEditDTO, Service} from "@/api";
import {Deck} from "@/types/global";

const show = ref(true);

//拿取到跳转到的信息
const router = useRouter();

//使用抽屉式的添加方法
const addDeck = async () => {
  const res = await Service.addDeck(form);
  if (res.code === "00000") {
    await loadData();
    Notification.success("添加成功！！");
  } else Notification.error(res.msg);
};

//这是增加的可视化
const visible = ref(false);
//这里是修改的可视化
const visible2 = ref(false);

const getid = ref(null);

const form: DeckAddDTO = reactive({
  name: "",
});

const renameForm: DeckEditDTO = reactive({
  id: "",
  name: ""
})

const handleCancel = () => {
  visible.value = false;
};

const decks = ref<Deck[]>([]);

const pagination = ref<PaginationProps>({
  current: 1,
  pageSize: 10,
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
const handleRename = async () => {
  renameForm.id = getid.value;
  console.log(renameForm);
  const res = await Service.renameDeck(renameForm)
  if (res.code === "00000") {
    await loadData();
    Notification.success("修改成功！！");
  } else Notification.error(res.msg);
};


const handleDelete = async (id: string) => {
  Modal.warning({
    title: "Warning Notification",
    content: "Are you sure you want to delete?",
    simple: false,
    hideCancel: false,
    onBeforeOk: (
        done: (closed: boolean) => void
    ): boolean | void | Promise<boolean | void> => {
      Service.deleteDeck(id).then(async (res) => {
        if (res.code === "00000") {
          Message.success("删除成功！！");
          await loadData();
          done(true);
        } else {
          Message.error(res.msg);
          done(false);
        }
      });
      // done(true);
    },
  });
};

onMounted(async () => {
  loadData();
});
</script>

<style lang="less"></style>
