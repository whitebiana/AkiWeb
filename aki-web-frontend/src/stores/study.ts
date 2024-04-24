import type { Deck, DeckVO } from "@/types/global";
import { defineStore } from "pinia";

const current = JSON.parse(localStorage.getItem('current') as string) as DeckVO;

export const useStudyStore = defineStore("study", {
    state: () => {
        return {
            current: <DeckVO> {
                id: current ? current.id : "",
                name: current ? current.name : "",
                newNum: current ? current.newNum : 0,
                learningNum: current ? current.learningNum : 0,
                reviewNum: current ? current.reviewNum : 0,
            },
            index: 0,
        }
    },
    getters: {

    },
    actions: {

    }
})