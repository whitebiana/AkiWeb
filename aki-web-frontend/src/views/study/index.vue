<template>
  <div class="container" v-if="!finished">
    <a-row :wrap="false" align="center">
      <a-col flex="230px">
        <a-space>
          <a-button type="outline">Edit</a-button>
          <a-button type="outline">Options</a-button>
          <a-button type="outline">
            <template #icon>
              <icon-plus />
            </template>
          </a-button>
          <a-button type="outline">
            <template #icon>
              <icon-minus />
            </template>
          </a-button>
        </a-space>
      </a-col>
      <a-col flex="auto"></a-col>
      <a-col flex="64px">
        <a-typography-text type="primary" underline>1</a-typography-text>
        + <a-typography-text type="danger" underline>1</a-typography-text> +
        <a-typography-text type="success" underline>1</a-typography-text>
      </a-col>
    </a-row>
    <MdPreview
      editorId="data"
      modelValue="<center>1 + 1 = ?</center>"
      previewTheme="github"
    />
    <div class="answer" v-show="answerVisible">
      <a-divider />
      <MdPreview
        editorId="data"
        modelValue="<center>2</center>"
        previewTheme="github"
      />
    </div>
    <div class="operation">
      <a-button type="primary" @click="showAnswer" v-show="!answerVisible"
        >Show Answer</a-button
      >
      <a-space v-show="answerVisible">
        <div>
          <div>{{ scheduling.again }}</div>
          <a-button type="primary" @click="repeat(1)">Again</a-button>
        </div>
        <div>
          <div>{{ scheduling.hard }}</div>
          <a-button type="primary" @click="repeat(2)">Hard</a-button>
        </div>
        <div>
          <div>{{ scheduling.good }}</div>
          <a-button type="primary" @click="repeat(3)">Good</a-button>
        </div>
        <div>
          <div>{{ scheduling.easy }}</div>
          <a-button type="primary" @click="repeat(4)">Easy</a-button>
        </div>
      </a-space>
    </div>
  </div>
  <h1 v-else>Congratulations! You have finished this deck for now.</h1>
</template>

<script setup lang="ts">
// 仅预览
import { MdPreview, MdCatalog } from "md-editor-v3";
// preview.css相比style.css少了编辑器那部分样式
import "md-editor-v3/lib/preview.css";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
dayjs.extend(relativeTime);
import * as fsrsJs from "fsrs.js";
import { Card } from "@/types/global";
let fsrs = new fsrsJs.FSRS();
// transfer card

const finished = ref(false);

const answerVisible = ref(false);

const card = ref<Card>({
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
});

const scheduling = reactive({
  again: "",
  hard: "",
  good: "",
  easy: "",
});

const loadDate = async () => {
  // 获取需要复习的card
  // 如果复习完了，
  // finished.value = true;
};

const getCard = (card: Card): fsrsJs.Card => {
  let res = new fsrsJs.Card();
  res.state = card.state;
  res.difficulty = card.difficuty;
  res.stability = card.stability;
  res.reps = card.reps;
  res.lapses = card.lapess;
  res.elapsed_days = card.elapsedDays;
  res.scheduled_days = card.scheduledDays;
  res.due = new Date(card.due);
  res.last_review = new Date(card.lastReview);
  return res;
};

const showAnswer = () => {
  answerVisible.value = true;
  let scheduling_cards = fsrs.repeat(getCard(card.value), new Date());

  // 计算复习间隔
  scheduling.again = dayjs(
    scheduling_cards[fsrsJs.Rating.Again].card.due.toISOString()
  ).fromNow();
  scheduling.hard = dayjs(
    scheduling_cards[fsrsJs.Rating.Hard].card.due.toISOString()
  ).fromNow();
  scheduling.good = dayjs(
    scheduling_cards[fsrsJs.Rating.Good].card.due.toISOString()
  ).fromNow();
  scheduling.easy = dayjs(
    scheduling_cards[fsrsJs.Rating.Easy].card.due.toISOString()
  ).fromNow();
};

const sync = async (info: fsrsJs.SchedulingInfo) => {
  // 同步数据
  console.log(info.card);
};

const repeat = (rating: number) => {
  answerVisible.value = true;
  let scheduling_cards = fsrs.repeat(getCard(card.value), new Date());
  // 把card和review_log数据同步到服务器
  sync(scheduling_cards[rating]);
};

onMounted(() => {});
</script>

<style lang="less" scoped>
.answer {
  padding-bottom: 50px;
}

.operation {
  width: 100%;
  padding-top: 18px;
  padding-bottom: 24px;
  text-align: center;
  position: fixed;
  left: 0;
  bottom: 0;
  background-color: white;
}
</style>
