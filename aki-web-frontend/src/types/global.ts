export interface Deck {
    id: string,
    name: string,
    gmtCreate: string,
    gmtModified: string,
    isDeleted: number
}

export interface DeckVO {
    id: string,
    name: string,
    newNum: number,
    reviewNum: number,
    learningNum: number
}

export interface Card {
    id: string,
    did: string,
    data: string,
    ans: string,
    tags: string,
    state: number,
    difficuty: number,
    stability: number,
    reps: number,
    lapses: number,
    elapsedDays: number,
    scheduledDays: number,
    due: string,
    lastReview: string,
    gmtCreate: string,
    gmtModified: string,
    isDeleted: number
}