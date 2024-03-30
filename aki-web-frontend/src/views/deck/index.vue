<template>
  <h1>Decks</h1>
  <div id="list">
    <a-table :columns="columns" :show-header="false" :data="data">
      <template #actions="{ record }">
        <!--        <a-button @click="$modal.info({ title:'deckName', content:record.deckName })">Actions</a-button>-->
        <a-dropdown position="bottom">
          <a-button>Actions</a-button>
          <template #content>
            <a-doption>Rename</a-doption>
            <a-doption>Share</a-doption>
            <a-doption>Delete</a-doption>
          </template>
        </a-dropdown>
      </template>
    </a-table>
  </div>
  <a-space>
      <div id="list">
        <a-button type="outline" @click="addDeck">
          <template #icon>
            <icon-plus/>
          </template>
          <!-- Use the default slot to avoid extra spaces -->
          <template #default>AddDeck</template>
        </a-button>
        <a-modal v-model:visible="visible" title="input DeckName" @cancel="handleCancel" @before-ok="handleBeforeOk">
          <a-form :model="form">
            <a-form-item field="deckName" label="deckName">
              <a-input v-model="form.deckName"/>
            </a-form-item>
          </a-form>
        </a-modal>
      </div>
  </a-space>

  <!--  <template>-->
  <!--    <a-button type="primary" @click="handleClick">Open Drawer</a-button>-->
  <!--  </template>-->


</template>


<script setup lang="ts">
import {reactive, ref} from 'vue';
import {IconPlus} from '@arco-design/web-vue/es/icon';
import {useRouter} from "vue-router";
import {Drawer} from '@arco-design/web-vue';


const show = ref(true)

//拿取到跳转到的信息
const router = useRouter();

//使用抽屉式的添加方法
const addDeck = () => {
  visible.value = true;
}

const visible = ref(false);
const form = reactive({
  deckName: ''
});

//在此处提交牌组的信息
const handleBeforeOk = (done: any) => {
  console.log(form)
  window.setTimeout(() => {
    done()
    // prevent close
    // done(false)
  }, 3000)
};
const handleCancel = () => {
  visible.value = false;
}

const columns = [{
  title: 'deckName',
  dataIndex: 'deckName',
}, {
  title: 'Email',
  dataIndex: 'email',
  ellipsis: true,
  width: 150,
}, {
  title: 'Actions',
  slotName: 'actions',
  tooltip: {position: 'left'},
  width: 200,
}];
const data = [{
  key: '1',
  deckName: 'Jane Doe',
  email: 'jane.doe@example.com'
}, {
  key: '2',
  deckName: 'Alisa Ross',
  email: 'alisa.ross@example.com'
}, {
  key: '3',
  deckName: 'Kevin Sandra',
  email: 'kevin.sandra@example.com'
}, {
  key: '4',
  deckName: 'Ed Hellen',
  email: 'ed.hellen@example.com'
}, {
  key: '5',
  deckName: 'William Smith',
  email: 'william.smith@example.com'
}];

</script>

<style lang="less">
#list {
  margin-right: 100px;
  margin-left: 100px;
}
</style>
