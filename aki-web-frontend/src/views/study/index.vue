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
    <MdPreview editorId="data" :modelValue="card.data" previewTheme="github" />
    <div class="answer" v-show="answerVisible">
      <a-divider />
      <MdPreview editorId="ans" :modelValue="card.ans" previewTheme="github" />
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
// import dayjs from "dayjs";
import dayjs from "@/utils/dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
dayjs.extend(relativeTime);
import * as fsrsJs from "fsrs.js";
import { Card } from "@/types/global";
import { Service } from "@/api";
import { Message } from "@arco-design/web-vue";
import { useStudyStore } from "@/stores/study";
let fsrs = new fsrsJs.FSRS();
// transfer card

const finished = ref(false);

const answerVisible = ref(false);

const studyStore = useStudyStore();

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

const cards = ref<Card[]>([]);

const scheduling = reactive({
  again: "",
  hard: "",
  good: "",
  easy: "",
});

const loadDate = async () => {
  // 获取需要复习的cards
  const res = await Service.getCardList({
    searchCommand: `deck:${studyStore.current}`,
  });
  if (res.code === "00000") {
    if (res.data.length === 0) finished.value = true;
    else {
      cards.value = res.data as Card[];
      card.value = cards.value[studyStore.index];
    }
  } else Message.error(res.msg);
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
  scheduling.again =
    dayjs(scheduling_cards[fsrsJs.Rating.Again].card.due.toISOString()).fromNow(
      true
    ) + "后";
  scheduling.hard =
    dayjs(scheduling_cards[fsrsJs.Rating.Hard].card.due.toISOString()).fromNow(
      true
    ) + "后";
  scheduling.good =
    dayjs(scheduling_cards[fsrsJs.Rating.Good].card.due.toISOString()).fromNow(
      true
    ) + "后";
  scheduling.easy =
    dayjs(scheduling_cards[fsrsJs.Rating.Easy].card.due.toISOString()).fromNow(
      true
    ) + "后";
};

const sync = async (info: fsrsJs.SchedulingInfo) => {
  // 同步数据

  const res = await Service.updateCard({
    id: card.value.id,
    state: info.card.state,
    difficuty: info.card.difficulty,
    stability: info.card.stability,
    reps: info.card.reps,
    lapses: info.card.lapses,
    elapsedDays: info.card.elapsed_days,
    scheduledDays: info.card.scheduled_days,
    due: info.card.due.toISOString(),
    lastReview: info.card.last_review.toISOString(),
  });

  if (res.code === "00000") {
    // 跳转到下一题
  } else Message.error(res.msg);
};

const repeat = (rating: number) => {
  answerVisible.value = true;
  let scheduling_cards = fsrs.repeat(getCard(card.value), new Date());
  // 把card和review_log数据同步到服务器
  sync(scheduling_cards[rating]);

  if (studyStore.index === cards.value.length - 1) {
    finished.value = true;
    return;
  }
  // 跳转到下一题
  answerVisible.value = false;
  card.value = cards.value[++studyStore.index];
};

onMounted(() => {
  loadDate();
});
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
@/stores/study
