<template>
  <a-form :model="form" auto-label-width @submit="handleSubmit">
    <a-form-item field="data" label="题目">
      <MdEditor
          v-model="form.data"
          previewTheme="github"
          :maxLength="8000"
          :toolbars="(toolbars as ToolbarNames[])"
          :tableShape="[16, 8]"
          @onUploadImg="onUploadImg"
          @onError="onError"
      >
        <template #defToolbars>
          <Mark>
            <template #trigger> mark</template>
          </Mark>
          <Emoji>
            <template #trigger> Emoji</template>
          </Emoji>
          <OriginalImg>
            <template #trigger>
              <span>img</span>
            </template>
          </OriginalImg>
          <ExportPDF :modelValue="form.data"/>
        </template>
      </MdEditor>
    </a-form-item>
    <a-form-item field="ans" label="答案">
      <MdEditor
          v-model="form.ans"
          previewTheme="github"
          :maxLength="8000"
          :toolbars="(toolbars as ToolbarNames[])"
          :tableShape="[16, 8]"
          @onUploadImg="onUploadImg"
          @onError="onError"
      >
        <template #defToolbars>
          <Mark>
            <template #trigger> mark</template>
          </Mark>
          <Emoji>
            <template #trigger> Emoji</template>
          </Emoji>
          <OriginalImg>
            <template #trigger>
              <span>img</span>
            </template>
          </OriginalImg>
          <ExportPDF :modelValue="form.ans"/>
        </template>
      </MdEditor>
    </a-form-item>
    <a-form-item field="tags" label="标签">
      <a-select
          v-model="form.tags"
          :style="{ width: '360px' }"
          placeholder="Please select ..."
          multiple
          :max-tag-count="2"
          allow-clear
          scrollbar
      >
        <a-option>标签 1</a-option>
        <a-option>标签 2</a-option>
        <a-option>标签 3</a-option>
        <a-option>标签 4</a-option>
        <a-option>标签 5</a-option>
        <a-option>标签 6</a-option>
      </a-select>
    </a-form-item>

    <a-form-item>
      <a-button html-type="submit" type="primary">Save</a-button>
    </a-form-item>
  </a-form>
</template>

<script setup lang="ts">
import {MdEditor, Themes, ToolbarNames, config} from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import {Mark, Emoji, OriginalImg, ExportPDF} from "@vavt/v3-extension";
import MarkExtension from "markdown-it-mark";
// All CSS for this extension library
import "@vavt/v3-extension/lib/asset/style.css";
// Or individual style for Emoji
// import '@vavt/v3-extension/lib/asset/Emoji.css';
import {CardAddDTO, type CardEditDTO, Service} from "@/api";
import {Message, Notification} from "@arco-design/web-vue";
import {Card, Deck} from "@/types/global";

config({
  editorConfig: {
    // 输入渲染延迟（ms）
    renderDelay: 0,
  },
  markdownItConfig(md) {
    md.use(MarkExtension);
  },
});

const toolbars = [
  "bold",
  "underline",
  "italic",
  "-",
  "title",
  "strikeThrough",
  "sub",
  "sup",
  "quote",
  "unorderedList",
  "orderedList",
  "task",
  "-",
  "codeRow",
  "code",
  "link",
  "image",
  "table",
  "mermaid",
  "katex",
  0,
  1,
  2,
  3,
  "-",
  "revoke",
  "next",
  "save",
  "=",
  "pageFullscreen",
  "fullscreen",
  "preview",
  "htmlPreview",
  "catalog",
];

const route = useRoute();

const form: CardEditDTO = reactive({
  id: "",
  data: "",
  ans: "",
  tags: [],
});

const handleSubmit = async () => {
  const res = await Service.editCard(form);
  if (res.code === "00000") {
    Notification.success("保存成功！！");
  } else Notification.error(res.msg);
};

const onError = (err) => {
  Message.error(err.message);
};

// Array<file>可以上传多个文件
const onUploadImg = async (files: File[], callback) => {
  const res = await Promise.all(
      files.map((file) => {
        return new Promise(async (rev, rej) => {
          const res = await Service.uploadFile({file});
          if (res.code === "00000") rev(res.data);
          else rej(res.msg);
        });
      })
  );
  // 上传图片事件，弹窗会等待上传结果，务必将上传后的 urls 作为 callback 入参回传。
  callback(res);
};

const loadData = async () => {
  const res = await Service.getCard(route.params.cid as string)
  if (res.code === "00000") {
    let card = res.data as Card;
    form.id = card.id;
    form.ans = card.ans;
    form.data = card.data;
    form.did = card.did;
    form.tags = JSON.parse(card.tags);
  } else Notification.error(res.msg);
};

onMounted(async () => {
  await loadData()
});
</script>

<style lang="less" scoped></style>
