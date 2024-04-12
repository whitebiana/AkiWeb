export interface Deck {
    id: string,
    name: string,
    gmt_create: Date,
    gmt_modified: Date,
    is_deleted: number
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
    lapess: number,
    elapsedDays: number,
    scheduledDays: number,
    due: string,
    lastReview: string,
    gmtCreate: string,
    gmtModified: string,
    isDeleted: number
}