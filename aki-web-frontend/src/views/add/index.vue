<template>
  <a-form
    :model="form"
    auto-label-width
    :style="{ width: '100%' }"
    @submit="handleSubmit"
  >
    <a-form-item field="deckname" label="错题本名称">
      <a-input
        v-model="form.deckname"
        placeholder="please enter your post..."
      />
    </a-form-item>
    <a-form-item field="data" label="正面">
      <MdEditor
        v-model="form.data"
        previewTheme="github"
        :maxLength="8000"
        :toolbars="(toolbars as ToolbarNames[])"
        :tableShape="[16, 8]"
        @onUploadImg="onUploadImg"
        @onError="onError"
        @onSave="onSave"
      >
        <template #defToolbars>
          <Mark>
            <template #trigger> mark </template>
          </Mark>
          <Emoji>
            <template #trigger> Emoji </template>
          </Emoji>
          <OriginalImg>
            <template #trigger>
              <span>img</span>
            </template>
          </OriginalImg>
          <ExportPDF :modelValue="form.data" />
        </template>
      </MdEditor>
    </a-form-item>
    <a-form-item field="ans" label="背面">
      <MdEditor
        v-model="form.ans"
        previewTheme="github"
        :maxLength="8000"
        :toolbars="(toolbars as ToolbarNames[])"
        :tableShape="[16, 8]"
        @onUploadImg="onUploadImg"
        @onError="onError"
        @onSave="onSave"
      >
        <template #defToolbars>
          <Mark>
            <template #trigger> mark </template>
          </Mark>
          <Emoji>
            <template #trigger> Emoji </template>
          </Emoji>
          <OriginalImg>
            <template #trigger>
              <span>img</span>
            </template>
          </OriginalImg>
          <ExportPDF :modelValue="form.ans" />
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
        <a-option>Beijing</a-option>
        <a-option>Shanghai</a-option>
        <a-option>Guangzhou</a-option>
        <a-option>Shenzhen</a-option>
        <a-option>Chengdu</a-option>
        <a-option>Wuhan</a-option>
      </a-select>
    </a-form-item>

    <a-form-item>
      <a-button html-type="submit">Submit</a-button>
    </a-form-item>
  </a-form>
</template>

<script setup lang="ts">
import { MdEditor, Themes, ToolbarNames, config } from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { Mark, Emoji, OriginalImg, ExportPDF } from "@vavt/v3-extension";
import MarkExtension from "markdown-it-mark";
// All CSS for this extension library
import "@vavt/v3-extension/lib/asset/style.css";
// Or individual style for Emoji
// import '@vavt/v3-extension/lib/asset/Emoji.css';
import { CardAddDTO, Service } from "@/api";
import { Message, Notification } from "@arco-design/web-vue";

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

const form: CardAddDTO = reactive({
  deckname: "",
  data: "",
  ans: "",
  tags: [],
});

const handleSubmit = async () => {
  const res = await Service.addCard(form);
  if (res.code === "00000") {
    Notification.success("添加成功！！");
  } else Notification.error(res.msg);
};

const onSave = (v: string, h: Promise<string>) => {
  // console.log(v);
  // h.then((html) => {
  //   console.log(html);
  // });
};

const onError = (err) => {
  Message.error(err.message);
};

// Array<file>可以上传多个文件
const onUploadImg = async (files: File[], callback) => {
  const res = await Promise.all(
    files.map((file) => {
      return new Promise(async (rev, rej) => {
        const res = await Service.uploadFile({ file });
        if (res.code === "00000") rev(res.data);
        else rej(res.msg);
      });
    })
  );
  // 上传图片事件，弹窗会等待上传结果，务必将上传后的 urls 作为 callback 入参回传。
  callback(res);
};
</script>

<style lang="less" scoped></style>
