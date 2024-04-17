import type { Deck } from "@/types/global";
import { defineStore } from "pinia";

export const useStudyStore = defineStore("study", {
    state: () => {
        return {
            current: localStorage.getItem('current'),
            index: 0
        }
    },
    getters: {

    },
    actions: {

    }
})